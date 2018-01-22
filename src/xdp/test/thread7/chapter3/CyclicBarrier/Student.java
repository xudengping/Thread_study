package xdp.test.thread7.chapter3.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

// 学生考试
public class Student implements Runnable{
	
	private CyclicBarrier barrier;
	
	public Student(CyclicBarrier  barrier){
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			// 第一步:等待其他同学到来
			System.out.printf("%s: 等待其他同学到来,看看环境!\n",Thread.currentThread().getName());
			barrier.await();// 等待其他同学到来
		} catch (Exception e) {
			barrier.reset();
		}
		try {
			// 第二步：开始考试第一题
			doFirstExam();
			barrier.await();// 等待其他同学都做完第一道题
		} catch (Exception e) {
			barrier.reset();
		}	
		try {
			// 第三步：开始考试第二题
			doSencondExam();
			barrier.await();// 等待其他同学都做完第二道题
		} catch (Exception e) {
			barrier.reset();
		}	
			// 第四步：考试结束，请交卷离开
			leaveClass();
	}

	private void leaveClass() {
		System.out.printf("%s: 终于考完了，离开喽，打篮球去了!\n",Thread.currentThread().getName());
		
	}

	private void doSencondExam() {
		System.out.printf("%s: 做第二道题,别打扰我!\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(2l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("学生2".equals(Thread.currentThread().getName())){
			int i =1/0;
		}
		
		System.out.printf("%s: 第二道题完成,等其他人都完成第二道题，我就可以离开教室，你们快点，咯咯~~~\n",Thread.currentThread().getName());
		
	}

	private void doFirstExam() {
		System.out.printf("%s: 做第一道题,别打扰我!\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(1l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("%s: 第一道题完成,等其他人都完成第一道题，我就可以开始做第二道了，他们好菜，咯咯~~~\n",Thread.currentThread().getName());
	}

}
