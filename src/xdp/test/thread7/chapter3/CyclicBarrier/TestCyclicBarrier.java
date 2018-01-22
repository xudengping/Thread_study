package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	
	public static void main(String[] args){
		final int ROWS =  10000;// 1w��
		final int NUMBERS =  1000;// 1k��
		final int SEARCH =  5;// Ҫ���ҵ�����
		final int PARTICIPANTS =  5;// �ָ��5���̲߳���ִ��
		final int LINE_PARTICIPANTS=  2000;// ÿ���̼߳���2k�е��Ӽ�
		
		
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
		System.out.printf("Min:���߳̽���.\n");
	}

}
