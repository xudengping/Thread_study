package xdp.test.thread7.chapter6.SynchronousQueue;

/**
 * 使用锁保证put和take操作的串行化,实现SynchronousQueue
 * @author dell
 *
 * @param <E>
 */
public class NativeSynchronousQueue<E> {
	
	private boolean putting = false;
	private E item = null;
	
	public synchronized E take() throws InterruptedException{
		while(item == null){
			// 当元素为空让当前实例化对象等待
			wait();
		}
		// 能够获取到元素
		E e = item;
		// 取出元素之后删除元素
		item = null;
		notify();
		return e;
	}
	
	public synchronized void put(E e) throws InterruptedException{
		if(e == null){
			return;
		}
		// 当有线程正在put
		while(putting){
			wait();
		}
		putting = true;
		item = e;
		notify();
	}
	
	

}
