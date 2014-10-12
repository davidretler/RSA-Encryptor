/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RSA.encrpytor;

public class Alphabet {
    public char[] alphabet;
    public int length;
    
    public Alphabet(){
    //constructs the array of letters. We will use both capital and lower case, and some basic punctuation.
        length = 95;
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
