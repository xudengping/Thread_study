package xdp.test.thread7.chapter6.SynchronousQueue;

/**
 * ʹ������֤put��take�����Ĵ��л�,ʵ��SynchronousQueue
 * @author dell
 *
 * @param <E>
 */
public class NativeSynchronousQueue<E> {
	
	private boolean putting = false;
	private E item = null;
	
	public synchronized E take() throws InterruptedException{
		while(item == null){
			// ��Ԫ��Ϊ���õ�ǰʵ��������ȴ�
			wait();
		}
		// �ܹ���ȡ��Ԫ��
		E e = item;
		// ȡ��Ԫ��֮��ɾ��Ԫ��
		item = null;
		notify();
		return e;
	}
	
	public synchronized void put(E e) throws InterruptedException{
		if(e == null){
			return;
		}
		// �����߳�����put
		while(putting){
			wait();
		}
		putting = true;
		item = e;
		notify();
	}
	
	

}
