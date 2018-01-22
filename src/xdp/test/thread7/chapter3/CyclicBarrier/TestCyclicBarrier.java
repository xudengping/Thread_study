package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	
	public static void main(String[] args){
		final int ROWS =  10000;// 1w行
		final int NUMBERS =  1000;// 1k列
		final int SEARCH =  5;// 要查找的数字
		final int PARTICIPANTS =  5;// 分割成5个线程并行执行
		final int LINE_PARTICIPANTS=  2000;// 每个线程计算2k行的子集
		
		
		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);
		
		Results results = new Results(ROWS);
		
		Grouper grouper = new Grouper(results);
		
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS,grouper);
		
		Searcher searchers[] = new Searcher[PARTICIPANTS];
		
		for(int i=0;i<PARTICIPANTS;i++){
			searchers[i] = new Searcher(i*LINE_PARTICIPANTS, (i*LINE_PARTICIPANTS)+LINE_PARTICIPANTS, mock, results, 5, barrier);
		    Thread t = new Thread(searchers[i]);
		    t.start();
		}
		System.out.printf("Min:主线程结束.\n");
	}

}
