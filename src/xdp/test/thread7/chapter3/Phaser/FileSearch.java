package xdp.test.thread7.chapter3.Phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable{
	
	private String initPath;// ���ҵ��ļ���
	
	private String end;// ���ҵ��ļ���չ��
	
	private List<String> results;// �洢���ҵ����ļ��ľ���·��
	
	private Phaser phaser;// ��������ͬ�׶ε�ͬ��
	
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
					// �ݹ����
					directoryProcess(listFiles[i]);
				}else{
					fileProcess(listFiles[i]);
				}
			}
		}
		
	}
	
	

	// �ж��ļ����ļ���׺��ƥ��ƥ��
	private void fileProcess(File file) {
		if(file.getName().endsWith(end)){
			results.add(file.getAbsolutePath());
		}
		
	}
	
	// �ж��ļ�������޸����ڳ�������24Сʱ
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
			// ��ǰ�߳��Ѿ������������ٲ���������Ĳ���
			phaser.arriveAndDeregister();
			System.out.printf("%s:Phaser %d:0 results ,then end.\n",Thread.currentThread().getName(),phaser.getPhase());
			return false;
		}else{
			System.out.printf("%s:Phaser %d:%d results\n",Thread.currentThread().getName(),phaser.getPhase(),results.size());
			// ��ǰ�߳��Ѿ��������׶�������Ҫ�������ȴ������̶߳��������׶�
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
		// �������ҹ�����ʼֱ�������̶߳��������
		phaser.arriveAndAwaitAdvance();
		System.out.printf("%s: Starting.\n",Thread.currentThread().getName());
		File file = new File(initPath);
		if(file.isDirectory()){
			directoryProcess(file);
		}
		// �鿴results�Ƿ�Ϊ�գ����Ϊ�ս�����ǰ�߳�
		if(!checkResults()){
			return;
		}
		// ���˽�������ļ����޸������Ƿ�û�г���24Сʱ
		filterResults();
		if(!checkResults()){
			return;
		}
		// ��ӡ
		showInfo();
		// �����̵߳�ע��
		phaser.arriveAndDeregister();
		System.out.printf("%s: Work completed.\n",Thread.currentThread().getName());
	}

}
