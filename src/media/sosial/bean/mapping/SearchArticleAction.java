package media.sosial.bean.mapping;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import media.sosial.dao.Article;
import media.sosial.dao.ViewArticle;

public class SearchArticleAction extends HttpServlet{ 
	
	private ViewArticle viewArticle; 
	
	public SearchArticleAction(){
		viewArticle = ViewArticle.getInstance(); 
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response){
		String keyword = request.getParameter("keyword").trim() ;  	
//		System.out.println(keyword); 
		try{
			List<Article> list = viewArticle.getArticleMatched(keyword ); 
			Gson gson  = new Gson(); 
			String str = gson.toJson( list ); 
//			System.out.println(str); 
			PrintWriter out = response.getWriter(); 
			out.write(str);  
			out.close();
		}catch(Exception e){
		}
	}
	
}
