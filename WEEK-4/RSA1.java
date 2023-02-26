import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class RSA1
{
	public static void main(String[] args) throws NoSuchAlgorithmException,NoSuchPaddingException,InvalidKeyException,IllegalBlockSizeException,BadPaddingException	
	{
		KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
		SecureRandom secureRandom=new SecureRandom();
		keyPairGenerator.initialize(2048,secureRandom);
		KeyPair pair=keyPairGenerator.generateKeyPair();
		PublicKey publicKey=pair.getPublic();
		
		String publicKeyString=Base64.getEncoder().encodeToString(publicKey.getEncoded());
		System.out.print("Public Key Generated : "+publicKeyString);
		PrivateKey privateKey=pair.getPrivate();
		
		String privateKeyString=Base64.getEncoder().encodeToString(privateKey.getEncoded());
		System.out.println("\nPrivate Key Generated : "+privateKeyString);
		
		//Encrypt User Message
		Cipher encryptionCipher=Cipher.getInstance("RSA");
		encryptionCipher.init(Cipher.ENCRYPT_MODE,privateKey);
		Scanner sc=new Scanner(System.in);
		System.out.print("\nEnter Plain Text Message : ");
		String message=sc.nextLine();
		byte[] encryptedMessage=encryptionCipher.doFinal(message.getBytes());
		String encryption=Base64.getEncoder().encodeToString(encryptedMessage);
		System.out.print("Encrypted Message : "+encryption);
		
		//Decrypt User Message
		Cipher decryptionCipher=Cipher.getInstance("RSA");
		decryptionCipher.init(Cipher.DECRYPT_MODE,publicKey);
		byte[] decryptedMessage=decryptionCipher.doFinal(encryptedMessage);
		String decryption=new String(decryptedMessage);
		System.out.print("\nDecrypted Message : "+decryption);
		sc.close();
	}
}

/*
Public Key Generated : MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr4aug2YVIXjSZdDpXu5PNSu3l4ohMQW7sWyJkfyFzdFoBs7enj2+kRjkaa5RnsLqo07zg1z1n1rbOsTLVNnBsRNeAd9Y+j9grpH2Nc4KPk1VUxzIaPc5aTeKE4G4ZZW3Dv85ziPyt25OAcjDq/3hS6lkwRmgHJn73NP+d2dWTwES2sosfyGwFfrTjk2BMu2yV2WweQwT1eOQ/RAoFmM61x9dr80P724jKOVt10zbIfkvBSRoH5P9BQKgkDqaUlJTMUmY5vOfEENTXxF2PRi7sEZT9UizFnuW/t8izqyh6Pi8dUjwHN6cXP/FF5gLt1Dmlgp4q/FDh9Y9liGJWcNz5QIDAQAB
Private Key Generated : MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCvhq6DZhUheNJl0Ole7k81K7eXiiExBbuxbImR/IXN0WgGzt6ePb6RGORprlGewuqjTvODXPWfWts6xMtU2cGxE14B31j6P2CukfY1zgo+TVVTHMho9zlpN4oTgbhllbcO/znOI/K3bk4ByMOr/eFLqWTBGaAcmfvc0/53Z1ZPARLayix/IbAV+tOOTYEy7bJXZbB5DBPV45D9ECgWYzrXH12vzQ/vbiMo5W3XTNsh+S8FJGgfk/0FAqCQOppSUlMxSZjm858QQ1NfEXY9GLuwRlP1SLMWe5b+3yLOrKHo+Lx1SPAc3pxc/8UXmAu3UOaWCnir8UOH1j2WIYlZw3PlAgMBAAECggEAKu93+r5axuBGHw+2Njl2+JukAhaQvewYGHNjR2mM1Wa22ot52x1hYx7CqLMyVfJ58M6cNHLlxkA0RjDjR/MoNQd2xGB85GHiZXFg15J54PmLuJPe1gY7behfBxyBHaA8shdubWVPttztifQxIP4uQRLDiZSaHMTx3fehVFXbv+EsjQbUTy9RAC/yZJGlG+X7uchSd7LbzalhxpYpomz+IO/iuB/jhSW5F1u6rYSWPdzriha254tRaXVF3mIeVBAHBVKftD8AXkDTCnIvKaYwoaVIo2CMJhBdgA4pottN4oWBk7VptnbPEaOaBJfKMdETE1nS2t92iaF50mb3QafQ1QKBgQDdXHa+8LG017jZnQ2CIIeQyQUnVE1KCx6JZfQArtLUkDYwkvloN89xbPjNKKqEvfcSmH5BDkhOtl8fZTp9rfy7W8mWN8s/3OcHJy7N4sDtgdlFnX9lzNhBdnYAnHnPVIw/Bx4p4ErWF40H0Xt87LSD3BfIqmOISYyjKiMygDLTpwKBgQDK/hqvPKZee5yXa8estfPczBCXMiuHj2C0fN+xCFCHx/0QLS9zczBHxy91JYUBkmE7d9bbvMSRPz/iZKIK8gCOeHQ5kmsIa7JBJb2t9fCb3Y9edxEXJIy2gZjm5xS9se2pgGNQZyR2LeYrUEVcs+of79x0jt/u/16D9JB2K9YdkwKBgQDFAv5MgjwnD7fdNgJHxlvhaIfFsSn4tFzBPJQl53KM7KzOHoELWGCwOqRVM9RPwCKd0krL68I/C+EtOBMOuEMvHUoVACNM+3ZCz4TAiQfcWqzdYId6Ow5ddOkhMiLXfBA2KEeYTZ9NXDiQtMiLLsCaetBQMBXVvmAE+APLdn3GEQKBgQC9n9QDoO91UZm9ZcSYVTyiJMrWwtog/1ICKKi58p9vx+VhyCQMgC040BxIHa17A2frKo6+pD8nw+MEtKce/GSCsok4AswTiK9y5jOjnxyOLdGxGB8R9GWyU6wm/vTEI8N01Odxg3hvIX9jNNAmwjbScLoK9DDCTTHYqIyOp3KUGwKBgQCLpkERri207YoHunM2+/yciKK9aWADsBx4eRkqC683iilhbkA05VM549AN+33EdLjeaWdmBaWAen+/quttmayQ19X4OMqCtgcKKTA1eQPAd5TgUgh8jimhfN8fFZzH8JcyG6101qjHUByfEjsBTmImrSogcCUoC+PmbN8Ggv6hjg==

Enter Plain Text Message : hello world
Encrypted Message : MPDAhqICeSJLT3QL9zxrEoLK/iI5AqA7LXo7gXkslIHZWCFWV3rH8MhK0GQ3vAZInGbVpWKbJE0yOuUuw/F5k07ovUBgMvH+OcwsqmHv2uJ28ANBFIwR/o2nn48t+8dJXtyzJW64d+A9Y2PwsYHR9PQfler1mUYWmAHE4PYbLWHB1eX22FDQnjuF/+LnbOMRBwpILNiOWdsP6KgAHDWrzHL5RPOtmXmU+59TZCaGsx1m4KhJhM/U+IEfUV34OEdo2f6EgAy0GmGuuaXBmLPHCOZkg6FJ14XeMq9qy+LQ8VUDUn69PLU1narBMh64KizV907LGPMdXw+YOMFRwF6bgA==
Decrypted Message : hello world
 */
