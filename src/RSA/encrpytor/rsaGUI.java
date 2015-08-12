/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA.encrpytor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.*;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.TextArea;
import java.awt.Component;



/**
 *
 * @author Chris Etler, David Etler
 */
public class rsaGUI extends javax.swing.JFrame {

    private RSA myRSA = new RSA();
    private BigInteger[] keys = new BigInteger[2];
    private final int RADIX = Character.MAX_RADIX;
    private BigInteger e = BigInteger.valueOf((1 << (1 << 4)) + 1);
    private Message message = new Message();
    private int keySize;
    private File keyImport;
    private String keyLocation;
    
    
    
   
    /**
     * Creates new form rsaGUI
     */
    public rsaGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        messageLabel = new javax.swing.JLabel();
        outputLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setAlignmentX(Component.LEFT_ALIGNMENT);
        messageTextArea = new javax.swing.JTextArea();
        messageTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane2.setAlignmentX(Component.LEFT_ALIGNMENT);
        outputTextArea = new javax.swing.JTextArea();
        outputTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        encryptButton = new javax.swing.JButton();
        decryptButton = new javax.swing.JButton();
        keysLabel = new javax.swing.JLabel();
        publicKeyLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane3.setAlignmentX(Component.LEFT_ALIGNMENT);
        publicKeyTextArea = new javax.swing.JTextArea();
        publicKeyTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane4.setAlignmentX(Component.LEFT_ALIGNMENT);
        privateKeyTextArea = new javax.swing.JTextArea();
        privateKeyTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        privateKeyLabel = new javax.swing.JLabel();
        keyButton = new javax.swing.JButton();
        BitCount = new javax.swing.JComboBox();
        BitCount.setAlignmentX(Component.LEFT_ALIGNMENT);
        jMenuBar1 = new javax.swing.JMenuBar();
        ImportKeysMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RSA Encryptor");
        setMinimumSize(new java.awt.Dimension(640, 480));

        messageLabel.setText("Plaintext Message:");

        outputLabel.setText("Encrypted Message:");

        messageTextArea.setColumns(20);
        messageTextArea.setLineWrap(true);
        messageTextArea.setRows(5);
        jScrollPane1.setViewportView(messageTextArea);
        outputTextArea.setColumns(20);
        outputTextArea.setLineWrap(true);
        outputTextArea.setRows(5);
        jScrollPane2.setViewportView(outputTextArea);

        encryptButton.setText("Encrypt");
        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });

        decryptButton.setText("Decrypt");
        decryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptButtonActionPerformed(evt);
            }
        });

        keysLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        keysLabel.setText("Bits:");

        publicKeyLabel.setText("Public Key:");

        publicKeyTextArea.setEditable(false);
        publicKeyTextArea.setColumns(20);
        publicKeyTextArea.setLineWrap(true);
        publicKeyTextArea.setRows(4);
        jScrollPane3.setViewportView(publicKeyTextArea);

        privateKeyTextArea.setEditable(false);
        privateKeyTextArea.setColumns(20);
        privateKeyTextArea.setLineWrap(true);
        privateKeyTextArea.setRows(4);
        jScrollPane4.setViewportView(privateKeyTextArea);

        privateKeyLabel.setText("Private  Key:");

        keyButton.setText("Generate!");
        keyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyButtonActionPerformed(evt);
            }
        });

        BitCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "32", "128", "512", "1024", "2048", "4096" }));
        BitCount.setSelectedIndex(4);

        ImportKeysMenu.setText("File");
        ImportKeysMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportKeysMenuActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Import Keys From File");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseReleased(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        ImportKeysMenu.add(jMenuItem2);

        jMenuItem3.setText("Save Keys to File");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseReleased(evt);
            }
        });
        ImportKeysMenu.add(jMenuItem3);

        jMenuBar1.add(ImportKeysMenu);

        jMenu2.setText("About");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenu2MouseReleased(evt);
            }
        });

        jMenuItem1.setText("About RSA Encryptor");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseReleased(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);
        
        lblRecipientPublicKey = new JLabel("Recipient Public Key");
        
        scrollPane = new JScrollPane();
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea textArea = new JTextArea();
        textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setViewportView(textArea);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(22)
        			.addComponent(messageLabel)
        			.addGap(264)
        			.addComponent(keysLabel)
        			.addGap(12)
        			.addComponent(BitCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(22)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(10)
        					.addComponent(encryptButton)
        					.addGap(12)
        					.addComponent(decryptButton)))
        			.addGap(26)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(publicKeyLabel)
        				.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
        				.addComponent(privateKeyLabel)
        				.addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(434)
        			.addComponent(keyButton))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(22)
        			.addComponent(outputLabel)
        			.addGap(256)
        			.addComponent(lblRecipientPublicKey))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(22)
        			.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
        			.addGap(26)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(12)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(5)
        					.addComponent(messageLabel))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(5)
        					.addComponent(keysLabel))
        				.addComponent(BitCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(2)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(4)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
        					.addGap(6)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(encryptButton)
        						.addComponent(decryptButton)))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(publicKeyLabel)
        					.addGap(10)
        					.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        					.addGap(6)
        					.addComponent(privateKeyLabel)
        					.addGap(12)
        					.addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
        			.addGap(6)
        			.addComponent(keyButton)
        			.addGap(6)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(outputLabel)
        				.addComponent(lblRecipientPublicKey))
        			.addGap(6)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(6)
        					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))))
        );
        getContentPane().setLayout(groupLayout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //encrypts the message in the text area
    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptButtonActionPerformed
        // TODO add your handling code here:
        String text = messageTextArea.getText();
        message = new Message(text);
        if(keys[0] != null) {
        	message.Encrypt(keys[0]);
        	outputTextArea.setText(message.toInt().toString(RADIX));
        	messageTextArea.setText(message.toString());
        }
        else {
        	JOptionPane.showMessageDialog(null, "You need to enter a public key to encrypt.","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_encryptButtonActionPerformed

    //decrypts the message
    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptButtonActionPerformed
        String text = outputTextArea.getText();
        BigInteger textInt = new BigInteger(text,RADIX);
        message = new Message(textInt);
        if(keys[0] != null && keys[1] != null) {
        	message.Decrypt(keys[0], keys[1]);
            messageTextArea.setText(message.toString());
        } else {
        	JOptionPane.showMessageDialog(null, "You need to enter a public and private keypair to decrypt.","Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_decryptButtonActionPerformed

    //generates keys and displays them in the text areas
    private void keyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyButtonActionPerformed
        // TODO add your handling code here:
        keySize = Integer.parseInt(BitCount.getSelectedItem().toString());
        keys = myRSA.generateKey(keySize/2);
        publicKeyTextArea.setText(keys[0].toString(RADIX));
        privateKeyTextArea.setText(keys[1].toString(RADIX));
    }//GEN-LAST:event_keyButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ImportKeysMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportKeysMenuActionPerformed
        // TODO add your handling code here:
        //FileSelect fileSel = new FileSelect();
        new FileSelect().setVisible(true);
        
        
    }//GEN-LAST:event_ImportKeysMenuActionPerformed

    private void jMenuItem2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseReleased
       
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
                publicKeyTextArea.setText(keys[0].toString(RADIX));
                privateKeyTextArea.setText(keys[1].toString(RADIX));
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(rsaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }  
    }//GEN-LAST:event_jMenuItem2MouseReleased

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseReleased
        // TODO add your handling code here:
       new aboutPage().setVisible(true);
    }//GEN-LAST:event_jMenu2MouseReleased

    private void jMenuItem1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseReleased
        // TODO add your handling code here:
        new aboutPage().setVisible(true);
    }//GEN-LAST:event_jMenuItem1MouseReleased

    private void jMenuItem3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseReleased
        // TODO add your handling code here:
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
            if(!( fileName.substring(fileName.length()-4,fileName.length()).equals(ext) )){
              fileName = fileName + ext;  
            }
            System.out.println(fileName);
            try {
                writer = new FileWriter(fileName);
                String toWrite = publicKeyTextArea.getText();
                toWrite = toWrite + "\n";
                toWrite = toWrite + privateKeyTextArea.getText();
                System.out.println(toWrite);
                writer.write(toWrite);
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(rsaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
       
    }//GEN-LAST:event_jMenuItem3MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(rsaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(rsaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(rsaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(rsaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new rsaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox BitCount;
    private javax.swing.JMenu ImportKeysMenu;
    private javax.swing.JButton decryptButton;
    private javax.swing.JButton encryptButton;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton keyButton;
    private javax.swing.JLabel keysLabel;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JLabel privateKeyLabel;
    private javax.swing.JTextArea privateKeyTextArea;
    private javax.swing.JLabel publicKeyLabel;
    private javax.swing.JTextArea publicKeyTextArea;
    private JLabel lblRecipientPublicKey;
    private JScrollPane scrollPane;
}
