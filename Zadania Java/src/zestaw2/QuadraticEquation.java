package zestaw2;

public class QuadraticEquation {

	private int a;
	private int b;
	private int c;
	
	public QuadraticEquation(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	private int delta(int a, int b, int c){
		return b*b - 4*a*c;
	}
	
	public int numberOfElemental(){
		if(delta(a, b, c)<0){
			return 0;
		}
		else if(delta(a, b, c)==0){
			return 1;
		}
		else{
		return 2;	
		}
	}

	public double calculateValue(double x){		
		return a*x*x + b*x + c;
	}
	
	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public void setC(int c) {
		this.c = c;
	}
	
}
