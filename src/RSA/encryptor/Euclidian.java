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
package RSA.encryptor;

import java.lang.Math;
import java.math.BigInteger;

/**
 * Implements the Euclidian algorithm for finding the greatest common
 * denominator for two numbers greater than one
 *
 * @author David Etler
 * @author Chris Etler
 *
 *
 * @since 2014-Oct-11
 */
public final class Euclidian {

    /**
     * Euclidian Algorithm
     * <p>
     * Given A and B, return the greatest common denominator of both Used by the
     * RSA algorithm to check if A and B are relatively prime (checks if
     * GCD(A,B)==1)
     *
     * @param a first number
     * @param b second number
     * @return the greatest common denominator of and b
     */
    public static int gcd(int a, int b) {
        int c;
        int r;
        while (true) {
            if (a < 0 || b < 0) {
                return -1;
            } else if (a < b) {
                c = a;
                a = b;
                b = c;
            } else if (b == 0) {
                return a;
            } else if (b == 1) {
                return 1;
            } else {
                c = (int) Math.floor((double) a / (double) b);
                r = a - b * c;
                a = b;
                b = r;
            }
        }
    }

    /**
     * Extends Euclidian Algorithm
     * <p>
     * Returns s and t such that As+Bt=GCD(A,B) If GCD(A,B)==1, then s is the
     * inverse of A mod B Used by the RSA algorithm to find the decryption key
     *
     * @param A number
     * @param B modulus
     * @return Array of numbers {a, s, t} where a is the GCD of A and B and s is
     * the inverse of A mod B is a==1
     */
    public static long[] extendedEuclidian(long A, long B) {
        long a = A;
        long b = B;
        long s = 1;
        long t = 0;
        long u = 0;
        long v = 1;
        long r = 0;
        long q = 0;
        long nu;
        long nv;
        while (b != 0) {
            r = a % b;
            q = (long) Math.floor(a / b);
            a = b;
            b = r;
            nu = s - u * q;
            nv = t - v * q;
            s = u;
            t = v;
            u = nu;
            v = nv;
        }
        // gcd == a
        long[] m = {a, s, t};
        return m;
    }

    /**
     * Extends Euclidian Algorithm - BigInteger edition
     * <p>
     * Returns s and t such that As+Bt=GCD(A,B) If GCD(A,B)==1, then s is the
     * inverse of A mod B Used by the RSA algorithm to find the decryption key
     *
     * @param A number
     * @param B modulus
     * @return Array of numbers {a, s, t} where a is the GCD of A and B and s is
     * the inverse of A mod B is a==1
     */
    public static BigInteger[] extendedEuclidian(BigInteger A, BigInteger B) {
        BigInteger a = A;
        BigInteger b = B;
        BigInteger s = BigInteger.valueOf(1);
        BigInteger t = BigInteger.valueOf(0);
        BigInteger u = BigInteger.valueOf(0);
        BigInteger v = BigInteger.valueOf(1);
        BigInteger r = BigInteger.valueOf(0);
        BigInteger q = BigInteger.valueOf(0);
        BigInteger nu;
        BigInteger nv;
        while (!b.equals(BigInteger.valueOf(0))) {
            r = a.mod(b);
            q = a.divideAndRemainder(b)[0];
            a = b;
            b = r;
            nu = s.subtract(u.multiply(q));
            nv = t.subtract(v.multiply(q));
            s = u;
            t = v;
            u = nu;
            v = nv;
        }
        // gcd == a
        BigInteger[] m = {a, s, t};
        return m;
    }
    
    //Not really needed anymore
    public static boolean isPrime(long n) {
        long sqrt = (long) Math.floor(Math.sqrt(n));
        for (long i = 2; i <= sqrt; i++) {
            if (n % i == 0 && n != i) {
                return false;
            }
        }
        return true;
    }
}
