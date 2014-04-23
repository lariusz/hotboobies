package zestaw4;

public class ZarzadzanieToaleta {

	public static void main(String[] args) throws InterruptedException {
		Lazienka lazienka = new Lazienka();
		final int ileKobiet = 5;
		final int iluMezczyzn = 5;
		final int proby = 100;
		Thread[] watki = new Thread[ileKobiet + iluMezczyzn];
		for (int i = 0; i < ileKobiet; ++i) {
			watki[i] = new Kobieta(lazienka, proby);
		}
		for (int i = ileKobiet; i < ileKobiet + iluMezczyzn; ++i) {
			watki[i] = new Mezczyzna(lazienka, proby);
		}
		for (Thread t : watki) {
			t.start();
		}
		for (Thread t : watki) {
			t.join();
		}

	}
}
