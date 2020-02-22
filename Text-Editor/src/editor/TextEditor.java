package editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
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
    private boolean isRegex;
    private final java.util.List<AbstractButton> registeredRegexToggles = new ArrayList<>();
    private File file;

    void loadFile() {
        try {
            String fileContent;
            if (file == null || !file.exists()) {
                fileContent = "";
            } else {
                byte[] bytes = Files.readAllBytes(file.toPath());
                fileContent = new String(bytes, StandardCharsets.UTF_8);
                // topPanel.setFilenameField(file.getAbsolutePath());
                centerPanel.setText(fileContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void selectAndLoadFile(Component obj) {        
        chooser.setVisible(true);
        File file = null;
        if (chooser.showOpenDialog(obj) == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();        
        }         
        chooser.setVisible(false);       
        if (file != null && file.exists()) {
            setFileObj(file);  
            loadFile();
        }    
    }

    void saveFile() {
        try {
            if (file == null) {
                return;
            }
            byte[] bytes = centerPanel.getText().getBytes(StandardCharsets.UTF_8);
            Files.write(file.toPath(), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setFileObj(File fileName) {
        this.file = fileName;
    }

    void startSearch() {
        System.out.println("startSearch");
        // TODO
    }

    void prevSearchTerm() {
        System.out.println("prevSearchTerm");
        // TODO
    }

    void nextSearchTerm() {
        System.out.println("nextSearchTerm");
        // TODO
    }

    void toggleRegex() {
        System.out.println("toggleRegex");
        this.isRegex = !this.isRegex;
        for (AbstractButton button : registeredRegexToggles){
            button.setSelected(this.isRegex);
        }
    }

    void setSearchTerm(String searchTerm) {
        System.out.println("searchTerm \"" + searchTerm +"\"");
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