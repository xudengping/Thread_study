package xdp.test.thread7.chapter3.semaphore.test2;

public class RecevingThread implements Runnable {
	private Semaphore semaphore = null;
	
	public RecevingThread(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		while(true){
			try {
				this.semaphore.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
