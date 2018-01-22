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
		System.out.printf("%s：已经到达考试现场，准备考试\n",Thread.currentThread().getName());;
		phaser.arriveAndAwaitAdvance();// 阻塞
		System.out.printf("%s：开始考试第一道题\n",Thread.currentThread().getName());
		doExercise1();
		System.out.printf("%s：第一道题完成\n",Thread.currentThread().getName());
		phaser.arriveAndAwaitAdvance();// 阻塞
		System.out.printf("%s：开始考试第二道题\n",Thread.currentThread().getName());
		doExercise2();
		System.out.printf("%s：第二道题完成\n",Thread.currentThread().getName());
		phaser.arriveAndAwaitAdvance();// 阻塞
		System.out.printf("%s：开始考试第三道题\n",Thread.currentThread().getName());
		doExercise3();
		System.out.printf("%s：第三道题完成\n",Thread.currentThread().getName());
		phaser.arriveAndAwaitAdvance();// 阻塞
		System.out.printf("%s：完成了所以考试\n",Thread.currentThread().getName());
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
