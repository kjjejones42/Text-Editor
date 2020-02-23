package editor;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import javax.imageio.ImageIO;
import java.util.*;

public class TextEditor extends JFrame {

    static final long serialVersionUID = 1;

    static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().contains("windows");

    final CenterPanel centerPanel;
    final TopPanel topPanel;
    final MenuBar menuBar;    
    final JFileChooser chooser;
    private String searchTerm;
    private String text;
    private boolean hasTextChanged;
    private boolean isRegex;
    private final java.util.List<AbstractButton> registeredRegexToggles = new ArrayList<>();
    private File file;
    private java.util.List<Searcher.SearchResult> searchResults;
    private final Searcher searcher;
    private int searchIndex;

    private void updateTextField() {
        this.text = centerPanel.getText();   
        hasTextChanged = false;    
    }

    private void selectSearchResult() {  
        if (searchResults.isEmpty()){
            return;
        }
        int max = searchResults.size() - 1; 
        if (this.searchIndex < 0) {
            this.searchIndex = max;
        } else if (this.searchIndex > max) {
            this.searchIndex = 0;
        }
        centerPanel.selectSearchResult(searchResults.get(searchIndex));
    }

    void notifyTextHasChanged() {
        this.hasTextChanged = true;
    }

    void setText(String text){
        this.text = text;
    }

    void loadFile() {
        new SwingWorker<String,Object>() {
            @Override
            protected String doInBackground() throws Exception {
                if (file == null || !file.exists()) {
                    return "";
                } else {
                    byte[] bytes = Files.readAllBytes(file.toPath());
                    return new String(bytes, StandardCharsets.ISO_8859_1);
                }
            }
            @Override
            protected void done() {
                String text;
                try {
                    text = get();                                
                } catch (Exception e) {
                    text = "";
                } 
                setText(text);
                centerPanel.setText(text);  
            }
        }.execute();
    }

    void selectAndLoadFile(Component obj) {      

        chooser.setVisible(true);
        File chosenFile = null;
        if (chooser.showOpenDialog(obj) == JFileChooser.APPROVE_OPTION) {
            chosenFile = chooser.getSelectedFile();        
        }         
        chooser.setVisible(false);       
        if (chosenFile != null && chosenFile.exists()) {
            setFileObj(chosenFile);  
            loadFile();
        }    
    }

    void saveFile() {        
        try {
            if (file != null && file.isFile()) {
                byte[] bytes = centerPanel.getText().getBytes(StandardCharsets.ISO_8859_1);
                Files.write(file.toPath(), bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setFileObj(File file) {        
        this.file = file;
    }

    void startSearch() {
        SwingWorker<java.util.List<Searcher.SearchResult>, Object> worker = new SwingWorker<java.util.List<Searcher.SearchResult>, Object>() {
            @Override
            protected java.util.List<Searcher.SearchResult> doInBackground() throws Exception {                
                updateTextField();
                return isRegex ? searcher.regexSearch(text, searchTerm) : searcher.stringSearch(text, searchTerm);
            }
        };            
        JWindow window = new JWindow(this);   
        window.setVisible(false);
        window.setLocationRelativeTo(this);         
        window.setContentPane(new JLabel("Searching..."));
        window.pack();
        worker.addPropertyChangeListener(new PropertyChangeListener(){           
            public void propertyChange(PropertyChangeEvent event) {
                if ("state".equals(event.getPropertyName())
                        && SwingWorker.StateValue.DONE == event.getNewValue()) {                             
                    window.setVisible(false);
                    window.dispose();
                }
            }
        });
        worker.execute();
        window.setVisible(true);
        try {   
            this.searchResults = worker.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setSearchIndex(0);
        selectSearchResult();
    }

    void setSearchResults(java.util.List<Searcher.SearchResult> searchResults){
        this.searchResults = searchResults;
    }

    void setSearchIndex(int searchIndex) {
        this.searchIndex = searchIndex;
    }

    void prevSearchTerm() {
        if (hasTextChanged) {
            startSearch();
        } else {
            this.searchIndex--;
            selectSearchResult();
        }
    }

    void nextSearchTerm() {
        if (hasTextChanged) {
            startSearch();
        } else {
            this.searchIndex++;            
            selectSearchResult();
        }
    }

    void toggleRegex() {
        this.isRegex = !this.isRegex;
        for (AbstractButton button : registeredRegexToggles){
            button.setSelected(this.isRegex);
        }
    }

    void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    void registerRegexToggle(AbstractButton button){
        registeredRegexToggles.add(button);
    }

    public TextEditor() {
        super("Text Editor");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            setIconImage(ImageIO.read(getClass().getResource("/toolbarButtonGraphics/general/History24.gif")));        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.centerPanel = new CenterPanel(this);
        this.topPanel = new TopPanel(this);
        this.menuBar = new MenuBar(this);
        this.chooser = new JFileChooser();        
        this.chooser.setName("FileChooser");
        this.searcher = new Searcher();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 480);
        setLocationRelativeTo(null);
        
        setJMenuBar(menuBar);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        add(topPanel, c);

        c.weighty = 1.0;
        add(centerPanel, c);

        setVisible(true);
    }

    public static void main(final String[] args) {
        new TextEditor();
    }
}