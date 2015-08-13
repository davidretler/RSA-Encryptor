package RSA.encrpytor;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;

public class newGUI extends JFrame {
	private final ButtonGroup KeySizeButtonGroup = new ButtonGroup();
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
		mnFi.add(mntmImportKey);
		
		JMenuItem mntmExportKey = new JMenuItem("Export Keys");
		mnFi.add(mntmExportKey);
		
		JSeparator separator = new JSeparator();
		mnFi.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open Message");
		mnFi.add(mntmNewMenuItem);
		
		JMenuItem mntmSaveMessage = new JMenuItem("Save Message");
		mnFi.add(mntmSaveMessage);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		JMenu mnKeys = new JMenu("Keys");
		menuBar.add(mnKeys);
		
		JMenuItem mntmGenerateKeys = new JMenuItem("Generate Keys");
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
		
		JTextArea MessageTextArea = new JTextArea();
		MessageTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		MessageScrollPane.setViewportView(MessageTextArea);
		
		JPanel MessageButtonsPanel = new JPanel();
		MessageButtonsPanel.setBounds(10, 138, 321, 55);
		MessageButtonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		MessageButtonsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		MessageButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		LeftPanel.add(MessageButtonsPanel);
		
		JButton btnEncrypt = new JButton("Encrypt");
		MessageButtonsPanel.add(btnEncrypt);
		
		JButton btnDecrypt = new JButton("Decrypt");
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
		EncodedMessageTextScrollPane.setBorder(null);
		EncodedMessageTextPanel.add(EncodedMessageTextScrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		EncodedMessageTextScrollPane.setViewportView(textArea);
		
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
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		
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
		
		JTextArea PrivateKeyTextArea = new JTextArea();
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		RecipientKeyTextPanel.add(scrollPane_1);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_1.setViewportView(textArea_2);
	}
}
