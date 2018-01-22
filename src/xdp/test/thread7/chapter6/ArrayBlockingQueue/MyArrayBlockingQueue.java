package xdp.test.thread7.chapter6.ArrayBlockingQueue;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5279755156958890832L;

	
	// 存储的元素
	private Object[] items;
	
	// 下一个take索引位置
	private int takeIndex;
	
	// 下一个put索引位置
	private int putIndex;
	
	// 数组包含的元素总数
	private int count;
	
	// 可重入锁
	private ReentrantLock lock;
	
	// 非满条件
	private Condition notFull;
	
	// 非空条件
	private Condition notEmpty;
	
	MyArrayBlockingQueue(int capacity){
		this(capacity, false);
	}
	
    MyArrayBlockingQueue(int capacity,boolean fair){
		if(capacity <=0){
			throw new IllegalArgumentException();
		}
		items = new Object[capacity];
		lock = new ReentrantLock(fair);
		notFull = lock.newCondition();
		notEmpty = lock.newCondition();
	}
	
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	// 阻塞式的获取,不支持中断
	@Override
	public boolean offer(E e) {
		checkNull(e);
		lock.lock();
		try{
			if(count == items.length){
				return false;
			}
			insert(e);
			return true;
		}finally{
			lock.unlock();
		}
	}
	
	@Override
	public E poll() {
		lock.lock();
		try{
			return (count == 0)?null:extract();
		}finally{
			lock.unlock();
		}
	}
	

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		checkNull(e);
		lock.lockInterruptibly();
		try{
			while(count == items.length){
				long nanos = unit.toNanos(timeout);
				if(nanos <=0){
					return false;
				}
				// 阻塞一段时间
				nanos = notFull.awaitNanos(nanos);
			}
			insert(e);
			notEmpty.signal();
			return true;
		}finally{
			lock.unlock();
		}
	}
	
	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		lock.lockInterruptibly();
		try{
			while(count == 0){
				long nanos = unit.toNanos(timeout);
				if(nanos <=0){
					return null;
				}
				nanos = notEmpty.awaitNanos(nanos);
			}
			E extract = extract();
			notFull.signal();
			return extract;
		}finally{
			lock.unlock();
		}
	}

	// 阻塞式的添加,可中断
	@Override
	public void put(E e) throws InterruptedException {
		checkNull(e);
		lock.lockInterruptibly();
		try{
			while(count == items.length){
				System.out.println("队列满了:count"+count);
				notFull.await();
				System.out.println("满队列被唤醒:count"+count);
			}
			insert(e);
			// 唤醒等待获取的线程
			notEmpty.signal();
		}finally{
			lock.unlock();
		}
		
	}
	
	@Override
	public E take() throws InterruptedException {
		lock.lockInterruptibly();
		try{
			while(count == 0){
				System.out.println("队列空了:count"+count);
				notEmpty.await();
				System.out.println("空队列被唤醒:count"+count);
			}
			E extract = extract();
			// 唤醒等待插入元素的线程
			notFull.signal();
			return extract;
		}finally{
			lock.unlock();
		}
	}

	
	// 必须获取到锁
	private void insert(E e) {
		items[putIndex] = e;
		putIndex = incrIndex(putIndex);
		count++;
	}
	// 必须获取到锁
	private int incrIndex(int index) {
		return (++index == items.length)?0:index;
	}

	private E extract() {
		Object obj = items[takeIndex];
		items[takeIndex] = null;
		takeIndex = incrIndex(takeIndex);
		count--;
		return (E)obj;
	}

	@Override
	public int remainingCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		lock.lock();
		try{
			return count;
		}finally{
			lock.unlock();
		}
	}
	
	private static void checkNull(Object o){
		if(o == null){
			throw new NullPointerException();
		}
	}

}
