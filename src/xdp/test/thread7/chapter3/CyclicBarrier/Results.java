package xdp.test.thread7.chapter3.CyclicBarrier;

// 保存矩阵中每行指定数字出现的次数,最后遍历该数组中的次数相加得到结果
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
