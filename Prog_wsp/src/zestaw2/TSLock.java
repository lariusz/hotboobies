package zestaw2;

import java.util.concurrent.atomic.AtomicBoolean;

public class TSLock implements MyLock {

	AtomicBoolean wants = new AtomicBoolean();
	@Override
	public void lock() {
		while(wants.getAndSet(true) == true) {} 
		
	}

	@Override
	public void unlock() {
		wants.set(false);
	}

}
