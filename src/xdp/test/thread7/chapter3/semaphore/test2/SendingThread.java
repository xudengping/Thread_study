package xdp.test.thread7.chapter3.semaphore.test2;

public class SendingThread implements Runnable{
	
	private Semaphore semaphore = null;
	
	public SendingThread(Semaphore semaphore){
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		while(true){
			this.semaphore.take();
		}
	}

}
