import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Main
{
	private final static SecureRandom random = new SecureRandom();

	public static void main(String[] args)
	{

		try
		{

			PrintWriter writer = new PrintWriter(new File("D://RSATestRun48v7.txt"));
			writer.write("HelloPrint");

			int N = Integer.parseInt(args[0]);
			RSA key = new RSA(N);

			// create random message, encrypt and decrypt
			BigInteger message = new BigInteger(12 - 1, random);

			// // create message by converting string to integer
			// String s = "test";
			// byte[] bytes = s.getBytes();
			// BigInteger message = new BigInteger(s);

			BigInteger encrypt = key.encrypt(message);
			BigInteger decrypt = key.decrypt(encrypt);
			System.out.println("message   = " + message);
			System.out.println("encrpyted = " + encrypt);

			PrimeNumberGenerator primeGen = new PrimeNumberGenerator();

			// RSA bit_4 = new RSA(4);
			RSA bit_8 = new RSA(8);
			RSA bit_12 = new RSA(12);
			RSA bit_16 = new RSA(16);
			RSA bit_24 = new RSA(24);
			RSA bit_32 = new RSA(32);
			RSA bit_48 = new RSA(48);
			RSA bit_64 = new RSA(64);

			long startTimePrimeGen = System.nanoTime();
			double sqrt = Math.sqrt(bit_48.getModulus().doubleValue()) + 5;
			System.out.println("Counter: " + PrimeNumberGenerator.counter + " Double: " + sqrt + " derp: " + bit_48.getModulus().toString());
			// System.out.println("PrimeGen modulus: " +
			// bit_32.getModulus().toString() + " Modulus sqrt: " + sqrt);
			while (PrimeNumberGenerator.counter < sqrt)// bit_32.getModulus().intValue()
			{
				System.out.println("Counter: " + PrimeNumberGenerator.counter + " Double: " + sqrt + " derp: " + bit_48.getModulus().toString());
				primeGen.generatePrime();
			}
			long endTimePrimeGen = System.nanoTime();
			double deltaTimePrimeGen = (endTimePrimeGen - startTimePrimeGen) / 1000000000f;
			writer.println("PrimeGen modulus: " + bit_24.getModulus().toString() + " Modulus sqrt: ");
			writer.println("All primes generated:" + deltaTimePrimeGen);
			System.out.println("Done PRIMEGEN");

			Primer p8 = new Primer(bit_8);
			try
			{
				Thread.sleep(5000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			long startTime8 = System.nanoTime();
			p8.crack();
			long endTime8 = System.nanoTime();
			double deltaTime8 = (endTime8 - startTime8) / 1000000000f;
			writer.println("RSA 8bits crackTime:" + deltaTime8 + " RESULTS EQUAL: " + Boolean.toString(p8.testRSAKeys(message)));

			Primer p12 = new Primer(bit_12);
			long startTime12 = System.nanoTime();
			p12.crack();
			long endTime12 = System.nanoTime();
			double deltaTime12 = (endTime12 - startTime12) / 1000000000f;
			String ou12 = "RSA 12bits crackTime:" + deltaTime12 + " RESULTS EQUAL: " + Boolean.toString(p12.testRSAKeys(message));
			writer.println(ou12);
			System.out.println(ou12);

			Primer p16 = new Primer(bit_16);
			long startTime16 = System.nanoTime();
			p16.crack();
			long endTime16 = System.nanoTime();
			double deltaTime16 = (endTime16 - startTime16) / 1000000000f;
			writer.println("RSA 16bits crackTime:" + deltaTime16 + " RESULTS EQUAL: " + Boolean.toString(p16.testRSAKeys(message)));

			Primer p24 = new Primer(bit_24);
			long startTime24 = System.nanoTime();
			p24.crack();
			long endTime24 = System.nanoTime();
			double deltaTime24 = (endTime24 - startTime24) / 1000000000f;
			writer.println("RSA 24bits crackTime:" + deltaTime24 + " RESULTS EQUAL: " + Boolean.toString(p24.testRSAKeys(message)));

			Primer p32 = new Primer(bit_32);
			long startTime32 = System.nanoTime();
			p32.crack();
			long endTime32 = System.nanoTime();
			double deltaTime32 = (endTime32 - startTime32) / 1000000000f;
			writer.println("RSA 32bits crackTime:" + deltaTime32 + " RESULTS EQUAL: " + Boolean.toString(p32.testRSAKeys(message)));

			Primer p48 = new Primer(bit_48);
			long startTime48 = System.nanoTime();
			p48.crack();
			long endTime48 = System.nanoTime();
			double deltaTime48 = (endTime48 - startTime48) / 1000000000f;
			writer.println("RSA 48bits crackTime:" + deltaTime48 + " RESULTS EQUAL: " + Boolean.toString(p48.testRSAKeys(message)));

			/*
			 * Primer p64 = new Primer(bit_64); long startTime64 =
			 * System.nanoTime(); p64.crack(); long endTime64 =
			 * System.nanoTime(); double deltaTime64 = (endTime64 - startTime64)
			 * / 1000000000f; writer.println("RSA 64bits crackTime:" +
			 * deltaTime64 + " RESULTS EQUAL: " +
			 * Boolean.toString(p64.testRSAKeys(message)));
			 */
			/*
			 * Primer p4 = new Primer(bit_4); long startTime4 =
			 * System.nanoTime(); p4.crack(); long endTime4 = System.nanoTime();
			 * double deltaTime4 = (endTime4 - startTime4) / 1000000000f;
			 * writer.print("RSA 4bits crackTime:" + deltaTime4 +
			 * " RESULTS EQUAL: " + Boolean.toString(p4.testRSAKeys(message)));
			 */
			writer.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static class Primer
	{
		RSA rsa;
		BigInteger modulus;
		BigInteger q;
		BigInteger p;
		private RSA testRSA;

		public Primer(RSA rsa)
		{
			this.rsa = rsa;
		}

		public void crack()
		{
			modulus = new BigInteger(rsa.getModulus().toString());
			for (BigInteger d : PrimeNumberGenerator.getPrimeNumbers())
			{
				if (modulus.mod(d) == BigInteger.ZERO)
				{
					q = new BigInteger(d.toString());
					p = modulus.divide(q);
					break;
				}

			}
		}

		public boolean testRSAKeys(BigInteger message)
		{
			testRSA = new RSA(p, q);
			BigInteger crypt1 = rsa.encrypt(message);
			BigInteger crypt2 = testRSA.encrypt(message);
			if (crypt1.equals(crypt2))
			{
				return true;
			}
			return false;
		}
	}

}
