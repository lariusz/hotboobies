package zestaw4;

public class ProblemCiP {

	public static void main(String[] args) throws InterruptedException {
		final int n = 100;
		final int iluPisarzy = 2;
		final int iluCzytelnikow = 10;
		final CzytelniaLock czytelnia = new CzytelniaLock();
		Thread pisarze[] = new Thread[iluPisarzy];
		for (int i = 0; i < iluPisarzy; i++)
			pisarze[i] = new Thread(new Pisarz(czytelnia, i, n));
		Thread czytelnicy[] = new Thread[iluCzytelnikow];
		for (int i = 0; i < iluCzytelnikow; i++)
			czytelnicy[i] = new Thread(new Czytelnik(czytelnia, i, n));
		for (Thread watek : pisarze) {
			watek.start();
		}
		for (Thread watek : czytelnicy) {
			watek.start();
		}
		for (Thread watek : pisarze) {
			watek.join();
		}
		for (Thread watek : czytelnicy) {
			watek.join();
		}
	}

}
