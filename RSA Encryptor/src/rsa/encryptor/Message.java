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
package rsa.encryptor;

import java.math.BigInteger;

/**
 * Message class
 * <p>
 * Used to represent a message as an integer and convert back and forth between
 * the two encodings. Necessary for the RSA class to function as plaintext needs
 * to be written as an integer for ciphering.
 *
 * @author Chris Etler
 */
public class Message {

    Alphabet myAlphabet = new Alphabet();

    public Message() {
    }

    public BigInteger toInt(String message) {
        char[] messageChars = new char[message.length()];
        int[] messageInts = new int[message.length()];
        BigInteger output = BigInteger.valueOf(0);
        BigInteger space = BigInteger.valueOf(1);
        BigInteger toAdd;

        //Convert string to list of chars based on unicode 
        for (int i = 0; i < message.length(); i++) {
            messageChars[i] = message.charAt(i);
        }
        //convert chars to int using alphabet class
        for (int i = 0; i < message.length(); i++) {
            messageInts[i] = myAlphabet.getInt(messageChars[i]) + 1;
        }

        //make integers into one big number
        for (int i = 0; i < messageChars.length; i++) {
            toAdd = BigInteger.valueOf(messageInts[i]);
            toAdd = toAdd.multiply(space);
            output = output.add(toAdd);
            space = space.multiply(BigInteger.valueOf(100));
        }
        return output;
    }

    public String toString(BigInteger a) {
        int[] intList = new int[1000];
        String output = "";
        BigInteger currentBigInt;
        int currentInt;
        int i = 0;

        //turns integer into array of integers that are all +1 their alphabet value
        while (a.signum() == 1) {
            currentBigInt = a.mod(BigInteger.valueOf(100));
            currentInt = currentBigInt.intValue();
            intList[i] = currentInt;
            a = a.subtract(currentBigInt);
            a = a.divide(BigInteger.valueOf(100));
            i++;
        }

        //Writes each of the characer in the list to a string. Gets the character from the integer-1 since we added one in the way begining
        int n = 0;
        while (intList[n] > 0) {
            intList[n] = intList[n] - 1;
            output = output + myAlphabet.getChar(intList[n]);
            n++;
        }
        return output;

    }
}
