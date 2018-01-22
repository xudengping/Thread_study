package xdp.test.thread7.chapter3.semaphore;

public class TestSemaphore {
	

	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();
		Thread t[] = new Thread[10];
		for(int i=0;i<10;i++){
			t[i] = new Thread(new Job(printQueue),"Thread"+i);
		}
		
		for(int i=0;i<10;i++){
			t[i].start();
		}
		
		for(int i=0;i<10;i++){
			try {
				t[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	 System.out.println(printQueue.getCount());

	}

}
