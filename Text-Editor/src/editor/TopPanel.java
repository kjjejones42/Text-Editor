package editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;

class TopPanel extends JPanel {

    static final long serialVersionUID = 1;

    private JTextField filenameField;
    private final TextEditor editor;

    private void processSelectButton(File file) {
        if (!file.exists()) {
            return;
        }
        editor.loadFile(file);
    }

    void setFilenameField(String text){
        filenameField.setText(text);
    }

    TopPanel(TextEditor editor) {
        setLayout(new GridBagLayout());
        this.editor = editor;

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

        filenameField = new JTextField("");
        filenameField.setName("FilenameField");

        JButton saveButton = new JButton("Save");
        saveButton.setName("SaveButton");
        saveButton.addActionListener(e -> {
            editor.saveFile(filenameField.getText());
        });

        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(e -> {
            editor.loadFile(new File(filenameField.getText()));
        });


        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 0, 5);
        c.fill = GridBagConstraints.BOTH;

        add(selectButton, c);
        c.weightx = 1.0;
        add(filenameField, c);
        c.weightx = 0.0;
        add(saveButton, c);
        add(loadButton, c);

        setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
    }

}