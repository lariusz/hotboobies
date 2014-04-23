package zestaw2;


public class Zadanie1 {
	
	public static void main(String[] args) {
		
		Rational l1 = new Rational(1, 2);
		Rational l2 = new Rational(1, 4);

		Rational l3 = l1.add(l2);
		System.out.println("Dodawanie: " + l3.toString());
		Rational l4 = l1.div(l2);
		System.out.println("Dzielenie: " + l4.toString());
		Rational l5 = l1.mul(l2);
		System.out.println("Mno¿enie: " + l5.toString());
		Rational l6 = l1.sub(l2);
		System.out.println("Odejmowanie: " + l6.toString());
		System.out.println(l1.equals(l2));
		System.out.println(l1.compareTo(l2));		
		
	}


}
