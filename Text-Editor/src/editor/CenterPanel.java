package editor;


import javax.swing.*;
import java.awt.*;

class CenterPanel extends JPanel  {

    static JTextArea textArea;

    static void setText(String text){
        textArea.setText(text);
    }

    CenterPanel() {
        
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
        JPanel textAreaContainer = new JPanel();
        textAreaContainer.setLayout(new BorderLayout());
        textAreaContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));        
        textAreaContainer.setBackground(Color.WHITE);
        
        textArea = new JTextArea();
        textArea.setName("TextArea");        
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textAreaContainer.add(textArea, BorderLayout.CENTER);

        JScrollPane pane = new JScrollPane(textAreaContainer);
        pane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(pane, BorderLayout.CENTER);
    }
}