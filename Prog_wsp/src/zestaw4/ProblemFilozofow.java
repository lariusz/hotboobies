package zestaw4;

public class ProblemFilozofow {

	public static void main(String[] args) throws InterruptedException {
	int n = 100;
	final Jadalnia jadalnia = new Jadalnia();
	Thread filozofowie[] = new Thread[5];
	
	for (int i = 0; i < 5; i++){
		filozofowie[i] = new Thread(new Filozof(jadalnia, i, n));
	}
	for (Thread watek : filozofowie) {
		watek.start();
	}
	for (Thread watek : filozofowie) {
		watek.join();
	}
}
}
