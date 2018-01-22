package xdp.test.thread7.chapter2.jmm;

public class VolatileDoubleCheck {
	
	private volatile static  Instance instance = null;// Ω˚÷π÷∏¡Ó÷ÿ≈≈
	
	private VolatileDoubleCheck(){}
	
	public static Instance getInstance(){
		if(instance == null){
			synchronized (VolatileDoubleCheck.class) {
				if(instance == null){
					instance = new Instance();
				}
			}
		}
		return instance;
	}
	

}
