package editor;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

class CenterPanel extends JPanel {

    static final long serialVersionUID = 1;

    final JTextArea textArea;

    void setText(String text) {
        textArea.setText(text);
    }

    String getText() {
        return textArea.getText();
    }

    void selectSearchResult(Searcher.SearchResult result) {      
        int start = result.startInclusive;
        int end = result.endExclusive;  
        textArea.setCaretPosition(end);
        textArea.select(start, end);
        textArea.grabFocus();
    }

    CenterPanel(TextEditor editor) {

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
        textArea.getDocument().addDocumentListener(new DocumentListener(){

            void process() {
                editor.notifyTextHasChanged();
            }
        
            @Override
            public void removeUpdate(DocumentEvent e) {
                process();                
            }
        
            @Override
            public void insertUpdate(DocumentEvent e) {
                process();                
                
            }
        
            @Override
            public void changedUpdate(DocumentEvent e) {
                process();                
                
            }
        });
        textAreaContainer.add(textArea, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textAreaContainer);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        scrollPane.setName("ScrollPane");

        add(scrollPane, BorderLayout.CENTER);
    }
}