package editor;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;

class TopPanel extends JPanel {

    static final long serialVersionUID = 1;

    private JTextField filenameField;
    private final TextEditor editor;
    private final JButton selectButton;
    private final JButton saveButton;
    private final JButton loadButton;

    private void processSelectButton(File file) {
        if (!file.exists()) {
            return;
        }
        editor.setFileObj(file);
        editor.loadFile();
    }

    private JButton createSelectButton() {
        JButton selectButton = new JButton("Choose File");

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
            editor.setFileObj(new File(filenameField.getText()));
            editor.saveFile();
        });
        return saveButton;
    }

    private JButton createLoadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(e -> {
            editor.setFileObj(new File(filenameField.getText()));
            editor.loadFile();
        });
        return loadButton;
    }

    private void addChildComponents() {

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 0, 5);
        c.fill = GridBagConstraints.BOTH;

        add(selectButton, c);
        c.weightx = 1.0;
        add(filenameField, c);
        c.weightx = 0.0;
        add(saveButton, c);
        add(loadButton, c);

    }

    void setFilenameField(String text) {
        filenameField.setText(text);
    }

    TopPanel(TextEditor editor) {
        setLayout(new GridBagLayout());

        this.editor = editor;
        this.selectButton = createSelectButton();
        this.saveButton = createSaveButton();
        this.loadButton = createLoadButton();

        filenameField = new JTextField("");
        filenameField.setName("FilenameField");

        filenameField.getDocument().addDocumentListener(new DocumentListener() {
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
                editor.setFileObj(new File(filenameField.getText()));
            }
        });

        addChildComponents();

        setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
    }

}