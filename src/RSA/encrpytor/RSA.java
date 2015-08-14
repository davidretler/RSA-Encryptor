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
public final class RSA {

    /**
     * Public exponent
     */
    static final BigInteger e = BigInteger.valueOf((1 << (1 << 4)) + 1);

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
     * Encrypts the given integer-represented message.
     *
     * @param M message
     * @param e power
     * @param pq public key
     * @return ciphertext
     */
    public static BigInteger encrypt(BigInteger M, BigInteger pq) {
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
    public static BigInteger[] generateKey(int bits) {
        Random rand = new Random();
        BigInteger p = BigInteger.probablePrime(bits, rand);
        BigInteger q = BigInteger.probablePrime(bits, rand);
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
        BigInteger d = Euclidian.extendedEuclidan(BigInteger.valueOf((1<<(1 << 4)) + 1), v)[1];
        if (d.compareTo(BigInteger.valueOf(0)) < 0) {
            d = d.add(v);
        }
        return d;
    }
}
