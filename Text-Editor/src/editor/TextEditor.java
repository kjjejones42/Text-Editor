package editor;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {

    static final long serialVersionUID = 1;

    static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().contains("windows");

    CenterPanel centerPanel = new CenterPanel();
    TopPanel topPanel = new TopPanel(this);

    void setText(String text) {
        centerPanel.setText(text);
    }

    String getText() {
        return centerPanel.getText();
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