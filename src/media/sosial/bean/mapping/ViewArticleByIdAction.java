package media.sosial.bean.mapping;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import media.sosial.dao.xml.ViewArticle;

public class ViewArticleByIdAction extends HttpServlet{
	
private ViewArticle viewArticle; 
	
	public  ViewArticleByIdAction() {
		// TODO Auto-generated constructor stub
		viewArticle = ViewArticle.getInstance(); 
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
			String id = request.getParameter( "idArticle" ); 
//			System.out.println(id); 
			String isi = viewArticle.getArticleById(id);  
//			System.out.println( isi ) ; 
			PrintWriter out = response.getWriter(); 
			out.write(isi ) ; 
			out.close();
		}catch(Exception e) {
			System.out.println("eror: " + e.toString()); 
			e.printStackTrace();
		}
	}
	

}
