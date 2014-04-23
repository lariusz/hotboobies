package zestaw1;

import java.math.BigInteger;

public class Zadanie2 {

	public static void main(String[] args) {
		BigInteger silnia = BigInteger.ONE;
		BigInteger n = new BigInteger("10");
		
		while(n.compareTo(BigInteger.ONE)>0){
			silnia = silnia.multiply(n);
			n = n.subtract(BigInteger.ONE);				
		}
		System.out.println(silnia.toString());
	}

}
