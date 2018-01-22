package xdp.test.thread7.chapter4.ThreadPoolExecutor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator {
	
	private String name;
	
	public UserValidator(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean validate(String name,String passsword){
		Random random = new Random();
		try{
			long duration = (long) (Math.random()*10);
			System.out.printf("Validator %s: Validating a user during %d seconds\n",this.name,duration);
			TimeUnit.SECONDS.sleep(duration);
		}catch(InterruptedException e){
			e.printStackTrace();
			return false;
		}
		return random.nextBoolean();
	}

}
