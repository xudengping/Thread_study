package xdp.test.thread7.chapter3.semaphore;

public class Job implements Runnable{
	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue){
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		System.out.printf("%s:Going to pring a job\n",Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s:The document has been printedd\n",Thread.currentThread().getName());
		
	}

}
