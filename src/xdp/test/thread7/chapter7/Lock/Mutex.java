package xdp.test.thread7.chapter7.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * ʵ��һ����ռ��
 * @author dell
 *
 */
public class Mutex implements Lock{
	
	// ͬ����
	private final Sync sync = new Sync();
	
	public boolean isLocked() {
		return sync.isHeldExclusively();
	}

	@Override
	public void lock() {
		sync.acquire(1);
	}
	
	// �������Ļ�ȡ��
	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}
	
	// �������Ļ�ȡ����timeout
	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	// ����Ӧ�жϵĻ�ȡ��
	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	// �Ƿ���
	@Override
	public void unlock() {
		// �����sync��tryRelease����
		sync.release(1);
		
	}
	
	public boolean hasQueuedThreads(){
		return sync.hasQueuedThreads();
	}
	

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}
	
	// ��̬�ڲ��࣬�Զ���ͬ����ʵ��
	private static class Sync extends AbstractQueuedSynchronizer{
		
		// �Ƿ���ռ��״̬
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		
		// ��״̬Ϊ0��ʱ���ȡ��
		@Override
		protected boolean tryAcquire(int acquire) {
			if(compareAndSetState(0, acquire)){
				// ���õ�ǰ�߳�ռ�и���
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		// �ͷ�������ͬ����״̬����Ϊ0
		@Override
		protected boolean tryRelease(int release) {
			if(getState() == 0){
				throw new IllegalMonitorStateException();
			}
			// ����ռ�������߳�Ϊ��
			setExclusiveOwnerThread(null);
			// ����ͬ����״̬
			setState(0);
			return true;
		}
		
		// ����һ��Condition
		Condition newCondition(){
			return new ConditionObject();
		}
		
	}

}
