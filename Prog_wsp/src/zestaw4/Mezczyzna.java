package zestaw4;

public class Mezczyzna extends Thread {

	private int proby;
	private Lazienka lazienka;

	public Mezczyzna(Lazienka lazienka, int proby) {
		this.lazienka = lazienka;
		this.proby = proby;
	}

	@Override
	public void run() {
		try{
		for (int i = 0; i < proby; ++i) {
			lazienka.wchodziMezczyzna();
			System.out.println("M�czyzna wchodzi");
			Thread.yield();
			System.out.println("M�czyzna wychodzi");
			lazienka.wychodziMezczyzna();
		}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
