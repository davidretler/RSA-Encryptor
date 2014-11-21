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
 * Used to represent a message as an integer and convert back and forth between
 * the two encodings. Necessary for the RSA class to function as plaintext needs
 * to be written as an integer for ciphering.
 *
 * @author Chris Etler
 */
public class Message {

     Alphabet myAlphabet = new Alphabet();
    public BigInteger toInt(String message) {
        char[] messageChars = new char[message.length()];
        int[] messageInts = new int[message.length()];
        //output starts at 0 and then gets added to
        BigInteger output = BigInteger.valueOf(0);
        BigInteger space = BigInteger.valueOf(1);
        BigInteger toAdd;

        
        //convert chars to int using alphabet class, into an array
        //adds a plus one because we cannot have 0 or there would be a 0 at the end and java wouldn't
        //know where to stop (as ther are infinitely many 0's you can append to an integer
        for (int i = 0; i < message.length(); i++) {
            messageInts[i] = myAlphabet.getInt(message.charAt(i));
        }

        //make integers into one big number
        //big integer because even at two digits per char it gets too big too 
        //quick (max int is only 10 digits (5 characters)
        
        for (int i = 0; i < messageChars.length; i++) {
            toAdd = BigInteger.valueOf(messageInts[i]);
            toAdd = toAdd.multiply(space);
            output = output.add(toAdd);
            space = space.multiply(BigInteger.valueOf(100));
        }
        //add a 3 didgit value at end that represents the length of the String
        output = output.multiply(BigInteger.valueOf(1000));
        output = output.add(BigInteger.valueOf(messageChars.length));
        
        return output;
    }

    //takes a big integer and converts it into a string
    public String toString(BigInteger a) {
        
        //creates array long enough for 1 spot for each 2 digits, plus 1 extra
        //won't work when there are more than around 300 characters, so works fine for short messages
        //Messages will eventually be broken up into 150 character packets anyway
        int messageLength = a.mod(BigInteger.valueOf(1000)).intValue();
        int intLen = messageLength;
        int[] intList = new int[intLen];
        String output = "";
        BigInteger currentBigInt;
        int currentInt;
        
       // int i = 0;
        //int messageLength = a.mod(BigInteger.valueOf(1000)).intValue();
        a = a.subtract(BigInteger.valueOf(messageLength));
        a = a.divide(BigInteger.valueOf(1000));
        //System.out.println(a);
        
        //turns integer into array of integers that are all +1 their alphabet value
        for (int i=0; i<messageLength; i++) {
            //goes for the last two digits. They go to the first spot in the array. Subtract
            //those and divide by 100, to get to the next two digits and make that the second spot in the array
            //These two digit numbers will then be converted into their character equivilent per the Alphabet class
            
            
            if (a.compareTo(BigInteger.valueOf(0))<=0)
                { 
                    intList[i] = 0;
                    a = a.divide(BigInteger.valueOf(100));
                }
            
            else{
                currentBigInt = a.mod(BigInteger.valueOf(100));
                currentInt = currentBigInt.intValue();
                intList[i] = currentInt;
                a = a.subtract(currentBigInt);
                a = a.divide(BigInteger.valueOf(100));
            }
            //System.out.println("rem: "+a);
            //System.out.println("Char at "+i+": "+intList[i]);
            //i++;
        }

        //Writes each of the characer in the list to a string. Gets the character from the integer-1 since we added one in the way begining
//        int n = 0;
//        while (intList[n] > 0) {
//            
//            output = output + myAlphabet.getChar(intList[n]);
//            n++;
//        }
        for (int b=0; b<messageLength; b++){
            output = output + myAlphabet.getChar(intList[b]);
        }
        return output;

    }
}
