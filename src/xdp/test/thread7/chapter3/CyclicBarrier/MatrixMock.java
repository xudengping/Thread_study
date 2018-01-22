package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.Random;
// 矩阵数据
public class MatrixMock {
	
	private int data[][];
	
	// 初始化矩阵行和列
	public MatrixMock(int size,int length,int number){
		int conuter=0;// 记录该查询数字的个数
		data = new int[size][length];
		Random random = new Random();
		
		for(int i=0;i<size;i++){
			for(int j=0;j<length;j++){
				data[i][j] = random.nextInt(10);
				if(data[i][j] == number){
					conuter++;
				}
			}
		}
		
		System.out.printf("Mock:这里存在%d个%d数据.\n",conuter,number);
	}
	
	// 获取第几行
	public int[] getRow(int row){
		if((row>=0) && (row<data.length)){
			return data[row];
		}
		return null;
	}

}
