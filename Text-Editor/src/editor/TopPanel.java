package editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

class TopPanel extends JPanel {

    static final long serialVersionUID = 1;

    private String fileName;
    private JTextField filenameField;
    private final TextEditor editor;

    private String loadFile(File file) {
        if (!file.exists()){
            return "File does not exist.";
        }
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());            
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void saveFile(String fileContents, String file) {
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

    void processSelectButton(File file){
        if (!file.exists()){
            return;
        }
        String fileContent = loadFile(file);
        filenameField.setText(file.getAbsolutePath());
        editor.setText(fileContent);

    }

    TopPanel(TextEditor editor) {
        setLayout(new BorderLayout());
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
            saveFile(editor.getText(), filenameField.getText());           
        });

        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(e -> {
            editor.setText(loadFile(new File(filenameField.getText())));           
        });

        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,0,5);
        c.fill = GridBagConstraints.BOTH ;

        top.add(selectButton, c);
        c.weightx = 1.0;        
        top.add(filenameField, c);        
        c.weightx = 0.0;
        top.add(saveButton, c);
        top.add(loadButton, c);
        
        top.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        add(top, BorderLayout.CENTER);
    }

}