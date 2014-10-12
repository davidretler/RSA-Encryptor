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

    static final Message textConverter = new Message();

    public static BigInteger encrypt(BigInteger M, BigInteger e, BigInteger pq) {
        BigInteger C = M.modPow(e, pq);
        return C;
    }

    public static BigInteger decrypt(BigInteger C, BigInteger d, BigInteger pq) {
        BigInteger M = C.modPow(d, pq);
        return M;
    }

    public static BigInteger encrypt(String M, BigInteger e, BigInteger pq) {
        BigInteger Mn = textConverter.toInt(M);
        return encrypt(Mn, e, pq);
    }

    public static BigInteger decrypt(String C, BigInteger d, BigInteger pq) {
        BigInteger Cn = textConverter.toInt(C);
        return encrypt(Cn, d, pq);
    }

    public static BigInteger[] generateKey() {
        Random rand = new Random();
        BigInteger p = BigInteger.probablePrime(2048, rand);
        BigInteger q = BigInteger.probablePrime(2048, rand);
        BigInteger[] key = {p.multiply(q), decryptionKey(p, q)};
        return key;
    }

    public static BigInteger decryptionKey(BigInteger p, BigInteger q) {
        BigInteger v = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
        BigInteger d = Euclidian.extendedEuclidan(BigInteger.valueOf(65537), v)[1];
        if (d.compareTo(BigInteger.valueOf(0)) < 0) {
            d = d.add(v);
        }
        return d;
    }
}
