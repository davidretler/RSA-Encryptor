/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA.encrpytor;

import java.lang.Math;
import java.math.BigInteger;
import java.util.Random;

/**
 * RSA Encryption class
 * <p>
 * Encrypts a number or string through RSA
 *
 * @author David Etler
 * @author Chris Etler
 *
 */
public class RSA {

    /**
     * Message class to convert text to integer representation and back.
     */
    static final Message textConverter = new Message();

    /**
     * Encrypts the given integer-represented message.
     *
     * @param M message
     * @param e power
     * @param pq public key
     * @return ciphertext
     */
    public static BigInteger encrypt(BigInteger M, BigInteger e, BigInteger pq) {
        BigInteger C = M.modPow(e, pq);
        return C;
    }

    /**
     * Decrypts the given integer-represented message.
     *
     * @param C ciphertext
     * @param d private key
     * @param pq public key
     * @return message
     */
    public static BigInteger decrypt(BigInteger C, BigInteger d, BigInteger pq) {
        BigInteger M = C.modPow(d, pq);
        return M;
    }

    /**
     * Encrypts the given string-represented message.
     *
     * @param M message
     * @param e power
     * @param pq public key
     * @return ciphertext
     */
    public static BigInteger encrypt(String M, BigInteger e, BigInteger pq) {
        BigInteger Mn = textConverter.toInt(M);
        return encrypt(Mn, e, pq);
    }

    /**
     * Decrypts the given string-represented message.
     *
     * @param C ciphertext
     * @param d private key
     * @param pq public key
     * @return message
     */
    public static BigInteger decrypt(String C, BigInteger d, BigInteger pq) {
        BigInteger Cn = textConverter.toInt(C);
        return encrypt(Cn, d, pq);
    }

    /**
     * Generates a 2048-bit public and private key.
     *
     * @return {public key, private key}
     */
    public static BigInteger[] generateKey() {
        Random rand = new Random();
        BigInteger p = BigInteger.probablePrime(2048, rand);
        BigInteger q = BigInteger.probablePrime(2048, rand);
        BigInteger[] key = {p.multiply(q), decryptionKey(p, q)};
        return key;
    }

    /**
     * Generates the private key given the prime factors of the public key.
     *
     * @param p prime factor 1
     * @param q prime factor 2
     * @return private key
     */
    public static BigInteger decryptionKey(BigInteger p, BigInteger q) {
        BigInteger v = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
        BigInteger d = Euclidian.extendedEuclidan(BigInteger.valueOf(65537), v)[1];
        if (d.compareTo(BigInteger.valueOf(0)) < 0) {
            d = d.add(v);
        }
        return d;
    }
}
