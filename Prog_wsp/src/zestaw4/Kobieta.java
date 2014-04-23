package zestaw4;

class Kobieta extends Thread {
	private final Lazienka lazienka;
	private final int proby;

	public Kobieta(Lazienka lazienka, int proby) {
		this.lazienka = lazienka;
		this.proby = proby;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < proby; ++i) {
				lazienka.wchodziKobieta();
				System.out.println("Kobieta wchodzi");
				Thread.yield();
				System.out.println("Kobieta wychodzi");
				lazienka.wychodziKobieta();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
