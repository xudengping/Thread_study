package xdp.test.thread7.chapter3.Phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable{
	
	private Phaser phaser;
	
	public Student(Phaser phaser){
		this.phaser = phaser;
	}

	@Override
	public void run() {
		System.out.printf("%s���Ѿ����￼���ֳ���׼������\n",Thread.currentThread().getName());;
		phaser.arriveAndAwaitAdvance();// ����
		System.out.printf("%s����ʼ���Ե�һ����\n",Thread.currentThread().getName());
		doExercise1();
		System.out.printf("%s����һ�������\n",Thread.currentThread().getName());
		phaser.arriveAndAwaitAdvance();// ����
		System.out.printf("%s����ʼ���Եڶ�����\n",Thread.currentThread().getName());
		doExercise2();
		System.out.printf("%s���ڶ��������\n",Thread.currentThread().getName());
		phaser.arriveAndAwaitAdvance();// ����
		System.out.printf("%s����ʼ���Ե�������\n",Thread.currentThread().getName());
		doExercise3();
		System.out.printf("%s�������������\n",Thread.currentThread().getName());
		phaser.arriveAndAwaitAdvance();// ����
		System.out.printf("%s����������Կ���\n",Thread.currentThread().getName());
	}

	private void doExercise3() {
		long duratio  = (long)(Math.random()*10);
		try {
			TimeUnit.SECONDS.sleep(duratio);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doExercise2() {
		long duratio  = (long)(Math.random()*10);
		try {
			TimeUnit.SECONDS.sleep(duratio);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doExercise1() {
		long duratio  = (long)(Math.random()*10);
		try {
			TimeUnit.SECONDS.sleep(duratio);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
