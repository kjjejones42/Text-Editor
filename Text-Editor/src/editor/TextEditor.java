package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextEditor extends JFrame {

    static final long serialVersionUID = 1;

    public TextEditor() {
        super("Swing Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setName("TextArea");
        textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        panel.add(textArea);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(final String[] args) {
        new TextEditor();
    }
}