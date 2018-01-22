package xdp.test.thread7.chapter3.semaphore.test2;

public class TestSemaphore {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore();
		
		SendingThread sendingThread = new SendingThread(semaphore);
		Thread t1 = new Thread(sendingThread);
		RecevingThread recevingThread = new RecevingThread(semaphore);
		Thread t2 = new Thread(recevingThread);
		
		t1.start();
		t2.start();

	}

}
