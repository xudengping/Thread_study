package xdp.test.thread7.chapter4.ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// invokeAny ���������б����ʱû�����쳣�ĵ�һ��������
public class TestTaskValidator {

	public static void main(String[] args) {
		String username="test";
		String password="test";
		UserValidator ldapValidator=new UserValidator("LDAP");
		UserValidator dbValidator=new UserValidator("DataBase");
		
		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);
		List<TaskValidator> taskList=new ArrayList<TaskValidator>();
		taskList.add(ldapTask);
		taskList.add(dbTask);
		
		// ����ThreadPoolExecutor
		ExecutorService service = Executors.newCachedThreadPool();
		
		String result;
		
		try {
			// �������ʱû�����쳣�ĵ�һ��������
			result = service.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n",result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		service.shutdown();
		System.out.printf("Main: End of the Execution\n");




	}

}
