package zestaw2;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;

class BakeryLock implements MyLock {
	AtomicIntegerArray flags;
	AtomicLongArray tickets;
	final int threadsCount;
	final static int TRUE = 1;
	final static int FALSE = 0;
	
	public BakeryLock(int threadsCount) {
		this.threadsCount = threadsCount;
		flags = new AtomicIntegerArray(threadsCount);
		tickets = new AtomicLongArray(threadsCount);
	}
	public void lock() {
		final int id = ThreadID.get();
		flags.set(id, TRUE);
		long max = 0;
		for (int i = 0; i < tickets.length(); i++) {
			if(tickets.get(i)>max){
				max = tickets.get(i);
			}
		}
		tickets.set(id, max+1);
		
		while(true){
			for (int i = 0; i < tickets.length(); i++) {
				if(flags.get(id)==TRUE){
					if(tickets.get(i) == tickets.get(id)){
						if(i<id) break;
					}else if (tickets.get(i) < tickets.get(id))	break;
					else{;}
				}
			}			
		}
	}
	
	public void unlock() {
		final int id = ThreadID.get();
		flags.set(id, FALSE);
	}
}