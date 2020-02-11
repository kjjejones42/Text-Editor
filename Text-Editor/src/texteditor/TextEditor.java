package texteditor;

import javax.swing.*;

public class TextEditor extends JFrame {

    static final long serialVersionUID = 1;

    public TextEditor() {
        super("Swing Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");
        textArea.setBounds(10, 10, 260, 240);
        add(textArea);

        setVisible(true);
    }

    public static void main(final String[] args) {
        new TextEditor();
    }
}