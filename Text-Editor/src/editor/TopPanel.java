package editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

class TopPanel extends JPanel {

    static final long serialVersionUID = 1;

    private String fileName;

    private static String loadFile(File filePath) {
        if (!filePath.exists()){
            return "";
        }
        try {
            byte[] bytes = Files.readAllBytes(filePath.toPath());            
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void saveFile(String fileContents, String file) {
        try {
            byte[] bytes = fileContents.getBytes(StandardCharsets.UTF_8);
            Path path = Paths.get(file);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getFileName(){
        return fileName;
    }

    void setFileName(String fileName){
        this.fileName = fileName;
    }

    TopPanel() {
        JPanel top = new JPanel();
        JButton button = new JButton("Choose File");

        if (TextEditor.WINDOWS) {
            FileDialog chooser = new java.awt.FileDialog((java.awt.Frame) null, "Open", FileDialog.LOAD);
            button.addActionListener(e -> {
                chooser.setVisible(true);
                String fileContent = loadFile(new File(chooser.getDirectory() + chooser.getFile()));
                CenterPanel.setText(fileContent);
            });
        } else {
            JFileChooser chooser = new JFileChooser();
            button.addActionListener(e -> {
                if (chooser.showOpenDialog(button) == JFileChooser.APPROVE_OPTION) {
                    String fileContent = loadFile(chooser.getSelectedFile());
                    CenterPanel.setText(fileContent);
                }
            });

        }

        JTextField filenameField = new JTextField("", 20);
        filenameField.setName("FilenameField");

        JButton saveButton = new JButton("Save");
        saveButton.setName("SaveButton");
        saveButton.addActionListener(e -> {
            saveFile(CenterPanel.getText(), filenameField.getText());           
        });

        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(e -> {
            CenterPanel.setText(loadFile(new File(filenameField.getText())));           
        });
        
        top.add(button);
        top.add(filenameField);
        top.add(saveButton);
        top.add(loadButton);
        add(top);
    }

}