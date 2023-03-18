import java.math.BigInteger;
import java.util.Scanner;

public class EllipticCurve
{   
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);   
        System.out.print("Enter prime p:");
        BigInteger p = sc.nextBigInteger();
        System.out.print("Enter coefficient a:");
        BigInteger a = sc.nextBigInteger();
        System.out.print("Enter coefficient b:");
        sc.nextBigInteger();
        System.out.print("Enter x-coordinate of first point:");
        BigInteger x1 = sc.nextBigInteger();
        System.out.print("Enter y-coordinate of first point:");
        BigInteger y1 = sc.nextBigInteger();
        System.out.print("Enter x-coordinate of second point:");
        BigInteger x2 = sc.nextBigInteger();
        System.out.print("Enter y-coordinate of second point:");
        BigInteger y2 = sc.nextBigInteger();
       
        // Calculate P1 + P2
        BigInteger lambda = x1.equals(x2) && !y1.equals(y2) ? BigInteger.ZERO :
                x1.equals(x2) && y1.equals(y2) ? x1.pow(2).multiply(BigInteger.valueOf(3)).add(a)
                        .multiply(y1.multiply(BigInteger.valueOf(2)).modInverse(p)) :
                y2.subtract(y1).multiply(x2.subtract(x1).modInverse(p));
        BigInteger x3 = lambda.pow(2).subtract(x1).subtract(x2).mod(p);
        BigInteger y3 = lambda.multiply(x1.subtract(x3)).subtract(y1).mod(p);
        System.out.println("\np1 + p2 = (" + x3 + ", " + y3 + ")");
       
        // Calculate P1 - P2
        BigInteger y2Neg = y2.negate().mod(p);
        BigInteger x4 = x2;
        BigInteger y4 = y2Neg;
        lambda = x1.equals(x4) && !y1.equals(y4) ? BigInteger.ZERO :
                x1.equals(x4) && y1.equals(y4) ? x1.pow(2).multiply(BigInteger.valueOf(3)).add(a)
                        .multiply(y1.multiply(BigInteger.valueOf(2)).modInverse(p)) :
                y4.subtract(y1).multiply(x4.subtract(x1).modInverse(p));
        BigInteger x5 = lambda.pow(2).subtract(x1).subtract(x4).mod(p);
        BigInteger y5 = lambda.multiply(x1.subtract(x5)).subtract(y1).mod(p);
        System.out.println("p1 - p2 = (" + x5 + ", " + y5 + ")");
        
        sc.close();
    }
}

/*

	Enter prime p:23
	Enter coefficient a:1
	Enter coefficient b:1
	Enter x-coordinate of first point:3
	Enter y-coordinate of first point:5
	Enter x-coordinate of second point:9
	Enter y-coordinate of second point:7

	p1 + p2 = (6, 17)
	p1 - p2 = (15, 19)

*/