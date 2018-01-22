package xdp.test.thread7.chapter6.ConcurrentNavigableMap;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {

	public static void main(String[] args) {
		ConcurrentSkipListMap<String,Contact> map = new ConcurrentSkipListMap<String,Contact>();
		Thread threads[]=new Thread[25];
		int counter=0;
		for (char i='A'; i<'Z'; i++) {

			Task task = new Task(String.valueOf(i), map);
			threads[counter]=new Thread(task);
			threads[counter].start();
			counter++;
		}
		
		for (int i=0; i<25; i++) {
			try {
			threads[i].join();
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
			}
		
		System.out.printf("Main: Size of the map: %d\n",map.size());
		Entry<String, Contact> element = map.firstEntry();
		Contact contact = element.getValue();
		System.out.printf("Main: First Entry: %s: %s\n",contact.getName(),contact.getPhone());
		
		element=map.lastEntry();
		contact = element.getValue();
		System.out.printf("Main: Last Entry: %s: %s\n",contact.getName(),contact.getPhone());
		
		ConcurrentNavigableMap<String, Contact> subMap = map.subMap("A1996", "B1002");
		
		do{
			element = subMap.pollFirstEntry();
			 if(element != null){
				 contact=element.getValue();
				 System.out.printf("%s: %s\n",contact.getName(),contact.getPhone());
			 }
		}while(element != null);
		









	}

}
