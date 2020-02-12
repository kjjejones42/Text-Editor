package editor;

import javax.swing.*;
import java.awt.*;

class CenterPanel extends JPanel {

    static final long serialVersionUID = 1;

    static JTextArea textArea;

    static void setText(String text) {
        textArea.setText(text);
    }

    static String getText(){
        return textArea.getText();
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
        textArea.setName("TextArea");
        textAreaContainer.add(textArea, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textAreaContainer);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        scrollPane.setName("ScrollPane");

        add(scrollPane, BorderLayout.CENTER);
    }
}