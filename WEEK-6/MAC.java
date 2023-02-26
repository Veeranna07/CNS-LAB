import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

public class MAC
{
	public static void main(String[] args) throws Exception
	{
		KeyGenerator keyGen=KeyGenerator.getInstance("DES");
		SecureRandom secRandom=new SecureRandom();
		keyGen.init(secRandom);
		Key key=keyGen.generateKey();
		Mac mac=Mac.getInstance("HmacSHA256");
		mac.init(key);
		
		String msg=new String("Hi how are you?");
		byte[] bytes=msg.getBytes();
		byte[] macResult=mac.doFinal(bytes);
		
		System.out.println("Mac result for "+msg+":"+new String(macResult));
	}
}

/*
Mac result for Hi how are you?:˜÷ÏÞÊãz&W¤ùhxè„VÌÍ¡nUxKÝÖ©e!&
 */