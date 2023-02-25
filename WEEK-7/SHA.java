import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA
{
	public static String encryptThisString(String input)
	{
		try
		{
			MessageDigest md=MessageDigest.getInstance("SHA-1");
			byte[] messageDigest=md.digest(input.getBytes());
			BigInteger no=new BigInteger(1,messageDigest);
			String hashtext=no.toString(16);
			while(hashtext.length()<32)
			{
				hashtext="0"+hashtext;
			}
				return hashtext;
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}
		
	public static void main(String[] args) throws NoSuchAlgorithmException
	{
		System.out.print("Enter Your Message : ");
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		System.out.println("HashCode generated by SHA-1 for ");
		System.out.print(""+s+":"+encryptThisString(s));
		sc.close();
	}	
}

/*
Enter Your Message : how are you
HashCode generated by SHA-1 for 
how are you:4bfe46a79d38aba1480cd5d9466beb9111895bf2

Enter Your Message : how are  you
HashCode generated by SHA-1 for 
how are  you:5124c44ff1d39564ebafe9b29ae8d70301f8b6d4
 */
