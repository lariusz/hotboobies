package zestaw4;

public class Czytelnik implements Runnable {

	private CzytelniaLock czytelnia;
	private int id;
	private int n;

	public Czytelnik(CzytelniaLock czytelnia, int id, int n) {
		this.czytelnia = czytelnia;
		this.id = id;
		this.n = n;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < n; i++) {
//				System.out.println("Poczatek czytania " + id);
				czytelnia.poczatekCzytania();
				System.out.println("Czyt. " + id + " czyta");
//				Thread.sleep(100);
				czytelnia.koniecCzytania();
			}
		} catch (InterruptedException e) {
			;
		}

	}
}
