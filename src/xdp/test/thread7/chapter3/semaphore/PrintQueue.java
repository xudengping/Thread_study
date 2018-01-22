package xdp.test.thread7.chapter3.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ��Դ�Ķั����������
// ����һ��������Դ---��ӡ
public class PrintQueue {
	
	private  volatile int count = 0;

	private final Semaphore semaphore;
	private boolean[] fressPrinters;//PrintQueue�ĸ���
	private Lock lockPrinters;

	public PrintQueue() {
		// Ĭ�Ϸǹ�ƽģʽ
//		semaphore = new Semaphore(1);
//		semaphore = new Semaphore(1,true);
		
		semaphore = new Semaphore(3);// ͬʱ�������߳̿��Է��ʴ�ӡ���Ĵ�ӡ����
		fressPrinters = new boolean[3];// ��ʼ������PrintQueue�ĸ���
		for (int i=0;i<3;i++) {
			fressPrinters[i] = true;// ��Ϊ��ʹ��
		}
		lockPrinters = new ReentrantLock();
	}

	public void printJob(Object document) {
		try {
			// ��ȡ�ź���
			semaphore.acquire();
			int assignedPrinter = getPrinter();
//			long duration = (long)(Math.random()*10);
			long duration = 1l;
			System.out.printf("%s:PrintQueue:Printing a job in printer %d during %d second\n"
					,Thread.currentThread().getName(),assignedPrinter,duration);
//			Thread.sleep(duration);
			TimeUnit.SECONDS.sleep(duration);
			// ����assignedPrinter����ӡ����Ϊ����
			count++;
			fressPrinters[assignedPrinter] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			// �ͷ��ź���
			semaphore.release();
		}
	}

	private int getPrinter() {
		int ret = -1;
		try{
			lockPrinters.lock();
			for(int i=0;i<fressPrinters.length;i++){
				// �ҵ���һ��true
				if(fressPrinters[i]){
					ret = i;
					fressPrinters[i] = false;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			lockPrinters.unlock();
		}
		
		return ret;
	}
	
	public int getCount(){
		return count;
	}

}
