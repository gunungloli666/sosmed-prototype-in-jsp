package media.sosial.bean.mapping.example;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("testEvent") 
public class PollingServlet extends HttpServlet{

	private final Random random = new Random( ); 
	private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();
	private final Thread generator = new Thread("Event Generator"){
		public void run(){
			println("Listening thread started"); 
			while(!Thread.currentThread().isInterrupted()){
				try{
					Thread.sleep(random.nextInt(8000));  
				}catch(Exception e){}
				println("inserting 1 messages into queue"); 
				messages.offer("At " + new Date()); 
			}
		}
	};
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	public static void println(String output){
		System.out.println("[" + Thread.currentThread().getName() + "]" + output);  
	}
}
