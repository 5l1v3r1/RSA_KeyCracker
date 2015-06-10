import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA
{
	private final static BigInteger one = new BigInteger("1");
	private final static SecureRandom random = new SecureRandom();

	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger modulus;
	private BigInteger p;
	private BigInteger q;
	private BigInteger phi;

	// generate an N-bit (roughly) public and private key
	RSA(int N)
	{
		p = BigInteger.probablePrime(N / 2, random);
		q = BigInteger.probablePrime(N / 2, random);
		phi = (p.subtract(one)).multiply(q.subtract(one));

		modulus = p.multiply(q);
		publicKey = new BigInteger("65537"); // common value in practice = 2^16  65537
												// + 1
		privateKey = publicKey.modInverse(phi);
	}

	RSA(BigInteger p, BigInteger q)
	{
		this.p = p;
		this.q = q;
		phi = (p.subtract(one)).multiply(q.subtract(one));

		modulus = p.multiply(q);
		publicKey = new BigInteger("65537"); // common value in practice = 2^16
												// + 1
		privateKey = publicKey.modInverse(phi);
	}

	BigInteger encrypt(BigInteger message)
	{
		return message.modPow(publicKey, modulus);
	}

	BigInteger decrypt(BigInteger encrypted)
	{
		return encrypted.modPow(privateKey, modulus);
	}

	public String toString()
	{
		String s = "";
		s += "public  = " + publicKey + "\n";
		s += "private = " + privateKey + "\n";
		s += "modulus = " + modulus;
		return s;
	}

	public BigInteger getPrivateKey()
	{
		return privateKey;
	}

	public void setPrivateKey(BigInteger privateKey)
	{
		this.privateKey = privateKey;
	}

	public BigInteger getPublicKey()
	{
		return publicKey;
	}

	public void setPublicKey(BigInteger publicKey)
	{
		this.publicKey = publicKey;
	}
	public BigInteger getModulus(){
		return modulus;
	}

	public static void main(String[] args)
	{

		
	}
}
