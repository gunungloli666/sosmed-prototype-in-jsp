package media.sosial.bean.mapping;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import media.sosial.dao.Write;

public class UpdateArticleAction  extends HttpServlet{ 
	
	private Write writer ; 
	
	public UpdateArticleAction(){
		writer  = Write.getNewInstance(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response); 
	}
	
	// tambahin passing list ke dalam servlet
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
			String content = request.getParameter("content");  
			boolean success= writer.writeArticle( content);  
			if(success){
				PrintWriter out = response.getWriter(); 
				out.write(Long.toString(writer.getId()));  
				out.close();
			}
		}catch(Exception e){
			System.out.println( "eror" );  
			e.printStackTrace(); 
		}
	}


}
