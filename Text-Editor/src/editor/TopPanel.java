package editor;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

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

    private JButton createSelectButton() {
        JButton selectButton = new JButton(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Open24.gif")));
        selectButton.setToolTipText("Open File");
        selectButton.addActionListener(e -> editor.selectAndLoadFile(selectButton));
        return selectButton;
    }

    private JButton createSaveButton() {
        JButton saveButton = new JButton(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Save24.gif")));
        saveButton.setName("SaveButton");
        saveButton.setToolTipText("Save File");
        saveButton.addActionListener(e -> editor.saveFile());
        return saveButton;
    }

    private JTextField createSearchField() {
        JTextField searchField = new JTextField("");
        searchField.setName("SearchField");
        searchField.setToolTipText("Search Term");

        searchField.getDocument().addDocumentListener(new DocumentListener() {

            void process() {
                editor.setSearchTerm(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                process();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                process();
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                process();
            }
        });
        return searchField;
    }

    private JButton createStartSearchButton() {
        JButton startSearchButton = new JButton(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Find24.gif")));
        startSearchButton.setName("StartSearchButton");
        startSearchButton.setToolTipText("Search");
        startSearchButton.addActionListener(e -> {
            editor.startSearch();
        });
        return startSearchButton;
    }

    private JButton createPreviousMatchButton() {
        JButton previousMatchButton = new JButton(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/navigation/Back24.gif")));
        previousMatchButton.setName("PreviousMatchButton");
        previousMatchButton.setToolTipText("Previous Match");
        previousMatchButton.addActionListener(e -> {
            editor.prevSearchTerm();
        });
        return previousMatchButton;
    }

    private JButton createNextMatchButton() {
        JButton nextMatchButton = new JButton(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/navigation/Forward24.gif")));
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.setToolTipText("Next Match");
        nextMatchButton.addActionListener(e -> {
            editor.nextSearchTerm();
        });
        return nextMatchButton;
    }

    private JCheckBox createUseRegExCheckbox() {
        JCheckBox useRegExCheckbox = new JCheckBox("Use Regex");
        useRegExCheckbox.setName("UseRegExCheckbox");
        useRegExCheckbox.addActionListener(e -> editor.toggleRegex());
        editor.registerRegexToggle(useRegExCheckbox);
        return useRegExCheckbox;
    }

    private void addChildComponents() {
        setLayout(new GridBagLayout());

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

    TopPanel(TextEditor editor) {

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