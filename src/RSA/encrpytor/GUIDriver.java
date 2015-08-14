package RSA.encrpytor;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import RSA.encrpytor.newGUI;

/**
 * Driver Class for the GUI
 */
public class GUIDriver {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        newGUI gui = new newGUI();
        
        //center UI and make visible
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
        
    }
}
