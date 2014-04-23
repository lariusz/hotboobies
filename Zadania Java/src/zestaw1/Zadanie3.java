package zestaw1;


/**
 * Napisać program sumujący liczby nieparzyste z przedziału od 1 do n, gdzie n ­ podaje 
 *użytkownik na starcie programu. Program powinien zakończyć sumowanie na liczbie 
 *n, gdy liczba n jest nieparzysta lub na liczbie n �?� 1, gdy liczba n jest parzysta.
 * @author Lariusz
 *
 */
public class Zadanie3 {

	public static void main(String[] args) {		
		int a = 7;
		int wynik = 0;		
		for (int i = 1; i <= a; i+=2) {
			wynik +=i;
		}		
		System.out.println(wynik);
	}

}
