package xdp.test.thread7.chapter6.ConcurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements Runnable{
	// �������̰߳�ȫ�б�
	private ConcurrentLinkedDeque<String> list;
	
	public PollTask(ConcurrentLinkedDeque<String> list){
		this.list = list;
	}

	@Override
	public void run() {
		for(int i=0;i<5000;i++){
			// ɾ���б�ĵ�һ��Ԫ��
			String pollFirst = list.pollFirst();
			System.out.println("pollFirst"+pollFirst);
			// ɾ���б�����һ��Ԫ��
			String pollLast = list.pollLast();
			System.out.println("pollLast"+pollLast);
			String pollFirst2 = list.pollFirst();
			System.out.println("pollFirst2"+pollFirst2);
		}
		
	}

}
