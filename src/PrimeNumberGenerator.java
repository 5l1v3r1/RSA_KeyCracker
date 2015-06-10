import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PrimeNumberGenerator
{
	public static List<BigInteger> primeNumbers = new ArrayList<BigInteger>();
	public static long counter = 3;// 3

	public PrimeNumberGenerator()
	{
		primeNumbers.add(new BigInteger("2"));
	}

	public void generatePrime()
	{
		BigInteger n = new BigInteger(String.valueOf(counter));
		if (isPrime(n))
		{
			addPrimeNumber(n);
		}
		counter += 2;
	}

	public static List<BigInteger> getPrimeNumbers()
	{
		return primeNumbers;

	}

	public static void setPrimeNumbers(List<BigInteger> primeNumbers)
	{
		PrimeNumberGenerator.primeNumbers = primeNumbers;
	}

	public static void addPrimeNumber(BigInteger n)
	{
		synchronized (primeNumbers)
		{

			primeNumbers.add(n);
		}
	}

	private static BigInteger two = new BigInteger("2");

	// checks whether an int is prime or not.
	static boolean isPrime(BigInteger n)
	{

		if (n.mod(two).equals(BigInteger.ZERO))
		{
			return false;
		}
		// if not, then just check the odds
		for (long i = 3; i * i <= n.longValueExact(); i += 2)
		{
			BigInteger test = new BigInteger(String.valueOf(i));
			if (n.mod(test).equals(BigInteger.ZERO))
				return false;
		}
		return true;
	}
}
