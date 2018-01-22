package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.Random;
// ��������
public class MatrixMock {
	
	private int data[][];
	
	// ��ʼ�������к���
	public MatrixMock(int size,int length,int number){
		int conuter=0;// ��¼�ò�ѯ���ֵĸ���
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
		
		System.out.printf("Mock:�������%d��%d����.\n",conuter,number);
	}
	
	// ��ȡ�ڼ���
	public int[] getRow(int row){
		if((row>=0) && (row<data.length)){
			return data[row];
		}
		return null;
	}

}
