package editor;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {

    static final long serialVersionUID = 1;

    static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().contains("windows");
    

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

        add(new CenterPanel(), BorderLayout.CENTER);        
        add(new TopPanel(), BorderLayout.NORTH);
        setVisible(true);
    }

    public static void main(final String[] args) {
        new TextEditor();
    }
}