package media.sosial.bean.mapping;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import media.sosial.dao.Article;
import media.sosial.dao.ViewArticle;

public class ViewArticleAction  extends HttpServlet{ 
	
	private ViewArticle viewArticle; 
	
	public ViewArticleAction(){
		viewArticle = ViewArticle.getInstance(); 
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response); 
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
			List<Article > listArticle = viewArticle.getAllArticle(); 
			request.setAttribute("listArticle", listArticle ); 
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/ViewArticle.jsp" );
			rd.forward(request, response);  
		}catch(Exception e){
			System.out.println( "eror: " + e); 
		}
	}


}
