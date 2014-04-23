package zestaw1;

public class HelloWorld extends Thread {
	
	int id;
	
	public HelloWorld(int id){
		this.id = id;
	}
	
	public void run(){
		System.out.println("Hello, world "+ id);
	}
	
	
	public static void main(String[] args){		
		Thread [] hw = new Thread[3];
		for (int i = 0; i < 3; i++) {
			hw[i] = new HelloWorld(i);
			hw[i].start();
		}		
		try {
			for (int i = 0; i < hw.length; i++) {
				hw[i].join();
			}			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Koniec");
	}
}
