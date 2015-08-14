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

/**
 * Message class
 * <p>
 * Stores a message simultaneously as an integer and a string. Includes static 
 * helper functions for converting between the two representations. Also includes 
 * methods for encrypting and decrypting the message given the public key (for encryption)
 * and the public/private keypair for decryption.
 * 
 * @author Chris Etler, David Etler
 */
public class Message implements java.io.Serializable {

    
    
    /**
     * Generated ID
     */
    private static final long serialVersionUID = 3919067545339606603L;
    /**
     * Exception to be thrown when user tries to read an encrypted message
     */
    public class MessageEncryptedException extends java.lang.Exception {};
    public class MessageNotSignedException extends java.lang.Exception {};
    
    static Alphabet myAlphabet = new Alphabet(); //static definition of the alphabet
    
    private BigInteger messageInt;  //message as integer
    private String messageText;     //message as a string
    private Boolean encrypted;      //whether or not the message is currently encrypted
    private BigInteger messageHash; //the hash of the method
    private Boolean signed = false; //whether the message has been signed
    private BigInteger signature;   //the signature
    private BigInteger signee;      //public key of the signee
    
    /**
     * New blank message
     */
    public Message() {
        this.messageText = "";
        this.messageInt = Message.StrtoInt(this.messageText);
        this.encrypted = false;
        this.signed = false;
    }
    
    /**
     * Create a new Message from the messages text, assumed to be plaintext
     * @param text - Message text
     */
    public Message(String text) {
        this.messageText = text;
        this.messageInt = Message.StrtoInt(text);
        this.encrypted = false;
        this.signed = false;
    }
    
    /**
     * Create message from encoded integer, assumed to be an encrypted message
     * @param encodedMessage - Encoded message
     */
    public Message(BigInteger encodedMessage) {
        this.messageInt = encodedMessage;
        this.messageText = Message.InttoStr(encodedMessage);
        this.encrypted = true;
        this.signed = false;
    }

    /**
     * Get the message as a string
     * @return - The message
     * @throws MessageEncryptedException if the message is encrypted
     */
    public String getMessage() throws MessageEncryptedException {
        if(!this.encrypted) {
            return this.messageText;
        } else {
            throw new MessageEncryptedException();
        }
    }
    
    /**
     * Message as an integer
     */
    public BigInteger toInt() {
        return messageInt;
    }
    
    /**
     * Sets the message
     * @param messageText - New message text
     * @throws MessageEncryptedException 
     */
    public void setMessage(String messageText) throws MessageEncryptedException {
        if(this.encrypted) throw new MessageEncryptedException();
        this.messageText = messageText;
        this.messageInt = Message.StrtoInt(messageText);
    }
    
    /**
     * Sets the message
     * @param encodedMessage - New message encoded as an integer
     * @throws MessageEncryptedException 
     */
    public void setMessage(BigInteger encodedMessage) throws MessageEncryptedException {
        if(this.encrypted) throw new MessageEncryptedException();
        this.messageInt = encodedMessage;
        this.messageText = Message.InttoStr(encodedMessage);
    }
    
    /**
     * Encrypts the Message
     * @param pq - The public key of the intended recipient
     */
    public void Encrypt(BigInteger pq) {
        this.messageInt = RSA.encrypt(this.messageInt, pq);
        this.messageText = "Secret";
        this.encrypted = true;
    }
    
    /**
     * Decrypts the message, given the keypair
     * @param pq - Public key
     * @param d - Private key
     */
    public void Decrypt(BigInteger pq, BigInteger d) {
        this.messageInt = RSA.decrypt(this.messageInt, d, pq);
        this.messageText = Message.InttoStr(this.messageInt);
        this.encrypted = false;
    }
    
        
    /**
     * Helper function to convert text messages to bigInts
     * @param message - The message text to encode
     * @return - The encoded message as a `BigInteger` object
     */
    public static BigInteger StrtoInt(String message) {
        char[] messageChars = new char[message.length()];
        int[] messageInts = new int[message.length()];
        // output starts at 0 and then gets added to
        BigInteger output = BigInteger.valueOf(0);
        BigInteger space = BigInteger.valueOf(1);
        BigInteger toAdd;

        /* convert chars to int using alphabet class, into an array
         adds a plus one because we cannot have 0 or there would be a 0 at the
         end and java wouldn't
         know where to stop (as ther are infinitely many 0's you can append to
         an integer*/
        for (int i = 0; i < message.length(); i++) {
            messageInts[i] = myAlphabet.getInt(message.charAt(i));
        }

        /* make integers into one big number
         big integer because even at two digits per char it gets too big too
         quick (max int is only 10 digits (5 characters)*/

        for (int i = 0; i < messageChars.length; i++) {
            toAdd = BigInteger.valueOf(messageInts[i]);
            toAdd = toAdd.multiply(space);
            output = output.add(toAdd);
            space = space.multiply(BigInteger.valueOf(100));
        }
        // add a 3 didgit value at end that represents the length of the String
        output = output.multiply(BigInteger.valueOf(1000));
        output = output.add(BigInteger.valueOf(messageChars.length));

        return output;
    }

    /**
     * Helper function to convert an encoded message back to it's textual form
     * @param encodedMessage - The message, encoded as a BigInteger
     * @return - The message, as a string
     */
    public static String InttoStr(BigInteger encodedMessage) {

        /*
         *  creates array long enough for 1 spot for each 2 digits, plus 1 extra
         * won't work when there are more than around 300 characters, so works
         * fine for short messages
         * Messages will eventually be broken up into 150 character packets
         * anyway 
         */
        int messageLength = encodedMessage.mod(BigInteger.valueOf(1000)).intValue();
        int intLen = messageLength;
        int[] intList = new int[intLen];
        String output = "";
        BigInteger currentBigInt;
        int currentInt;

        
        encodedMessage = encodedMessage.subtract(BigInteger.valueOf(messageLength));
        encodedMessage = encodedMessage.divide(BigInteger.valueOf(1000));
        
        /* 
         * turns integer into array of integers that are all +1 their alphabet
         * value
         */
        for (int i = 0; i < messageLength; i++) {
            /* 
             * goes for the last two digits. They go to the first spot in the
             * array. Subtract
             * those and divide by 100, to get to the next two digits and make
             * that the second spot in the array
             * These two digit numbers will then be converted into their
             * character equivilent per the Alphabet class
             */

            if (encodedMessage.compareTo(BigInteger.valueOf(0)) <= 0) {
                intList[i] = 0;
                encodedMessage = encodedMessage.divide(BigInteger.valueOf(100));
            }

            else {
                currentBigInt = encodedMessage.mod(BigInteger.valueOf(100));
                currentInt = currentBigInt.intValue();
                intList[i] = currentInt;
                encodedMessage = encodedMessage.subtract(currentBigInt);
                encodedMessage = encodedMessage.divide(BigInteger.valueOf(100));
            }
        }

        /* 
         * Writes each of the characer in the list to a string. Gets the
         * character from the integer-1 since we added one in the way begining
         */
        for (int b = 0; b < messageLength; b++) {
            output = output + myAlphabet.getChar(intList[b]);
        }
        
        
        return output;
    }
    
    /**
     * Calculates the value of the message's hash and updates the hash parameter accordingly
     * <p>
     * Credit to jonathanasdf on Stack Overflow for hashing algorithm
     * @throws MessageEncryptedException 
     */
    private void hash() throws MessageEncryptedException {
        //7 and 31 chosen for being prime numbers
        BigInteger hash = BigInteger.valueOf(7);
        for(int i = 0; i < this.getMessage().length(); i++) {
            hash = hash.multiply(BigInteger.valueOf(31)).add(BigInteger.valueOf(this.getMessage().charAt(i)));
        }
        this.messageHash = hash;
    }
    
    /**
     * Signs the message given the keypair of the signee
     * <p>
     * Encrypts the message hash with the private key. To check signature, the 
     * recipient can decrypt the hash with the public key and compare.
     * This ensures only the owner of the keypair could have written the message.
     * @param publicKey - Public key
     * @param privateKey - Private key
     * @throws MessageEncryptedException 
     */
    public void sign(BigInteger publicKey, BigInteger privateKey) throws MessageEncryptedException {
        if(!this.encrypted) {
            this.hash();
            BigInteger signedHash = RSA.encrypt(this.messageHash, privateKey, publicKey); 
            this.signed = true;
            this.signature = signedHash;
            this.signee = publicKey;
            
            System.out.println("Message signed! Hash: " + this.messageHash);
        } else {
            throw new MessageEncryptedException();
        }
    }
    
    /**
     * Checks the message signature
     * @return - Whether or not the signature was valid
     * @throws MessageEncryptedException
     * @throws MessageNotSignedException 
     */
    public boolean checkSignature() throws MessageEncryptedException, MessageNotSignedException {
        if(!this.signed) {
            throw new MessageNotSignedException();
        }
        if(!this.encrypted) {
            this.hash();
            BigInteger allegedHash = RSA.decrypt(this.signature, RSA.e, this.signee);
            if(this.messageHash.equals(allegedHash)) {
                System.out.println("Hashes equal\nHash: " + this.messageHash + "\nSigned Hash: " + allegedHash);
                return true;
            } else {
                System.out.println("Hashes not equal\nHash: " + this.messageHash + "\nSigned Hash: " + allegedHash);
                return false;
            }
        } else {
            throw new MessageEncryptedException();
        }
    }
    
    public BigInteger getSignee() {
        return this.signee;
    }
}
