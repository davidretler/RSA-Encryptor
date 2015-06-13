package RSA.encrpytor;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import RSA.encrpytor.rsaGUI;

/**
 * 	Driver Class for the GUI
 */
public class GUIDriver {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		rsaGUI gui = new rsaGUI();
        
        gui.setVisible(true);
		
	}
}
