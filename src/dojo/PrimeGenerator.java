package dojo;

/**
 * Refactorings:
 *
 * Extract Fields.
 * Extract Methods: initArrayOfIntegers, crossOutMultiples, putUncrossedIntegerIntoResult
 * Inline s with f.length
 * Rename f to isCrossed
 * Ensure for loop starts from 2
 * Extract Methods: crossOutMultipleOf, numberOfUncrossedIntegers, notCrossed
 * Rename methods to: uncrossIntegersUpTo
 * @author jacky
 *
 */

public class PrimeGenerator {
    /**
     *
     * @param maxValue
     *            is the generation limit
     * @return
     */
    public int[] generatePrimes(int maxValue) {
        if (maxValue >= 2) { // the only valid case
            // declarations
            int s = maxValue + 1; // size of array
            boolean[] f = new boolean[s];

            // initialize array to true
            for (int i = 0; i < s; i++) {
                f[i] = true;
            }

            // get rid of known non-primes
            f[0] = f[1] = false;

            // sieve
            for (int i = 2; i < Math.sqrt(s) + 1; i++) {
                for (int j = 2 * i; j < s; j += i) {
                    f[j] = false; // multiple is not prime
                }
            }

            // how many primes are there?
            int count = 0;
            for (int i = 0; i < s; i++) {
                if (f[i])
                    count++; // bump count
            }

            int[] primes = new int[count];

            // move the primes into the result
            for (int i = 0, j = 0; i < s; i++) {
                if (f[i])
                    primes[j++] = i;
            }

            return primes;
        } else { // maxValue < 2
            return new int[0]; // return null array if bad input.
        }
    }
}
