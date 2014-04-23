package zestaw2;

class ThreadID {
	private static volatile int nextID = 0;
	private static ThreadLocalID threadID = new ThreadLocalID();

	public static int get() {
		return (int) threadID.get();
	}

	public static void reset() {
		nextID = 0;
	}

	private static class ThreadLocalID extends ThreadLocal<Integer> {
		protected synchronized Integer initialValue() {
			return nextID++;
		}
	}
}
