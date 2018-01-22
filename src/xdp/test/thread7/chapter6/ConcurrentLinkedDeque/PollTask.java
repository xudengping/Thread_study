package xdp.test.thread7.chapter6.ConcurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements Runnable{
	// 非阻塞线程安全列表
	private ConcurrentLinkedDeque<String> list;
	
	public PollTask(ConcurrentLinkedDeque<String> list){
		this.list = list;
	}

	@Override
	public void run() {
		for(int i=0;i<5000;i++){
			// 删除列表的第一个元素
			String pollFirst = list.pollFirst();
			System.out.println("pollFirst"+pollFirst);
			// 删除列表的最后一个元素
			String pollLast = list.pollLast();
			System.out.println("pollLast"+pollLast);
			String pollFirst2 = list.pollFirst();
			System.out.println("pollFirst2"+pollFirst2);
		}
		
	}

}
