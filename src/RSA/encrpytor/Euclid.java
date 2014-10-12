/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA.encrpytor;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Base64;
import java.io.*;

/**
 * Driver for the RSA encryption class
 * <p>
 * Command line interface for generating 2048-bit RSA keys, encrypting messages,
 * and decrpyting messages. Support for inputing a public key or retriving one's
 * private key from a file. Support for encrpyting a large messagefile.
 *
 * @author David Etler
 * @author Chris Etler
 *
 */
public class Euclid {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        Scanner scan = new Scanner(System.in);
        File keyFile = new File("key.txt");
        BufferedReader keyReader;
        PrintWriter keyWriter;
        File messageFile = new File("message.txt");
        BufferedReader messageReader;
        PrintWriter messageWriter;
        String option = "";
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger[] key;
        BigInteger p;
        BigInteger q;
        BigInteger pq;
        BigInteger d;
        BigInteger value;
        BigInteger M;
        BigInteger C;
        BigInteger Mn;
        String messageText;
        final int RADIX = Character.MAX_RADIX;
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

        System.out.println("TopSEEKRUT Encyptor v3.14159");
        //Print options
        System.out.println(COMMANDS);
        //Key going until user wants to quit
        while (!option.equals("q")) {
            System.out.print("Option: ");
            option = scan.next();
            scan.nextLine();
            switch (option) {
                case "q":
                    //Closes the program
                    System.out.println("Closing");
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
                    keyWriter = new PrintWriter(keyFile, "UTF-8");
                    key = RSA.generateKey();
                    pq = key[0];
                    d = key[1];
                    //pq = p.multiply(q);
                    //d = RSA.decryptionKey(p, q);
                    keyWriter.println(pq.toString(RADIX));
                    keyWriter.println(d.toString(RADIX));
                    keyWriter.close();
                    System.out.println("Key written to file");
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
                    System.out.println("Public key: " + pq.toString(RADIX));
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
                    M = RSA.decrypt(C, d, pq);
                    messageWriter = new PrintWriter(messageFile);
                    messageWriter.println(textConverter.toString(M));
                    System.out.println("Decrypted message written.");
                    messageWriter.close();
                    break;
            }
        }
    }
}
