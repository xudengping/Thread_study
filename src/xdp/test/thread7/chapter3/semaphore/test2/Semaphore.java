package xdp.test.thread7.chapter3.semaphore.test2;

import java.util.concurrent.TimeUnit;

public class Semaphore {
	// �Ƿ��ȡ������źű�ʶ
	private boolean signal = false;
	
	public synchronized void take(){
		this.signal = true;
		System.out.println("���ѵȴ�Semaphore���߳�");
		try {
			TimeUnit.SECONDS.sleep(1l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.notify();
	}
	
	public synchronized void release() throws InterruptedException{
		while(!this.signal){
			System.out.println("�ȴ�Semaphore��...");
			this.wait();
			System.out.println("�����̻߳��ѣ���ȡ��Semaphore��");
		}
		try {
			TimeUnit.SECONDS.sleep(1l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.signal = false;
	}

}
