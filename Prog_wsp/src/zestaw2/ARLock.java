package zestaw2;

public class ARLock {

	volatile boolean flagaAlicji;
	volatile boolean flagaRoberta;
	
	
	public void lockAlicja() {
		flagaAlicji = true;
		while(flagaRoberta==true){;}
		
	}
	public void unlockAlicja() {
		flagaAlicji = false;
	}
	
	
	public void lockRobert() {
		flagaRoberta = true;
		while(flagaAlicji==true){
			flagaRoberta = false;
			while(flagaAlicji==true){;}
			flagaRoberta = true;
		}
	}
	public void unlockRobert() {
		flagaRoberta = false;
	}
	
}
