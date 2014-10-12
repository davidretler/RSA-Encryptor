/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RSA.encrpytor;

import java.math.BigInteger;


/**
 *
 * @author Chris
 */
public class Message {
    Alphabet myAlphabet = new Alphabet();
    public Message(){ }
    
    public BigInteger toInt(String message){
        char[] messageChars = new char[message.length()];
        int[] messageInts = new int[message.length()];
        BigInteger output = BigInteger.valueOf(0);
        BigInteger space = BigInteger.valueOf(1);
        BigInteger toAdd;
        
        //Convert string to list of chars based on unicode 
        for (int i=0; i<message.length(); i++){
            messageChars[i] = message.charAt(i);
        }
        //convert chars to int using alphabet class
        for (int i=0; i<message.length(); i++){
            messageInts[i] = myAlphabet.getInt(messageChars[i])+1;
        }
        
        //make integers into one big number
        for (int i=0; i<messageChars.length; i++){
            toAdd = BigInteger.valueOf(messageInts[i]);
            toAdd = toAdd.multiply(space);
            output = output.add(toAdd);
            space = space.multiply(BigInteger.valueOf(100));
        }
        return output;
    }
    public String toString(BigInteger a){
        int[] intList = new int[1000];
        String output = "";
        BigInteger currentBigInt;
        int currentInt;
        int i=0;
        
        //turns integer into array of integers that are all +1 their alphabet value
        while (a.signum() == 1){
            currentBigInt = a.mod(BigInteger.valueOf(100));
            currentInt = currentBigInt.intValue();
            intList[i] = currentInt;
            a = a.subtract(currentBigInt);
            a = a.divide(BigInteger.valueOf(100));
            i++;
        }
        
        //Writes each of the characer in the list to a string. Gets the character from the integer-1 since we added one in the way begining
        int n=0;
        while(intList[n]>0){
            intList[n] = intList[n]-1;
            output = output + myAlphabet.getChar(intList[n]);
            n++;
        }
        return output;
           
    }
}
