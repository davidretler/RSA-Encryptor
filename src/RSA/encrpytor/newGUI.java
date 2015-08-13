package RSA.encrpytor;

import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioButtonBuilder;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import RSA.encrpytor.Message.MessageEncryptedException;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class newGUI extends JFrame {
	
	private class NoKeySizeSelectedException extends Exception {};
	
    private RSA myRSA = new RSA();
    private BigInteger[] keys = new BigInteger[2];
    private final int RADIX = Character.MAX_RADIX;
    private BigInteger e = BigInteger.valueOf((1 << (1 << 4)) + 1);
    private Message message = new Message();
    private int keySize = 2048;
    private File keyImport;
    private String keyLocation;
	
	private final ButtonGroup KeySizeButtonGroup = new ButtonGroup();
	private JTextArea MessageTextArea;
	private JTextArea EncodedMessageTextArea;
	private JTextArea PublicKeyTextArea;
	private JTextArea PrivateKeyTextArea;
	private JTextArea RecipientKeyTextArea;
	public newGUI() {
		setPreferredSize(new Dimension(688, 400));
		setSize(new Dimension(688, 400));
		setVisible(true);
		setTitle("RSA Encryption Demo");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFi = new JMenu("File");
		menuBar.add(mnFi);
		
		JMenuItem mntmImportKey = new JMenuItem("Import Keys");
		mntmImportKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				openKeys();
			}
		});
		mnFi.add(mntmImportKey);
		
		JMenuItem mntmExportKey = new JMenuItem("Export Keys");
		mntmExportKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				saveKeys();
			}
		});
		mnFi.add(mntmExportKey);
		
		JSeparator separator = new JSeparator();
		mnFi.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open Message");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				openMessage();
			}
		});
		mnFi.add(mntmNewMenuItem);
		
		JMenuItem mntmSaveMessage = new JMenuItem("Save Message");
		mntmSaveMessage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				saveMessage();
			}
		});
		mnFi.add(mntmSaveMessage);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				 new aboutPage().setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		
		JMenu mnKeys = new JMenu("Keys");
		menuBar.add(mnKeys);
		
		JMenuItem mntmGenerateKeys = new JMenuItem("Generate Keys");
		mntmGenerateKeys.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				keyButtonActionPerformed();
			}
		});
		mnKeys.add(mntmGenerateKeys);
		
		JMenu mnKeySize = new JMenu("Key Size");
		mnKeys.add(mnKeySize);
		
		JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem("512");
		KeySizeButtonGroup.add(radioButtonMenuItem);
		mnKeySize.add(radioButtonMenuItem);
		
		JRadioButtonMenuItem radioButtonMenuItem_1 = new JRadioButtonMenuItem("1024");
		KeySizeButtonGroup.add(radioButtonMenuItem_1);
		mnKeySize.add(radioButtonMenuItem_1);
		
		JRadioButtonMenuItem radioButtonMenuItem_2 = new JRadioButtonMenuItem("2048");
		radioButtonMenuItem_2.setSelected(true);
		KeySizeButtonGroup.add(radioButtonMenuItem_2);
		mnKeySize.add(radioButtonMenuItem_2);
		
		JRadioButtonMenuItem radioButtonMenuItem_3 = new JRadioButtonMenuItem("4096");
		KeySizeButtonGroup.add(radioButtonMenuItem_3);
		mnKeySize.add(radioButtonMenuItem_3);
		
		JMenu mnMessage = new JMenu("Message");
		menuBar.add(mnMessage);
		
		JMenuItem mntmSignMessage = new JMenuItem("Sign Message");
		mnMessage.add(mntmSignMessage);
		
		JMenuItem mntmCheckSignature = new JMenuItem("Check Signature");
		mnMessage.add(mntmCheckSignature);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JPanel MainPanel = new JPanel();
		getContentPane().add(MainPanel);
		MainPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel LeftPanel = new JPanel();
		LeftPanel.setAlignmentX(0.0f);
		MainPanel.add(LeftPanel);
		LeftPanel.setLayout(null);
		
		JPanel MessagePanel = new JPanel();
		MessagePanel.setBounds(new Rectangle(0, 0, 334, 151));
		MessagePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		MessagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		MessagePanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		LeftPanel.add(MessagePanel);
		MessagePanel.setLayout(null);
		
		JPanel MessageLabelPanel = new JPanel();
		MessageLabelPanel.setBounds(12, 0, 336, 25);
		FlowLayout fl_MessageLabelPanel = (FlowLayout) MessageLabelPanel.getLayout();
		fl_MessageLabelPanel.setAlignment(FlowLayout.LEFT);
		MessagePanel.add(MessageLabelPanel);
		
		JLabel MessageLabel = new JLabel("Message");
		MessageLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		MessageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		MessageLabelPanel.add(MessageLabel);
		
		JPanel MessageTextPanel = new JPanel();
		MessageTextPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		MessageTextPanel.setBounds(0, 26, 334, 113);
		MessagePanel.add(MessageTextPanel);
		MessageTextPanel.setLayout(new BoxLayout(MessageTextPanel, BoxLayout.X_AXIS));
		
		JScrollPane MessageScrollPane = new JScrollPane();
		MessageScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		MessageTextPanel.add(MessageScrollPane);
		
		MessageTextArea = new JTextArea();
		MessageTextArea.setLineWrap(true);
		MessageTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		MessageScrollPane.setViewportView(MessageTextArea);
		
		JPanel MessageButtonsPanel = new JPanel();
		MessageButtonsPanel.setBounds(10, 138, 321, 55);
		MessageButtonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		MessageButtonsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		MessageButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		LeftPanel.add(MessageButtonsPanel);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				encryptButtonActionPerformed();
			}
		});
		MessageButtonsPanel.add(btnEncrypt);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				decryptButtonActionPerformed();
			}
		});
		MessageButtonsPanel.add(btnDecrypt);
		
		JPanel EncodedPanel = new JPanel();
		EncodedPanel.setBounds(0, 192, 334, 159);
		EncodedPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		EncodedPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		EncodedPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		LeftPanel.add(EncodedPanel);
		EncodedPanel.setLayout(null);
		
		JPanel EncodedMessagePanel = new JPanel();
		EncodedMessagePanel.setBounds(12, 0, 336, 25);
		FlowLayout flowLayout = (FlowLayout) EncodedMessagePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		EncodedPanel.add(EncodedMessagePanel);
		
		JLabel EncodedMessageLabel = new JLabel("Encoded Message");
		EncodedMessageLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		EncodedMessageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		EncodedMessagePanel.add(EncodedMessageLabel);
		
		JPanel EncodedMessageTextPanel = new JPanel();
		EncodedMessageTextPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		EncodedMessageTextPanel.setBounds(0, 25, 334, 122);
		EncodedPanel.add(EncodedMessageTextPanel);
		EncodedMessageTextPanel.setLayout(new BoxLayout(EncodedMessageTextPanel, BoxLayout.X_AXIS));
		
		JScrollPane EncodedMessageTextScrollPane = new JScrollPane();
		EncodedMessageTextScrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		EncodedMessageTextPanel.add(EncodedMessageTextScrollPane);
		
		EncodedMessageTextArea = new JTextArea();
		EncodedMessageTextArea.setLineWrap(true);
		EncodedMessageTextArea.setBorder(new EmptyBorder(0, 0, 0, 0));
		EncodedMessageTextScrollPane.setViewportView(EncodedMessageTextArea);
		
		JPanel RightPanel = new JPanel();
		MainPanel.add(RightPanel);
		RightPanel.setLayout(null);
		
		JPanel PersonalKeysPanel = new JPanel();
		PersonalKeysPanel.setBounds(0, 0, 339, 191);
		RightPanel.add(PersonalKeysPanel);
		PersonalKeysPanel.setLayout(new BoxLayout(PersonalKeysPanel, BoxLayout.X_AXIS));
		
		JPanel PublicKeyPanel = new JPanel();
		PersonalKeysPanel.add(PublicKeyPanel);
		PublicKeyPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 169, 25);
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		PublicKeyPanel.add(panel);
		
		JLabel lblPublicKey = new JLabel("Public Key");
		panel.add(lblPublicKey);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(0, 27, 169, 163);
		PublicKeyPanel.add(scrollPane);
		
		PublicKeyTextArea = new JTextArea();
		PublicKeyTextArea.setLineWrap(true);
		scrollPane.setViewportView(PublicKeyTextArea);
		
		JPanel PrivateKeyPanel = new JPanel();
		PrivateKeyPanel.setBorder(new EmptyBorder(0, 2, 0, 0));
		PersonalKeysPanel.add(PrivateKeyPanel);
		PrivateKeyPanel.setLayout(null);
		
		JPanel PrivateKeyLabelPanel = new JPanel();
		PrivateKeyLabelPanel.setBounds(2, 0, 167, 25);
		FlowLayout flowLayout_2 = (FlowLayout) PrivateKeyLabelPanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		PrivateKeyPanel.add(PrivateKeyLabelPanel);
		
		JLabel lblPrivateKey = new JLabel("Private Key");
		PrivateKeyLabelPanel.add(lblPrivateKey);
		
		JPanel PrivateKeyTextPanel = new JPanel();
		PrivateKeyTextPanel.setBounds(2, 27, 167, 163);
		PrivateKeyPanel.add(PrivateKeyTextPanel);
		PrivateKeyTextPanel.setLayout(new BoxLayout(PrivateKeyTextPanel, BoxLayout.X_AXIS));
		
		JScrollPane PrivateKeyTextPane = new JScrollPane();
		PrivateKeyTextPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		PrivateKeyTextPanel.add(PrivateKeyTextPane);
		
		PrivateKeyTextArea = new JTextArea();
		PrivateKeyTextArea.setLineWrap(true);
		PrivateKeyTextPane.setViewportView(PrivateKeyTextArea);
		
		JPanel RecipientKeysPanel = new JPanel();
		RecipientKeysPanel.setBounds(0, 191, 339, 160);
		RightPanel.add(RecipientKeysPanel);
		RecipientKeysPanel.setLayout(null);
		
		JPanel RecipientKeyLabel = new JPanel();
		RecipientKeyLabel.setBounds(0, 0, 339, 23);
		RecipientKeysPanel.add(RecipientKeyLabel);
		RecipientKeyLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblRecipientPublicKey = new JLabel("Recipient Public Key");
		RecipientKeyLabel.add(lblRecipientPublicKey);
		
		JPanel RecipientKeyTextPanel = new JPanel();
		RecipientKeyTextPanel.setBounds(0, 26, 338, 122);
		RecipientKeysPanel.add(RecipientKeyTextPanel);
		RecipientKeyTextPanel.setLayout(new BoxLayout(RecipientKeyTextPanel, BoxLayout.X_AXIS));
		
		JScrollPane RecipientKeyPane = new JScrollPane();
		RecipientKeyPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		RecipientKeyTextPanel.add(RecipientKeyPane);
		
		RecipientKeyTextArea = new JTextArea();
		RecipientKeyTextArea.setLineWrap(true);
		RecipientKeyPane.setViewportView(RecipientKeyTextArea);
	}
	
	
	/**
	 * Encrypts the message in the text area, writing it to the other text area
	 * @param evt
	 */
	private void encryptButtonActionPerformed() {
	    // TODO add your handling code here:
	    String text = MessageTextArea.getText();
	    message = new Message(text);
	    if(!this.RecipientKeyTextArea.getText().equals("")) {
	    	keys[0] = new BigInteger(this.RecipientKeyTextArea.getText(),RADIX);
	    	message.Encrypt(keys[0]);
	    	this.EncodedMessageTextArea.setText(message.toInt().toString(RADIX));
	    	displayMessage();
	    }
	    else {
	    	JOptionPane.showMessageDialog(null, "You need to enter the recipient's public key to encrypt.","Error", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    
	    
	}
	
	/**
	 * Decrypts the message
	 * @param evt
	 */
	private void decryptButtonActionPerformed() {
	    String text = this.EncodedMessageTextArea.getText();
	    BigInteger textInt = new BigInteger(text,RADIX);
	    message = new Message(textInt);
	    if(!this.PublicKeyTextArea.getText().equals("") && !this.PrivateKeyTextArea.getText().equals("")) {
	    	keys[0] = new BigInteger(this.PublicKeyTextArea.getText(), RADIX);
	    	keys[1] = new BigInteger(this.PrivateKeyTextArea.getText(), RADIX); 
	    	message.Decrypt(keys[0], keys[1]);
	        displayMessage();
	    } else {
	    	JOptionPane.showMessageDialog(null, "You need to enter a public and private keypair to decrypt.","Error", JOptionPane.ERROR_MESSAGE);
	    }
	    
	}
	
	/**
	 * Generates keys
	 * @param evt
	 */
	private void keyButtonActionPerformed() {
	    // TODO add your handling code here:
	    try {
	    	keySize = getKeySize();
	    } catch (NoKeySizeSelectedException ex){
	    	JOptionPane.showMessageDialog(null, "Please select a key size before generating key.", "Error", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    keys = myRSA.generateKey(keySize/2);
	    this.PublicKeyTextArea.setText(keys[0].toString(RADIX));
	    this.PrivateKeyTextArea.setText(keys[1].toString(RADIX));
	}
		
	/**
	 * Determines the key size
	 * @return - Key size
	 * @throws NoKeySizeSelectedException 
	 */
	private int getKeySize() throws NoKeySizeSelectedException {
		Enumeration<AbstractButton> buttons = this.KeySizeButtonGroup.getElements();
		
		while(buttons.hasMoreElements()) {
			AbstractButton button = buttons.nextElement();
			if(button.isSelected()) {
				return Integer.parseInt(button.getText());
			}
		}
		
		throw new NoKeySizeSelectedException();
	}
	
	/**
     * Saves the message to a file, using a file output stram
     * @param evt
     */
    private void saveMessage() {
        JFileChooser jFileChooser1 = new JFileChooser();
        int returnVal = jFileChooser1.showSaveDialog(this);
        //if the user hit "save"
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String fileName = jFileChooser1.getSelectedFile().toString();
        	if(!fileName.endsWith(".msg")) {
        		fileName = fileName + ".msg"; //add extension if not present
        	}
        	
        	try {
        		 FileOutputStream myFOS = new FileOutputStream(fileName);
        		 ObjectOutputStream myOOS = new ObjectOutputStream(myFOS);
        		 myOOS.writeObject(message);
        		 System.out.println("Saved message to " + fileName);
        		 myOOS.close();
        		 myFOS.close();
        	} catch (IOException ex){
        		Logger.getLogger(rsaGUI.class.getName()).log(Level.SEVERE, null, ex);
        		JOptionPane.showMessageDialog(null, "Saving message to file failed.", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        }
    }
    
    /**
     * Open the message
     * @param evt
     */
    private void openMessage() {
    	JFileChooser jFileChooser1 = new JFileChooser();
        int returnVal = jFileChooser1.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String fileName = jFileChooser1.getSelectedFile().toString();
        	
        	try {
        		FileInputStream myFIS = new FileInputStream(fileName);
        		ObjectInputStream myOIS = new ObjectInputStream(myFIS);
        		message = (Message) myOIS.readObject();
        		myFIS.close();
        		myOIS.close();
        		displayMessage();
        	} catch (IOException ex) {
        		Logger.getLogger(rsaGUI.class.getName()).log(Level.SEVERE, null, ex);
        		JOptionPane.showMessageDialog(null, "Reading message from file failed.", "Error", JOptionPane.ERROR_MESSAGE);
        	} catch (ClassNotFoundException ex) {
        		Logger.getLogger(rsaGUI.class.getName()).log(Level.SEVERE, null, ex);
        		JOptionPane.showMessageDialog(null, "An unexpected error occured.", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        }
    }
    
    /**
     * Save key file, prompting the user to choose the location
     * @param evt
     */
    private void saveKeys() {
        Writer writer;
        JFileChooser jFileChooser1 = new JFileChooser();
        jFileChooser1.setFileFilter(new FileNameExtensionFilter("Text File (*.txt)","txt"));
        jFileChooser1.addChoosableFileFilter(new FileNameExtensionFilter("Key File (*.key)","key"));
        int returnVal = jFileChooser1.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            String fileName = jFileChooser1.getSelectedFile().toString();
            
            //Thank you to user59600 on StackOverflow for coming with this method of obtaining the extention from the FileFilter.
            String ext = jFileChooser1.getFileFilter().toString().replaceFirst(".*extensions=\\[(.*)]]", ".$1").replaceFirst(".*AcceptAllFileFilter.*", "");
            
            
            //adds the selected extension to the filename
            //only adds extension if extension isn't already there. Only works with 3 letter extensions currently
            if(!( fileName.endsWith(ext) )){
              fileName = fileName + ext;  
            }
            System.out.println(fileName);
            try {
                writer = new FileWriter(fileName);
                String toWrite = this.PublicKeyTextArea.getText();
                toWrite = toWrite + "\n";
                toWrite = toWrite + this.PrivateKeyTextArea.getText();
                System.out.println(toWrite);
                writer.write(toWrite);
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(rsaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
       
    }
    
    /**
     * Open key file
     * @param evt
     */
    private void openKeys() {
       
        JFileChooser jFileChooser1 = new JFileChooser();
        int returnVal = jFileChooser1.showOpenDialog(this);
        //Opens the Text file with keys and uses the first two lines as the public and private
        //key respectively
        if (returnVal == JFileChooser.APPROVE_OPTION){
            Reader reader=null;
            System.out.println("Open");
            keyImport = jFileChooser1.getSelectedFile();
            keyLocation = keyImport.toString();
            try {
                reader = new FileReader(keyLocation);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(rsaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader file = new BufferedReader(reader);
            
            String line;
            int i=0;
            try {
                while(((line = file.readLine()) != null) && i<2){                    
                    keys[i] = new BigInteger(line,RADIX);
                    i++;
                }
                this.PublicKeyTextArea.setText(keys[0].toString(RADIX));
                this.PrivateKeyTextArea.setText(keys[1].toString(RADIX));
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(rsaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }  
    }


    /**
     * Displays the message, updating the text areas
     */
    private void displayMessage() {
    	try {
        	this.MessageTextArea.setText(message.getMessage());
        } catch (MessageEncryptedException ex) {
        	this.MessageTextArea.setText("Message Encrypted");
        }
    	this.EncodedMessageTextArea.setText(message.toInt().toString(RADIX));
    }
   
	
}

