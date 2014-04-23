package zestaw1;

class Obliczenia implements Runnable {
	
	private long wynik = 0;
	private final long ile;
	
	public Obliczenia(long ile) {
		this.ile = ile;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			wynik +=1;
			}		 		
	}

	public long dajWynik() {
		return wynik;
	}

	/* Zwraca postêp obliczeñ jako liczbê od 0.0 do 1.0 */
	public double dajPostep() {
		return (double)wynik/ile*100;
	}

	/* Zwraca true je¿eli obliczenia zosta³y zakoñczone / przerwane */
	public boolean czyKoniec() {
		if(dajPostep() == 100.0){
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		
		long ile = 10000000000L;
		Obliczenia ob = new Obliczenia(ile);
		Thread obliczenia = new Thread(ob);
		obliczenia.start();
		
		while(ob.dajPostep()<50){
			try {
				System.out.println("Postêp: " + ob.dajPostep());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		obliczenia.interrupt();
		System.out.println("Obliczenia przerwane"+"\nWynik: "+ ob.dajWynik());		
	}


}