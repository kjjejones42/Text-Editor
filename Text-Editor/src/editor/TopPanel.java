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
        JButton selectButton = new JButton("Open");

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
        JButton saveButton = new JButton("Save");
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
        JButton startSearchButton = new JButton("Search");
        startSearchButton.setName("StartSearchButton");
        return startSearchButton;
    }

    private JButton createPreviousMatchButton() {
        JButton previousMatchButton = new JButton("Prev");
        previousMatchButton.setName("PreviousMatchButton");
        return previousMatchButton;
    }

    private JButton createNextMatchButton() {
        JButton nextMatchButton = new JButton("Next");
        nextMatchButton.setName("NextMatchButton");
        return nextMatchButton;
    }

    private JCheckBox createUseRegExCheckbox() {
        JCheckBox useRegExCheckbox = new JCheckBox("Use Regex");
        useRegExCheckbox.setName("UseRegExCheckbox");
        return useRegExCheckbox;
    }

    private void addChildComponents() {

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 0, 5);
        c.fill = GridBagConstraints.BOTH;

        add(openButton, c);
        add(saveButton, c);
        c.weightx = 1.0;
        add(searchField, c);
        c.weightx = 0;
        add(startSearchButton);
        add(previousMatchButton);
        add(nextMatchButton);
        add(useRegExCheckbox);

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

        setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
    }

}