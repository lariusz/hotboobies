package zestaw4;

public class Pisarz implements Runnable {

	private CzytelniaLock czytelnia;
	private int id;
	private int n;

	public Pisarz(CzytelniaLock czytelnia, int id, int n) {
		this.czytelnia = czytelnia;
		this.id = id;
		this.n = n;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < n; i++) {
//				System.out.println("Poczatek pisania "+ id);
				czytelnia.poczatekPisania();
				System.out.println("Pisarz " + id + " pisze");
//				Thread.sleep(100);
				czytelnia.koniecPisania();
			}
		} catch (InterruptedException e) {
			;
		}
	}
}
