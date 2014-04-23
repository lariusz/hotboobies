package zestaw4;

public class Filozof implements Runnable {

	private Jadalnia jadalnia;
	private int n;
	private int id;

	public Filozof(Jadalnia jadalnia, int id, int n) {
		this.jadalnia = jadalnia;
		this.id = id;
		this.n = n;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < n; i++) {
				jadalnia.poczatekJedzenia(id);
				System.out.println("Filozof " + id + " je");
				Thread.sleep(1);
				jadalnia.koniecJedzenia(id);
			}
		} catch (InterruptedException e) {
			;
		}
	}

}
