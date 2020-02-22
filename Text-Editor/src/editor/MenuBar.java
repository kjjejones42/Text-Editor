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

        JMenuItem loadMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        loadMenuItem.setName("MenuLoad");        
        Icon i = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/general/Open16.gif"));
        loadMenuItem.setIcon(i);
        // loadMenuItem.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        loadMenuItem.addActionListener(e -> editor.loadFile());
        fileMenu.add(loadMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        Icon j = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/general/Save16.gif"));
        saveMenuItem.setIcon(j);
        // saveMenuItem.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        saveMenuItem.setName("MenuSave");
        saveMenuItem.addActionListener(e -> editor.saveFile());
        fileMenu.add(saveMenuItem);

        fileMenu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.setName("MenuExit");
        exitMenuItem.addActionListener(e -> editor.dispose());
        fileMenu.add(exitMenuItem);

        add(fileMenu);

    }

    private void addSearchMenu() {
        
        JMenu searchMenu = new JMenu("Search");
        searchMenu.setMnemonic(KeyEvent.VK_S);

        JMenuItem startMenuItem = new JMenuItem("Start search", KeyEvent.VK_S);        
        Icon i1 = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/general/Find16.gif"));
        startMenuItem.setIcon(i1);
        searchMenu.add(startMenuItem);

        JMenuItem prevMenuItem = new JMenuItem("Previous search", KeyEvent.VK_P);        
        Icon i2 = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/navigation/Back16.gif"));
        prevMenuItem.setIcon(i2);
        searchMenu.add(prevMenuItem);

        JMenuItem nextMenuItem = new JMenuItem("Next match", KeyEvent.VK_N);        
        Icon i3 = new ImageIcon(TextEditor.class.getResource("/toolbarButtonGraphics/navigation/Forward16.gif"));
        nextMenuItem.setIcon(i3);
        searchMenu.add(nextMenuItem);

        JMenuItem regexMenuItem = new JMenuItem("Use Regular Expressions", KeyEvent.VK_R);
        searchMenu.add(regexMenuItem);

        add(searchMenu);
    }

    MenuBar(TextEditor editor) {
        this.editor = editor;

        addFileMenu();
        addSearchMenu();
    }
}
