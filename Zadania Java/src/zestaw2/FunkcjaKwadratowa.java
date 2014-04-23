package zestaw2;

public class FunkcjaKwadratowa implements Wielomian {

	private int a;
	private int b;
	private int c;
	
	public FunkcjaKwadratowa(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	private int delta(){
		return b*b - 4*a*c;
	}
	
	
	@Override
	public void wypiszMiejscaZerowe() {
		if(delta()<0){
			System.out.println("brak");
		}
		else if(delta()==0){
			System.out.println(-b/(2*a));
		}
		else{
			System.out.println((int)(-b-Math.sqrt(delta())/2*a) + " " + (int)(-b+Math.sqrt(delta())/2*a));
		}

	}

}
