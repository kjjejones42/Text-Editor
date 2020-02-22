package editor;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;

class TopPanel extends JPanel {

    static final long serialVersionUID = 1;

    private final TextEditor editor;
    private final JButton openButton;
    private final JButton saveButton;
    private final JTextField searchField;
    private final JButton startSearchButton;
    private final JButton previousMatchButton;
    private final JButton nextMatchButton;
    private final JCheckBox useRegExCheckbox;

    /**
     * 
     * Button that opens a filemanager to "OpenButton" Button that saves the file to
     * "SaveButton" Search field to "SearchField" Start search button to
     * "StartSearchButton" Previous match button to "PreviousMatchButton" Next match
     * button to "NextMatchButton" Use regex checkbox to "UseRegExCheckbox"
     */

    private void processSelectButton(File file) {
        if (!file.exists()) {
            return;
        }
        editor.setFileObj(file);
        editor.loadFile();
    }

    private JButton createSelectButton() {
        Icon i = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/general/Open24.gif"));
        JButton selectButton = new JButton(i);

        if (TextEditor.WINDOWS) {
            FileDialog chooser = new java.awt.FileDialog((java.awt.Frame) null, "Open", FileDialog.LOAD);
            selectButton.addActionListener(e -> {
                chooser.setVisible(true);
                processSelectButton(new File(chooser.getDirectory() + chooser.getFile()));
            });
        } else {
            JFileChooser chooser = new JFileChooser();
            selectButton.addActionListener(e -> {
                if (chooser.showOpenDialog(selectButton) == JFileChooser.APPROVE_OPTION) {
                    processSelectButton(chooser.getSelectedFile());
                }
            });
        }
        return selectButton;
    }

    private JButton createSaveButton() {
        Icon i = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/general/Save24.gif"));
        JButton saveButton = new JButton(i);
        saveButton.setName("SaveButton");
        saveButton.addActionListener(e -> {
            editor.setFileObj(new File(searchField.getText()));
            editor.saveFile();
        });
        return saveButton;
    }

    private JTextField createSearchField() {
        JTextField searchField = new JTextField("");
        searchField.setName("SearchField");

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                process();
            }

            public void removeUpdate(DocumentEvent e) {
                process();
            }

            public void insertUpdate(DocumentEvent e) {
                process();
            }

            public void process() {
                editor.setFileObj(new File(searchField.getText()));
            }
        });
        return searchField;
    }

    private JButton createStartSearchButton() {
        Icon i = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/general/Find24.gif"));
        JButton startSearchButton = new JButton(i);
        startSearchButton.setName("StartSearchButton");
        return startSearchButton;
    }

    private JButton createPreviousMatchButton() {
        Icon i = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/navigation/Back24.gif"));
        JButton previousMatchButton = new JButton(i);
        previousMatchButton.setName("PreviousMatchButton");
        return previousMatchButton;
    }

    private JButton createNextMatchButton() {
        Icon i = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/navigation/Forward24.gif"));
        JButton nextMatchButton = new JButton(i);
        nextMatchButton.setName("NextMatchButton");
        return nextMatchButton;
    }

    private JCheckBox createUseRegExCheckbox() {
        JCheckBox useRegExCheckbox = new JCheckBox("Use Regex");
        useRegExCheckbox.setName("UseRegExCheckbox");
        return useRegExCheckbox;
    }

    private void addChildComponents() {

        GridBagConstraints defaultConstraints = new GridBagConstraints();
        defaultConstraints.insets = new Insets(0, 5, 0, 5);
        defaultConstraints.fill = GridBagConstraints.HORIZONTAL;        
        defaultConstraints.weightx = 0.0;

        GridBagConstraints fillWidth = (GridBagConstraints) defaultConstraints.clone();        
        fillWidth.weightx = 1.0;
        
        GridBagConstraints noLeftPadding = (GridBagConstraints) defaultConstraints.clone();       
        noLeftPadding.insets = new Insets(0, 0, 0, 5);

        GridBagConstraints noRightPadding = (GridBagConstraints) defaultConstraints.clone();  
        noRightPadding.insets = new Insets(0, 5, 0, 0);

        add(openButton, noRightPadding);
        add(saveButton, noLeftPadding);
        add(searchField, fillWidth);
        add(startSearchButton, defaultConstraints);
        add(previousMatchButton, noRightPadding);
        add(nextMatchButton, noLeftPadding);
        add(useRegExCheckbox, defaultConstraints);

    }

    void setSearchField(String text) {
        searchField.setText(text);
    }

    TopPanel(TextEditor editor) {
        setLayout(new GridBagLayout());

        this.editor = editor;
        this.saveButton = createSaveButton();
        this.openButton = createSelectButton();
        this.searchField = createSearchField();
        this.startSearchButton = createStartSearchButton();
        this.previousMatchButton = createPreviousMatchButton();
        this.nextMatchButton = createNextMatchButton();
        this.useRegExCheckbox = createUseRegExCheckbox();

        addChildComponents();

        setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
    }

}