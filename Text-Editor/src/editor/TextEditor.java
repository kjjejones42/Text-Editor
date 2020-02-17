package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class TextEditor extends JFrame {

    static final long serialVersionUID = 1;

    static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().contains("windows");

    CenterPanel centerPanel = new CenterPanel(this);
    TopPanel topPanel = new TopPanel(this);
    File file;

    private void addMenu(){

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.setName("MenuLoad");
        loadMenuItem.addActionListener(e -> loadFile()); 
        fileMenu.add(loadMenuItem);
        
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setName("MenuSave");
        saveMenuItem.addActionListener(e -> saveFile()); 
        fileMenu.add(saveMenuItem);

        fileMenu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");
        exitMenuItem.addActionListener(e -> dispose());                
        fileMenu.add(exitMenuItem);
        
    }

    private void addChildComponents(){             
        setLayout(new GridBagLayout()); 

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0; 
        c.gridy = 1;       
        add(topPanel, c);
        
        c.gridy = 2;
        c.weighty = 1.0;        
        add(centerPanel, c);

    }
    
    void loadFile() {
        try {
            String fileContent;
            if (!file.exists()) {
                fileContent = "File does not exist.";
            } else {
                byte[] bytes = Files.readAllBytes(file.toPath());
                fileContent = new String(bytes, StandardCharsets.UTF_8);
            }
            topPanel.setFilenameField(file.getAbsolutePath());
            centerPanel.setText(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void saveFile() {
        try {
            byte[] bytes = centerPanel.getText().getBytes(StandardCharsets.UTF_8);            
            Path path = file.toPath();
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    File getFileObj(){
        return this.file;
    }

    void setFileObj(File fileName){
        this.file = fileName;
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

        addMenu();
        addChildComponents();

        setVisible(true);
        centerPanel.requestFocusInWindow();
    }

    public static void main(final String[] args) {
        new TextEditor();
    }
}