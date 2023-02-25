import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5
{
	public static String getMd5(String input)
	{
		try
		{
			MessageDigest md=MessageDigest.getInstance("MD5");
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
		System.out.println("Your HashCode generated by MD5 is : "+getMd5(s));
		sc.close();
	}	
}
/*
Enter Your Message : how are you?
Your HashCode generated by MD5 is : 35179a54ea587953021400eb0cd23201

Enter Your Message : how are  you?
Your HashCode generated by MD5 is : e8de8d964e7f7ea3a50255c6ee5f17b9
 */