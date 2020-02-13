package editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class TextEditor extends JFrame {

    static final long serialVersionUID = 1;

    static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().contains("windows");

    CenterPanel centerPanel = new CenterPanel(this);
    TopPanel topPanel = new TopPanel(this);
    
    void loadFile(File file) {
        try {
            String fileContent;
            if (!file.exists()) {
                fileContent = "File does not exist.";
            } else {
                byte[] bytes = Files.readAllBytes(file.toPath());
                fileContent = new String(bytes, StandardCharsets.UTF_8);
            }
            topPanel.setFilenameField(file.getAbsolutePath());
            centerPanel.setText(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void saveFile(String file) {
        try {
            byte[] bytes = centerPanel.getText().getBytes(StandardCharsets.UTF_8);
            Path path = Paths.get(file);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextEditor() {
        super("Text Editor");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 480);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        setVisible(true);
        centerPanel.requestFocusInWindow();
    }

    public static void main(final String[] args) {
        new TextEditor();
    }
}