package xdp.test.thread7.chapter7.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现一个共享式锁
 * @author dell
 *
 */
public class TwinsLock implements Lock{
	
	private Sync sync = new Sync(2);

	@Override
	public void lock() {
		sync.acquireShared(1);
	}
	
	@Override
	public void unlock() {
		sync.releaseShared(1);
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		int i = sync.tryAcquireShared(1);
		return i>=0?true:false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}
	
	private static final class Sync extends AbstractQueuedSynchronizer{
		
		public Sync(int count){
			if(count <0){
				throw new IllegalArgumentException("count must large than zero.");
			}
			// 设置出事同步状态
			setState(count);
		}
		
		// 自旋的减少同步状态
		@Override
		protected int tryAcquireShared(int reduce) {
			for(;;){
				int current = getState();
				int newCount = current-reduce;
				if(newCount <0 || compareAndSetState(current, newCount)){
					return newCount;
				}
			}
		}
		
		// 自旋的增加同步状态
		@Override
		protected boolean tryReleaseShared(int add) {
			for(;;){
				int current = getState();
				int newCount = current+add;
				if(compareAndSetState(current,newCount)){
					return true;
				}
			}
		}
		
		public Condition newCondition(){
			return new ConditionObject();
		}
	}

}
