package xdp.test.thread7.chapter3.semaphore.test2;

public class BoundedSemaphore {
	
	private int signals = 0;// �ź���
	
	private int bound = 0;// �������
	
	public BoundedSemaphore(int bound){
		this.bound = bound;
	}
	
	public synchronized void take() throws InterruptedException{
		while(this.signals == bound){
			this.wait();
		}
		System.out.println("+++++��ȡ��BoundedSemaphore��");
		this.signals++;
		
		// do something
		
		this.notify();
	}
	
	public synchronized void release() throws InterruptedException{
		while(this.signals == 0){
			this.wait();
		}
		this.signals--;
		System.out.println("-----�ͷ�BoundedSemaphore��");
		// do something
//		this.notify();
	}

}
