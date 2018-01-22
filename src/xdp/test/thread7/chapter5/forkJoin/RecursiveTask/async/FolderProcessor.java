package xdp.test.thread7.chapter5.forkJoin.RecursiveTask.async;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>>{

	private static final long serialVersionUID = -506383561066564674L;
	
	private String path;
	
	private String extension;
	
	public FolderProcessor(String path,String extension){
		this.path = path;
		this.extension = extension;
	}

	@Override
	protected List<String> compute() {
		List<String> list=new ArrayList<>();// 存储符合条件的文件
		List<FolderProcessor> tasks=new ArrayList<>();
		
		File file = new File(path);
		File[] content = file.listFiles();
		if(content != null){
			for (int i=0;i<content.length;i++) {
				if(content[i].isDirectory()){
					FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
					task.fork();// 异步提交给ForkJoinPool
					tasks.add(task);
				}else{
					if(checkFile(content[i].getName())){
						list.add(content[i].getAbsolutePath());
					}
				}
			}
		}
		
		if(tasks.size()>50){
			System.out.printf("%s: %d tasks ran.\n",file.getAbsolutePath(),tasks.size());
		}
		
		// 处理task中的返回结果文件
		addResultsFromTasks(list,tasks);

		return list;
	}

	private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
		for (FolderProcessor task : tasks) {
			// 等待任务结束并获取结果
			list.addAll(task.join());
//			try {
//				list.addAll(task.get());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
	}

	private boolean checkFile(String name) {
		if(name.endsWith("ini")){
			throw new RuntimeException(name+" end wihth ini.\n");
//			Exception e=new Exception("This task throws an Exception: "+name);
//            completeExceptionally(e);
		}
		return name.endsWith(extension);
	}

}
