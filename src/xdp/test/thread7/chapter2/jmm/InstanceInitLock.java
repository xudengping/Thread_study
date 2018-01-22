package xdp.test.thread7.chapter2.jmm;

public class InstanceInitLock {
	
	private static class InstanceHolder{
		private static Instance instance = new Instance();
	} 
	
	private InstanceInitLock(){}
	
	public static Instance getInstance(){
		return InstanceHolder.instance;
	}

}
