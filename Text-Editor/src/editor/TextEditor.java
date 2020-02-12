package editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

public class TextEditor extends JFrame {

    static final long serialVersionUID = 1;

    static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().contains("windows");

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

    public TextEditor() {
        super("Swing Example");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextArea textArea = new JTextArea();

        JPanel textAreaContainer = new JPanel();
        textAreaContainer.setLayout(new BorderLayout());
        textAreaContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textAreaContainer.add(textArea, BorderLayout.CENTER);
        textAreaContainer.setBackground(Color.WHITE);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane pane = new JScrollPane(textAreaContainer);
        center.add(pane);
        add(center, BorderLayout.CENTER);

        textArea.setName("TextArea");
        pane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JPanel top = new JPanel();
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton button = new JButton("Choose File");

        if (WINDOWS) {
            FileDialog chooser = new java.awt.FileDialog((java.awt.Frame) null, "Open", FileDialog.LOAD);
            button.addActionListener(e -> {
                chooser.setVisible(true);
                String fileContent = readLineByLineJava8(new File(chooser.getDirectory() + chooser.getFile()));
                textArea.setText(fileContent);
            });
        } else {
            JFileChooser chooser = new JFileChooser();
            button.addActionListener(e -> {
                if (chooser.showOpenDialog(button) == JFileChooser.APPROVE_OPTION) {
                    String fileContent = readLineByLineJava8(chooser.getSelectedFile());                 
                    textArea.setText(fileContent);
                }
            });

        }

        top.add(button);
        add(top, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(final String[] args) {
        new TextEditor();
    }
}