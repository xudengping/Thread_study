package xdp.test.thread7.chapter3.CyclicBarrier;

// ���������ÿ��ָ�����ֳ��ֵĴ���,�������������еĴ�����ӵõ����
public class Results {
	
	private int data[];
	
	public Results(int size){
		data = new int[size];
	}
	
	public void setData(int position,int value){
		data[position] = value;
	}
	
	public int[] getData(){
		return data;
	}

}
