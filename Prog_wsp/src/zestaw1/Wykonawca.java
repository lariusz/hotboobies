package zestaw1;

public class Wykonawca implements Runnable {

	final Licznik licznik;
	int delta;
	int n;
	
	public Wykonawca(Licznik licznik, int n, int delta){
		this.licznik = licznik;
		this.delta = delta;
		this.n = n;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < n; i++) {
			licznik.modyfikuj(delta);
		}
		
	}

	public static void main(String[] args) {
		int n = 100;
		Licznik licznik = new Licznik();
		Thread[] watki = new Thread[1000];
		long start = System.currentTimeMillis();
		for (int i = 0; i < watki.length; i++) {
			if(i%2==0){
				watki[i] = new Thread(new Wykonawca(licznik, n, 1));
			}else{
				watki[i] = new Thread(new Wykonawca(licznik, n, -1));
			}
			watki[i].start();
		}
		try {
			for (Thread thread : watki) {
				thread.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long stop = System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName() + " " + licznik.dajWartosc() + "\nCzas: "+ (stop-start));
		
		
		
		}

}
