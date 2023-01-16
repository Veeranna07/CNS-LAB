import java.security.*;
import javax.crypto.*;
import java.util.Scanner;

public class RSA
{
	public static void main(String[] args) throws Exception
	{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext message: ");
        String plaintext = sc.nextLine();
        sc.close();

        // Encrypt the message
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = cipher.doFinal(plaintext.getBytes());
        System.out.println("Encrypted message in bytes: " + encryptedMessage);

        // Decrypt the message
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
        System.out.println("Decrypted message in bytes: " + decryptedMessage);
        System.out.println("Decrypted plaintext message: " + new String(decryptedMessage));
    }
}

/*
	Enter plaintext message: Hello World!
	Encrypted message in bytes: [B@7c29daf3
	Decrypted message in bytes: [B@4f063c0a
	Decrypted plaintext message: Hello World!
*/
