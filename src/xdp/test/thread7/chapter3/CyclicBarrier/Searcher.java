package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// 查找某个数的出现次数的线程类
public class Searcher implements Runnable{
	
	// 决定查找子集的开始列号
	private int firstRow;
	
	// 决定查找子集的结束列号
	private int lastRow;
	
	// 要查找的目标源
	private MatrixMock mock;
	
	// 封装在指定列范围查找的每一行结果
	private Results results;
	
	// 要查找的数字
	private int number;
	
	// 栅栏
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
		System.out.printf("%s:查询行数从%d到%d.\n",Thread.currentThread().getName(),firstRow,lastRow);
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
		
		System.out.printf("%s:查询行结束,公找寻到%d个.\n",Thread.currentThread().getName(),searchCount);
		
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
