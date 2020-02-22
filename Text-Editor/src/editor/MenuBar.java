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
        loadMenuItem.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Open16.gif")));
        loadMenuItem.addActionListener(e -> editor.selectAndLoadFile(loadMenuItem));
        fileMenu.add(loadMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveMenuItem.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Save16.gif")));
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
        searchMenu.setName("MenuSearch");
        searchMenu.setMnemonic(KeyEvent.VK_S);

        JMenuItem startMenuItem = new JMenuItem("Start search", KeyEvent.VK_S);     
        startMenuItem.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Find16.gif")));
        startMenuItem.setName("MenuStartSearch");
        startMenuItem.addActionListener(e -> editor.startSearch());
        searchMenu.add(startMenuItem);

        JMenuItem prevMenuItem = new JMenuItem("Previous search", KeyEvent.VK_P);   
        prevMenuItem.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/navigation/Back16.gif")));
        prevMenuItem.setName("MenuPreviousMatch");        
        prevMenuItem.addActionListener(e -> editor.prevSearchTerm());
        searchMenu.add(prevMenuItem);

        JMenuItem nextMenuItem = new JMenuItem("Next match", KeyEvent.VK_N);   
        nextMenuItem.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/navigation/Forward16.gif")));
        nextMenuItem.setName("MenuNextMatch");
        nextMenuItem.addActionListener(e -> editor.nextSearchTerm());
        searchMenu.add(nextMenuItem);

        JCheckBoxMenuItem regexMenuItem = new JCheckBoxMenuItem("Use Regular Expressions");
        regexMenuItem.setMnemonic(KeyEvent.VK_R);
        regexMenuItem.setName("MenuUseRegExp");
        regexMenuItem.addActionListener(e -> editor.toggleRegex());        
        editor.registerRegexToggle(regexMenuItem);
        searchMenu.add(regexMenuItem);

        add(searchMenu);
    }

    MenuBar(TextEditor editor) {
        this.editor = editor;

        addFileMenu();
        addSearchMenu();
    }
}
