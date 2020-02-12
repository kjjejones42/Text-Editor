package editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

class TopPanel extends JPanel {

    static final long serialVersionUID = 1;

    private static String readLineByLineJava8(File filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            byte[] bytes = Files.readAllBytes(filePath.toPath());
            for (byte b : bytes) {
                contentBuilder.append((char) (b & 0xFF));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    TopPanel() {
        JPanel top = new JPanel();
        JButton button = new JButton("Choose File");

        if (TextEditor.WINDOWS) {
            FileDialog chooser = new java.awt.FileDialog((java.awt.Frame) null, "Open", FileDialog.LOAD);
            button.addActionListener(e -> {
                chooser.setVisible(true);
                String fileContent = readLineByLineJava8(new File(chooser.getDirectory() + chooser.getFile()));
                CenterPanel.setText(fileContent);
            });
        } else {
            JFileChooser chooser = new JFileChooser();
            button.addActionListener(e -> {
                if (chooser.showOpenDialog(button) == JFileChooser.APPROVE_OPTION) {
                    String fileContent = readLineByLineJava8(chooser.getSelectedFile());
                    CenterPanel.setText(fileContent);
                }
            });

        }

        JTextField filenameField = new JTextField("Filename", 20);
        filenameField.setName("FilenameField");

        JButton saveButton = new JButton("Save");
        saveButton.setName("SaveButton");

        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        
        top.add(button);
        top.add(filenameField);
        top.add(saveButton);
        top.add(loadButton);
        add(top);
    }

}