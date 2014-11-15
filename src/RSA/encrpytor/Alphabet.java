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

public class Alphabet {
    public char[] alphabet;
    public int length;
    
    public Alphabet(){
    //constructs the array of letters. We will use both capital and lower case, and some basic punctuation.
        length = 95;
        //length = 99;
        alphabet = new char[length];
        
        //Defines the first 26 characters in the Array to be A,B,C,...,Z
        int startPos = (int) 'A'; //start at the letter A
        for (int i=0; i<26; i++){
            alphabet[i]=(char)(i+startPos);
        }
        
        //Defines the 27-52 spot in the arry to be a,b,c,...,z
        startPos = (int)'a';
         for (int i=26; i<52; i++){
            alphabet[i]=(char)(i-26+startPos);
        }
        //Manually define basic punctuation. Characters 53-66
        alphabet[52]='.';
        alphabet[53]=',';
        alphabet[54]=';';
        alphabet[55]=':';
        alphabet[56]='(';
        alphabet[57]=')';
        alphabet[58]='{';
        alphabet[59]='}';
        alphabet[60]='[';
        alphabet[61]=']';
        alphabet[62]='!';
        alphabet[63]='?';
        alphabet[64]='"';
        alphabet[65]='\'';
   
        
        //Defines the 67th to 77th
        //Numbers 0-9
        startPos= (int)'1';
        alphabet[66] = '0';
        for (int i=67; i<76; i++){
            alphabet[i]=(char)(i-67+startPos);
        }
        
        //More symbols
        alphabet[76] = '@';
        alphabet[77] = '#';
        alphabet[78] = '$';
        alphabet[79] = '%';
        alphabet[80] = '^';
        alphabet[81] = '&';
        alphabet[82] = '*';
        alphabet[83] = '-';
        alphabet[84] = '_';
        alphabet[85] = '\\';
        alphabet[86] = '/';
        alphabet[87] = '~';
        alphabet[88] = '`';
        alphabet[89] = '+';
        alphabet[90] = '=';
        alphabet[91] = '<';
        alphabet[92] = '>';
        alphabet[93] = '|';
        //alphabet[94] = '€';
        //alphabet[95] = 'ƒ';
        //alphabet[96] = '†';
        //alphabet[97] = '‡';
        //alphabet[98] = 'Œ';
        //alphabet[99] = 'Ž';
                
        //Create a space. If the character isn't any of those, just make it whitespace.
        alphabet[length-1]=' ';
        
             
    }
    public char getChar(int pos){
        return alphabet[pos];
    }
    
    //return integer value of character based on Alphabet class. If character not found return 76, the value for space
    public int getInt(char myChar){
        int output = length;
        for (int i=0; i<length; i++){
            if (myChar == alphabet[i]) output=i;
        }
        return output;
    }
}
