package media.sosial.bean.mapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import media.sosial.dao.xml.DeleteArticle;

public class HapusArticleAction extends HttpServlet{ 
	
	private DeleteArticle delete; 
	
	public HapusArticleAction(){
		delete = DeleteArticle.getNewInstance(); 
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response){
		try{
			String id = request.getParameter("idArticle");  
			delete.deleteArticle(id); 
		}catch(Exception e){
			
		}
	}
}
