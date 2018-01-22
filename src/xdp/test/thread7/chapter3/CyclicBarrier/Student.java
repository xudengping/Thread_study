package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

// ѧ������
public class Student implements Runnable{
	
	private CyclicBarrier barrier;
	
	public Student(CyclicBarrier  barrier){
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			// ��һ��:�ȴ�����ͬѧ����
			System.out.printf("%s: �ȴ�����ͬѧ����,��������!\n",Thread.currentThread().getName());
			barrier.await();// �ȴ�����ͬѧ����
		} catch (Exception e) {
			barrier.reset();
		}
		try {
			// �ڶ�������ʼ���Ե�һ��
			doFirstExam();
			barrier.await();// �ȴ�����ͬѧ�������һ����
		} catch (Exception e) {
			barrier.reset();
		}	
		try {
			// ����������ʼ���Եڶ���
			doSencondExam();
			barrier.await();// �ȴ�����ͬѧ������ڶ�����
		} catch (Exception e) {
			barrier.reset();
		}	
			// ���Ĳ������Խ������뽻���뿪
			leaveClass();
	}

	private void leaveClass() {
		System.out.printf("%s: ���ڿ����ˣ��뿪ඣ�������ȥ��!\n",Thread.currentThread().getName());
		
	}

	private void doSencondExam() {
		System.out.printf("%s: ���ڶ�����,�������!\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(2l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("ѧ��2".equals(Thread.currentThread().getName())){
			int i =1/0;
		}
		
		System.out.printf("%s: �ڶ��������,�������˶���ɵڶ����⣬�ҾͿ����뿪���ң����ǿ�㣬����~~~\n",Thread.currentThread().getName());
		
	}

	private void doFirstExam() {
		System.out.printf("%s: ����һ����,�������!\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(1l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("%s: ��һ�������,�������˶���ɵ�һ���⣬�ҾͿ��Կ�ʼ���ڶ����ˣ����Ǻòˣ�����~~~\n",Thread.currentThread().getName());
	}

}
