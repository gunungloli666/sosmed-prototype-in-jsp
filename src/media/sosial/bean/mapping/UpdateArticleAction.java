package media.sosial.bean.mapping;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import media.sosial.dao.xml.ModifyArticle;
import media.sosial.dao.xml.Schedule;
import media.sosial.dao.xml.Write;

public class UpdateArticleAction extends HttpServlet {

	private Write writer;
	
	private ModifyArticle modifyArticle; 

	public UpdateArticleAction() {
		writer = Write.getNewInstance();
		modifyArticle = ModifyArticle.getInstance(); 
	}
	
	private final Thread generator = new Thread(new Runnable() {
		@Override
		public void run() {
//			while(!Thread.currentThread().isInterrupted()){
//				try{
//					Thread.sleep(1000); 
//					System.out.println(Thread.currentThread().getName()); 
//				}catch(Exception e){
//					
//				}
//			}
		}
	}); 
	
	public Thread getNegerator( ){
		if(generator != null){
			return generator; 
		}
		return null; 
	}
	
	public void init() throws ServletException{
//		System.out.println("servlet update status started"); 
//		generator.start(); 
	}
	
	public void destroy(){
		System.out.println("servlet update status destroyed"); 
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	// tambahin passing list ke dalam servlet
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		contentUpdate(request, response);
	}
	
	public void contentUpdate(HttpServletRequest request  , HttpServletResponse response ) {
//		System.out.println("name thread: " +  Thread.currentThread().getName()); 
//		if(generator != null){
//			System.out.println("miss: " + generator.getName()); 
//		}
		try {
			String content = request.getParameter("content"); 
			if(content.contains("fuck")){
				try{
					response.sendError(HttpServletResponse.SC_FORBIDDEN); 
					return; 
				}catch(Exception e){}
			}
			boolean success = writer.writeArticle(content);
			if (success) {
				PrintWriter out = response.getWriter();
				out.write(Long.toString(writer.getId()));
				out.close();
			}
		} catch (Exception e) {
			System.out.println("eror");
			e.printStackTrace();
		}

	}

}
