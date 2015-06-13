/*
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */
package RSA.encrpytor;

import java.math.BigInteger;
import java.util.Scanner;
import java.io.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Driver for the RSA encryption class
 * <p>
 * Command line interface for generating 2048-bit RSA keys, encrypting messages,
 * and decrypting messages. Support for inputing a public key or retrieving
 * one's public/private keys from a file. Support for encrypting a large
 * messagefile.
 * <p>
 * Keys are stored in "key.txt" and messages in "message.txt". Both in the same
 * directory as the program.
 *
 *
 * @author David Etler
 * @author Chris Etler
 *
 */
public class Euclid {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        

    	
    	Scanner scan = new Scanner(System.in);
        //File to say the key
        File keyFile = new File("key.txt");
        BufferedReader keyReader;
        PrintWriter keyWriter;
        //File to save the message
        File messageFile = new File("message.txt");
        BufferedReader messageReader;
        PrintWriter messageWriter;
        String option = "";
        //Value to use as public exponent
        BigInteger e = BigInteger.valueOf((1 << 16) + 1);
        BigInteger[] key;
        //To store public key
        BigInteger pq;
        //To store pivate key
        BigInteger d;
        //To store plaintxt as integer
        BigInteger M;
        //To store cyphertext as intger
        BigInteger C;
        //Message paintext as string
        String messageText;
        //To convert to and from chareter represntation of integers
        final int RADIX = Character.MAX_RADIX;
        //Convert string to and from integer
        
        
        Message textConverter = new Message();
        final String COMMANDS = "e  -\tEncrypt\n"
                + "d  -\tDecrypt\n"
                + "k  -\tGenerate Key and write to file\n"
                + "q  -\tQuit\n"
                + "o  -\tOptions\n"
                + "ef -\tEncrypt with key from file\n"
                + "df -\tDecrypt with key from file\n"
                + "rk -\tRead keys from file\n"
                + "wm -\tWrite a message to the file\n"
                + "em -\tEncrypt the message file with the key file\n"
                + "dm -\tDecypt the message file with the key file\n"
                + "rm -\tRead the message file";
                

        System.out.println("RSA Message Ecnryption/Decryption Shell -- 'o' for options.");
        //Print options
        System.out.println(COMMANDS);
        //Key going until user wants to quit
        while (!option.equals("q")) {
            System.out.print("Option: ");
            option = scan.next();
            scan.nextLine();
            option = option.toLowerCase();
            switch (option) {
                case "q":
                    //Closes the program
                    System.out.println("Closing\n");
                    return;
                case "o":
                    //Prints the options
                    System.out.println(COMMANDS);
                    break;
                case "k":
                    //Generates two prime numbers
                    //Product of numbers is public key
                    //Modular inverse of e to the product is the private key
                    //Encrpytion relies on the modular inverse being hard to find
                    System.out.println("Generating keys...");
                    keyWriter = new PrintWriter(keyFile, "UTF-8");
                    key = RSA.generateKey();
                    pq = key[0];
                    d = key[1];
                    //pq = p.multiply(q);
                    //d = RSA.decryptionKey(p, q);
                    keyWriter.println(pq.toString(RADIX));
                    keyWriter.println(d.toString(RADIX));
                    keyWriter.close();
                    System.out.println("Keys generated and saved to file.");
                    break;
                case "e":
                    //Prompts the user for the public key
                    //Promprs the user for a message
                    //Returns the encypted message
                    System.out.print("Enter public key: ");
                    pq = new BigInteger(scan.next(), RADIX);
                    scan.nextLine();
                    System.out.print("Enter message: ");
                    messageText = scan.nextLine();
                    M = textConverter.toInt(messageText);
                    C = RSA.encrypt(M, e, pq);
                    System.out.println("Encrypted: " + C.toString(RADIX));
                    break;
                case "d":
                    //Prompts the user for a public key
                    //Prompts the user for the corresspoding private key
                    //Prompts the user for the encrpyted message
                    //Decrypts and prints the message
                    //Both public and private keys are necessary
                    System.out.print("Enter public key: ");
                    pq = new BigInteger(scan.next(), RADIX);
                    System.out.print("Enter private key: ");
                    d = new BigInteger(scan.next(), RADIX);
                    System.out.print("Enter encrypted message: ");
                    C = new BigInteger(scan.next(), RADIX);
                    M = RSA.decrypt(C, d, pq);
                    System.out.println("Decrypted message: " + textConverter.toString(M));
                    break;
                case "ef":
                    //Uses the key from the file for encryption
                    //First key is the public key
                    //Then asks for a message and prints it
                    keyReader = new BufferedReader(new FileReader(keyFile));
                    pq = new BigInteger(keyReader.readLine(), RADIX);
                    System.out.print("Enter message: ");
                    messageText = scan.nextLine();
                    M = textConverter.toInt(messageText);
                    C = RSA.encrypt(M, e, pq);
                    System.out.println("Encrypted: " + C.toString(RADIX));
                    break;
                case "df":
                    //Decrypts using both keys from the file
                    //First is the public, second is the private
                    //Delimited by newline
                    keyReader = new BufferedReader(new FileReader(keyFile));
                    pq = new BigInteger(keyReader.readLine(), RADIX);
                    d = new BigInteger(keyReader.readLine(), RADIX);
                    System.out.print("Enter encrypted message: ");
                    C = new BigInteger(scan.next(), RADIX);
                    M = RSA.decrypt(C, d, pq);
                    System.out.println("Decrypted message: " + textConverter.toString(M));
                    break;
                case "rk":
                    //Reads both keys from the key file
                    //Good for finding your public key
                    keyReader = new BufferedReader(new FileReader(keyFile));
                    pq = new BigInteger(keyReader.readLine(), RADIX);
                    d = new BigInteger(keyReader.readLine(), RADIX);
                    System.out.println("Public key: " + pq.toString(RADIX) + "\n");
                    System.out.println("Private key: " + d.toString(RADIX));
                    break;
                case "wm":
                    //Writes a message to the message file
                    messageWriter = new PrintWriter(messageFile);
                    System.out.print("Enter message: ");
                    messageText = scan.nextLine();
                    messageWriter.println(messageText);
                    System.out.println("Message written.");
                    messageWriter.close();
                    break;
                case "rm":
                    //Reads the message file
                    messageReader = new BufferedReader(new FileReader(messageFile));
                    System.out.println("Message: " + messageReader.readLine());
                    messageReader.close();
                    break;
                case "em":
                    //Encrypts the message file with the key in the key file
                    messageReader = new BufferedReader(new FileReader(messageFile));
                    messageText = messageReader.readLine();
                    messageReader.close();
                    keyReader = new BufferedReader(new FileReader(keyFile));
                    pq = new BigInteger(keyReader.readLine(), RADIX);
                    keyReader.close();
                    C = RSA.encrypt(messageText, e, pq);
                    messageWriter = new PrintWriter(messageFile);
                    messageWriter.println(C.toString(RADIX));
                    //System.out.println(textConverter.toString(C));
                    //messageWriter.println(textConverter.toString(C));
                    //System.out.println(C.toString());
                    //System.out.println(textConverter.toInt(textConverter.toString(C)));
                    System.out.println("Encrypted message written.");
                    messageWriter.close();
                    break;
                case "dm":
                    //Decrpyts the message file with the keys in the key file
                    messageReader = new BufferedReader(new FileReader(messageFile));
                    messageText = messageReader.readLine();
                    messageReader.close();
                    keyReader = new BufferedReader(new FileReader(keyFile));
                    pq = new BigInteger(keyReader.readLine(), RADIX);
                    d = new BigInteger(keyReader.readLine(), RADIX);
                    keyReader.close();
                    C = new BigInteger(messageText, RADIX);
                    //C = textConverter.toInt(messageText);
                    //System.out.println(C.toString());
                    M = RSA.decrypt(C, d, pq);
                    messageWriter = new PrintWriter(messageFile);
                    messageWriter.println(textConverter.toString(M));
                    System.out.println("Decrypted message written.");
                    messageWriter.close();
                    break;
                
                    
                default:
                    System.out.println("Command '"+option+"' not found. \'o\' for options.");
                    break;
                    
                
            }
            System.out.println(); //always print a new line
        }
    }
}
