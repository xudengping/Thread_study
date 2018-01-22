package xdp.test.thread7.chapter7.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionUseCase {
	
	ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void await() throws InterruptedException{
		lock.lock();
		try{
			condition.await();
		}finally{
			lock.unlock();
		}
	}
	
	public void signal(){
		lock.lock();
		try{
			condition.signal();
		}finally{
			lock.unlock();
		}
	}

}
