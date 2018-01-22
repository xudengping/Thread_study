package xdp.test.thread7.chapter3.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 资源的多副本并发控制
// 这是一个共享资源---打印
public class PrintQueue {
	
	private  volatile int count = 0;

	private final Semaphore semaphore;
	private boolean[] fressPrinters;//PrintQueue的副本
	private Lock lockPrinters;

	public PrintQueue() {
		// 默认非公平模式
//		semaphore = new Semaphore(1);
//		semaphore = new Semaphore(1,true);
		
		semaphore = new Semaphore(3);// 同时有三个线程可以访问打印机的打印功能
		fressPrinters = new boolean[3];// 初始化三个PrintQueue的副本
		for (int i=0;i<3;i++) {
			fressPrinters[i] = true;// 置为可使用
		}
		lockPrinters = new ReentrantLock();
	}

	public void printJob(Object document) {
		try {
			// 获取信号量
			semaphore.acquire();
			int assignedPrinter = getPrinter();
//			long duration = (long)(Math.random()*10);
			long duration = 1l;
			System.out.printf("%s:PrintQueue:Printing a job in printer %d during %d second\n"
					,Thread.currentThread().getName(),assignedPrinter,duration);
//			Thread.sleep(duration);
			TimeUnit.SECONDS.sleep(duration);
			// 将第assignedPrinter个打印机置为可用
			count++;
			fressPrinters[assignedPrinter] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			// 释放信号量
			semaphore.release();
		}
	}

	private int getPrinter() {
		int ret = -1;
		try{
			lockPrinters.lock();
			for(int i=0;i<fressPrinters.length;i++){
				// 找到第一个true
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
