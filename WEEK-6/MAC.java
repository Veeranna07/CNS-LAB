import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import java.util.Scanner;

public class MAC
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		KeyGenerator keyGen=KeyGenerator.getInstance("DES");
		SecureRandom secRandom=new SecureRandom();
		keyGen.init(secRandom);
		Key key=keyGen.generateKey();
		Mac mac=Mac.getInstance("HmacSHA256");
		mac.init(key);
		
		System.out.print("Enter Message : ");
		String msg=sc.nextLine();
		byte[] bytes=msg.getBytes();
		byte[] macResult=mac.doFinal(bytes);
		
		System.out.println("Mac result for "+msg+" :"+new String(macResult));
		sc.close();
	}
}

/*
Enter Message : hello world
Mac result for hello world :ÅË*hÔ›è%Û˜’;¢£ªÖu5Ys;Ù{öm
 */
