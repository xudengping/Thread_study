package xdp.test.thread7.chapter3.Phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable{
	
	private String initPath;// 查找的文件夹
	
	private String end;// 查找的文件扩展名
	
	private List<String> results;// 存储查找到的文件的绝对路径
	
	private Phaser phaser;// 控制任务不同阶段的同步
	
	public  FileSearch(String initPath,String end,Phaser phaser) {
		this.initPath = initPath;
		this.end = end;
		this.phaser = phaser;
		this.results = new ArrayList<String>();
	}
	
	private void directoryProcess(File file){
		File[] listFiles = file.listFiles();
		if(listFiles != null){
			for(int i=0;i<listFiles.length;i++){
				if(listFiles[i].isDirectory()){
					// 递归调用
					directoryProcess(listFiles[i]);
				}else{
					fileProcess(listFiles[i]);
				}
			}
		}
		
	}
	
	

	// 判断文件的文件后缀名匹不匹配
	private void fileProcess(File file) {
		if(file.getName().endsWith(end)){
			results.add(file.getAbsolutePath());
		}
		
	}
	
	// 判断文件的最后修改日期超不超过24小时
	private void filterResults(){
		List<String> newResults = new ArrayList<String>();
		long time = new Date().getTime();
		for(int i=0;i<results.size();i++){
			File file = new File(results.get(i));
			long lastModified = file.lastModified();
			if(time-lastModified<TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)){
				newResults.add(results.get(i));
			}
		}
		results = newResults;
	}
	
	private boolean checkResults(){
		if(results.isEmpty()){
			System.out.printf("%s:Phaser %d:0 results ,then end.\n",Thread.currentThread().getName(),phaser.getPhase());
			// 当前线程已经结束，将不再参与接下来的操作
			phaser.arriveAndDeregister();
			System.out.printf("%s:Phaser %d:0 results ,then end.\n",Thread.currentThread().getName(),phaser.getPhase());
			return false;
		}else{
			System.out.printf("%s:Phaser %d:%d results\n",Thread.currentThread().getName(),phaser.getPhase(),results.size());
			// 当前线程已经完成这个阶段任务，需要被阻塞等待其他线程都完成这个阶段
		    phaser.arriveAndAwaitAdvance();
		    return true;
		}
	}
	
	private void showInfo(){
		for(int i=0;i<results.size();i++){
			File file = new File(results.get(i));
			System.out.printf("%s: %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
			
		}
		phaser.arriveAndAwaitAdvance();
	}

	@Override
	public void run() {
		// 阻塞查找工作开始直到所有线程都创建完成
		phaser.arriveAndAwaitAdvance();
		System.out.printf("%s: Starting.\n",Thread.currentThread().getName());
		File file = new File(initPath);
		if(file.isDirectory()){
			directoryProcess(file);
		}
		// 查看results是否为空，如果为空结束当前线程
		if(!checkResults()){
			return;
		}
		// 过滤结果集：文件的修改日期是否没有超过24小时
		filterResults();
		if(!checkResults()){
			return;
		}
		// 打印
		showInfo();
		// 撤销线程的注册
		phaser.arriveAndDeregister();
		System.out.printf("%s: Work completed.\n",Thread.currentThread().getName());
	}

}
