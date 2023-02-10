import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec; 

public class HMACExample {
  
  private static final String HMAC_SHA256 = "HmacSHA256";
  
  public static byte[] calculateHMAC(String data, String key) 
      throws NoSuchAlgorithmException, InvalidKeyException {
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
    Mac mac = Mac.getInstance(HMAC_SHA256);
    mac.init(secretKey);
    return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
  }
  
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the message: ");
    String data = sc.nextLine();
    System.out.print("Enter the key: ");
    String key = sc.nextLine();
    byte[] hmac = calculateHMAC(data, key);
    System.out.println("HMAC (hexadecimal): " + bytesToHex(hmac));
    System.out.println("Original message: " + data);
  }
  
  private static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}

/*
OUTPUT
Enter the message: HELLO AIML
Enter the key: GCTC
HMAC (hexadecimal): cb5a9857e8cfcbe3d8f74e627c98b9a41f6d8677fab731b1698ddd8bb8b93a3c
Original message: HELLO AIML 

*/
