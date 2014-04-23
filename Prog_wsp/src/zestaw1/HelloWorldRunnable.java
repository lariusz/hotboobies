package zestaw1;

public class HelloWorldRunnable implements Runnable {

	int id;
	
	public HelloWorldRunnable(int id){
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Hello, world Runnable "+ id);

	}

	public static void main(String[] args) {
		Thread [] watki = new Thread[3];
		for (int i = 0; i < watki.length; i++) {
			watki[i] = new Thread(new HelloWorldRunnable(i));
			watki[i].start();
			
		}		
		try {
			for (int i = 0; i < watki.length; i++) {
				watki[i].join();
			}			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Koniec");

	}

}
