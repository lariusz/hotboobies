package zestaw1;

public class Licznik {

	private volatile int wartosc;
	
	public void modyfikuj(int delta){
		if(delta>0){
			inkrementuj();
		}else{
			dekrementuj();
		}
	}
	
	public int dajWartosc(){
		return wartosc;	
	}
	
	public void inkrementuj(){
		wartosc++;		
	}
	
	public void dekrementuj(){	
		wartosc--;
	}

	public int getValue() {
		return wartosc;
	}
}
