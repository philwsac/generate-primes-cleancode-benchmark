package martin.developer.world.generateprimes;

/**
 * This class Generates prime numbers up to a user specified maximum. The
 * algorithm used is the Sieve of Eratosthenes.
 * <p>
 * Eratosthenes of Cyrene, b. c. 276 BC, Cyrene, Libya -- d. c. 194, Alexandria.
 * The first man to calculate the circumference of the Earth. Also known for
 * working on calendars with leap years and ran the library at Alexandria.
 * <p>
 * The algorithm is quite simple. Given an array of integers starting at 2.
 * Cross out all multiples of 2. Find the next uncrossed integer, and cross out
 * all of its multiples. Repeat until you have passed the square root of the
 * maximum value.
 * 
 * @author Alphonse
 * @version 13 Feb 2002 atp
 */

public class PrimesGenerator {
	/**
	 * @param maxValue
	 *            is the generation limit.
	 */
	public int[] generate(int maxValue) {
		if (maxValue < 2)
			return new int[0]; // return null array if bad input.
		
		int sieveLength = maxValue + 1;
		boolean[] isCrossedOut = new boolean[sieveLength];
				
		// get rid of known non-primes
		isCrossedOut[0] = isCrossedOut[1] = true;
		
		// sieve
		int limit = Math.sqrt(sieveLength) + 1;
		for (int i = 2; i < limit; i++) {
			isCrossedOut = crossOutPrimeMultiples(i, isCrossedOut);
		}
				
		int[] primes = Arrays.stream(isCrossedOut)
			.filter(x -> !x)
			.toArray();
		
		return primes;
	}
	
	private boolean[] crossOutPrimeMultiples(int n, boolean[] isCrossedOut) {
		if (isCrossedOut[n]) {
			// not a prime. don't do anything
			return isCrossedOut;
		}
		
		int multiple = n * 2;
		while (multiple < isCrossedOut.length) {
			isCrossedOut[multiple] = true; 
			multiple += n;
		}
		return isCrossedOut;
	}
}
