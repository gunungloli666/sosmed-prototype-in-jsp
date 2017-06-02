package media.sosial.bean.mapping;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CekPesanAction  extends HttpServlet{
	
	Thread thread;
	
	boolean sedangBaca = true; 
	
	BacaPesan bacaPesan; 

	class BacaPesan implements Runnable{
		PrintWriter out; 
		public BacaPesan(PrintWriter p) throws Exception{
			out  = p;
			out.write("\ninside constructor for baca pesan"); 
		}

		@Override
		public void run() {
			out.write("\njalankan method baca pesan"); 
			while(sedangBaca){
				try{
					out.write("makan"); 
					System.out.println("sedang makan"); 
					Thread.sleep(500); 
				}catch(Exception e){ }
			}
			out.close(); 
		}
		
		public void stopRead(){
			sedangBaca = false; 
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		boolean jalan = true; 
		int m = 0 ; 
		try{
			PrintWriter p = response.getWriter(); 
			while(jalan){
				// replace with chek db for unread message.. 
				if( m > 10){	// if thre are unread message arrive in db, stop loop
					jalan = false;  // and return response 
					p.write("\nbaca");
					p.close();
				}
				m = m + 1;  
				Thread.sleep(200); 
			}
		}catch(Exception e){
			
		}
		
	}
}
