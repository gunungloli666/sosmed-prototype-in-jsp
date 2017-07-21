package media.sosial.bean.mapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import media.sosial.dao.xml.ModifyArticle;

public class EditArticleAction extends HttpServlet {

	private ModifyArticle modifyArticle;

	public EditArticleAction() {
		modifyArticle = ModifyArticle.getInstance();
	}

	public void doPost(HttpServletRequest request , HttpServletResponse response ) {
		try {
			String content = request.getParameter("contentArticle");
			String id = request.getParameter("idArticle");
			modifyArticle.modifyArticle(id, content);
		} catch (Exception e) {

		}
	}

}
