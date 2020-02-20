package editor;

import javax.swing.*;
import java.awt.event.*;

class MenuBar extends JMenuBar {

    static final long serialVersionUID = 1;

    private final TextEditor editor;

    private void addFileMenu() {

        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.setName("MenuLoad");
        loadMenuItem.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        loadMenuItem.addActionListener(e -> editor.loadFile());
        fileMenu.add(loadMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        saveMenuItem.setName("MenuSave");
        saveMenuItem.addActionListener(e -> editor.saveFile());
        fileMenu.add(saveMenuItem);

        fileMenu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");
        exitMenuItem.addActionListener(e -> editor.dispose());
        fileMenu.add(exitMenuItem);

        add(fileMenu);

    }

    private void addSearchMenu() {
        
        JMenu searchMenu = new JMenu("Search");

        JMenuItem startMenuItem = new JMenuItem("Start search");
        searchMenu.add(startMenuItem);

        JMenuItem prevMenuItem = new JMenuItem("Previous search");
        searchMenu.add(prevMenuItem);

        JMenuItem nextMenuItem = new JMenuItem("Next match");
        searchMenu.add(nextMenuItem);

        JMenuItem regexMenuItem = new JMenuItem("Use Regular Expressions");
        searchMenu.add(regexMenuItem);

        add(searchMenu);
    }

    MenuBar(TextEditor editor) {
        this.editor = editor;
        
        addFileMenu();
        addSearchMenu();
    }
}
