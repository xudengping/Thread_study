package xdp.test.thread7.chapter7.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现一个独占锁
 * @author dell
 *
 */
public class Mutex implements Lock{
	
	// 同步器
	private final Sync sync = new Sync();
	
	public boolean isLocked() {
		return sync.isHeldExclusively();
	}

	@Override
	public void lock() {
		sync.acquire(1);
	}
	
	// 非阻塞的获取锁
	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}
	
	// 非阻塞的获取锁，timeout
	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	// 可响应中断的获取锁
	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	// 是否锁
	@Override
	public void unlock() {
		// 会调用sync的tryRelease方法
		sync.release(1);
		
	}
	
	public boolean hasQueuedThreads(){
		return sync.hasQueuedThreads();
	}
	

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}
	
	// 静态内部类，自定义同步器实现
	private static class Sync extends AbstractQueuedSynchronizer{
		
		// 是否处于占有状态
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		
		// 当状态为0的时候获取锁
		@Override
		protected boolean tryAcquire(int acquire) {
			if(compareAndSetState(0, acquire)){
				// 设置当前线程占有该锁
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		// 释放锁，将同步器状态设置为0
		@Override
		protected boolean tryRelease(int release) {
			if(getState() == 0){
				throw new IllegalMonitorStateException();
			}
			// 设置占有锁的线程为空
			setExclusiveOwnerThread(null);
			// 设置同步器状态
			setState(0);
			return true;
		}
		
		// 返回一个Condition
		Condition newCondition(){
			return new ConditionObject();
		}
		
	}

}
