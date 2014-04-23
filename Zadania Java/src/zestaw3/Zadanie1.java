package zestaw3;

public class Zadanie1 {

	public static void main(String[] args) {
		Kolejka k = new Kolejka();
		try {
			k.doKolejki(new Integer(7));
			k.doKolejki(new String("Ala ma kota"));
			k.doKolejki(new Double(3.14));
			k.doKolejki(new Boolean(true));
			k.doKolejki(new Float(3.14));
			for (int i = 1; i <= 6; ++i)
				System.out.println((k.zKolejki()).toString());
		} catch (Przepelnienie e) {
			System.out.println("Przepe³niona kolejka!");
		} catch (Niedomiar e) {
			System.out.println("Pusta kolejka!");
		}
	}

}
