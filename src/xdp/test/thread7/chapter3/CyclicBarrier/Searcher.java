package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// ����ĳ�����ĳ��ִ������߳���
public class Searcher implements Runnable{
	
	// ���������Ӽ��Ŀ�ʼ�к�
	private int firstRow;
	
	// ���������Ӽ��Ľ����к�
	private int lastRow;
	
	// Ҫ���ҵ�Ŀ��Դ
	private MatrixMock mock;
	
	// ��װ��ָ���з�Χ���ҵ�ÿһ�н��
	private Results results;
	
	// Ҫ���ҵ�����
	private int number;
	
	// դ��
	private final CyclicBarrier barrier;
	
	public Searcher(int firstRow, int lastRow,MatrixMock mock,Results results,int number,CyclicBarrier barrier){
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.mock = mock;
		this.results = results;
		this.number = number;
		this.barrier = barrier;
	}
	

	@Override
	public void run() {
		int searchCount = 0;
		int counter;
		System.out.printf("%s:��ѯ������%d��%d.\n",Thread.currentThread().getName(),firstRow,lastRow);
		for(int i=firstRow;i<lastRow;i++){
			int[] row = mock.getRow(i);
			counter = 0;
			for(int j=0;j<row.length;j++){
				if(row[j] == number){
					counter++;
				}
			}
			searchCount += counter;
			results.setData(i, counter);
		}
		
		System.out.printf("%s:��ѯ�н���,����Ѱ��%d��.\n",Thread.currentThread().getName(),searchCount);
		
		//
		try {
			barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
