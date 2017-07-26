package media.sosial.dao.xml;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import media.sosial.media.util.CommonName;

public class ViewArticle {
	
	static private ViewArticle instance ; 
	
	private ViewArticle(){}
	
	public static ViewArticle getInstance( ){
		if(instance == null){
			instance = new ViewArticle(); 
		}
		return instance; 
	}
	
	public List<Article> getAllArticle() throws Exception { 
		URL url = new URL(CommonName.URL);  
		URLConnection ucon = url.openConnection(); 
		Properties prop = new Properties(); 
		
		prop.load(ucon.getInputStream()); 
		String articlepath = prop.getProperty( CommonName.ARTICLE_PATH ) ;
		
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder dbuild = dbfac.newDocumentBuilder(); 
		Document doc = dbuild.parse(new FileInputStream(articlepath)); 
		
		NodeList nodelist = doc.getElementsByTagName("article"); 
		List<Article> listArticle = new ArrayList<>();
	
		for(int i = 0; i < nodelist.getLength(); i++){
		     Node n = nodelist.item(i);  
		     NamedNodeMap  nnp = n.getAttributes(); 
		     String title = nnp.getNamedItem("articleid").getNodeValue(); 
		    
		     if(title != null && ! title.isEmpty()){
		    	Article article = new Article(); 	
		     	article.setTitle(title);  
		     	NodeList nl2 = n.getChildNodes(); 
		     	Node n2 = nl2.item(1); 
		     	
		     	NamedNodeMap nnp2 = n2.getAttributes(); 
		     	String content = nnp2.getNamedItem("text").getNodeValue(); 
		     	content = content.replaceAll("\n", "<br/>" ); 
		     	article.setContent(content); // nanti ganti dengan isi dari artikel 
		     	listArticle.add(article);  
		     }
		}
		return listArticle; 
	}
	
	public List<Article> getArticleMatched( String searchKeyword ) throws Exception {
		
		URL url = new URL(CommonName.URL);  
		URLConnection ucon = url.openConnection(); 
		Properties prop = new Properties(); 
		
		prop.load(ucon.getInputStream()); 
		String articlepath = prop.getProperty( CommonName.ARTICLE_PATH ) ;
		
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder dbuild = dbfac.newDocumentBuilder(); 
		Document doc = dbuild.parse(new FileInputStream(articlepath)); 
		
		NodeList nodelist = doc.getElementsByTagName("article"); 
		List<Article> listArticle = new ArrayList<>();
		
		for(int i = 0; i < nodelist.getLength(); i++){
		     Node n = nodelist.item(i);  
		     NamedNodeMap  nnp = n.getAttributes(); 
		     String title = nnp.getNamedItem("articleid").getNodeValue(); 
		    
		     if(title != null && ! title.isEmpty()){
		    	Article article = new Article(); 	
		     	article.setTitle(title);  
		     	NodeList nl2 = n.getChildNodes(); 
		     	Node n2 = nl2.item(1); 
		     	
		     	NamedNodeMap nnp2 = n2.getAttributes(); 
		     	String content = nnp2.getNamedItem("text").getNodeValue(); 
		     	if(! searchKeyword.isEmpty()){
		     		if( content.toLowerCase().indexOf(searchKeyword.toLowerCase() , 0 ) != -1){
			     	 	article.setContent(content); // nanti ganti dengan isi dari artikel 		     	
				     	listArticle.add(article);  
			     	}
		     	}
		     }
		}
		return listArticle; 
	}
	
	
	public Article getArticleByID(){
		return null; 
	}
	
	
	
	public List<String> getTitleByNumber(){ 
		
		return null; 
	}
	
	public String getArticleById( String id) throws Exception { 
		URL url = new URL(CommonName.URL);  
		URLConnection ucon = url.openConnection(); 
		Properties prop = new Properties(); 
		
		prop.load(ucon.getInputStream()); 
		String articlepath = prop.getProperty( CommonName.ARTICLE_PATH ) ;
		
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder dbuild = dbfac.newDocumentBuilder(); 
		Document doc = dbuild.parse(new FileInputStream(articlepath)); 
		
		NodeList nodelist = doc.getElementsByTagName(CommonName.LIST_ARTICLE_TAG) ; 
		Node list1 = nodelist.item(0); 
		
		NodeList nl1 = list1.getChildNodes(); 
		for(int i =0 ; i < nl1.getLength(); i++){
			Node n2 = nl1.item(i);
			if(n2.getNodeName().equals("article")){
				NamedNodeMap nnp = n2.getAttributes(); 
				String idx = nnp.getNamedItem(CommonName.ARTICLE_ID).getNodeValue();
				if(id.equals(idx)) {
			     	NodeList nl2 = n2.getChildNodes(); 
			     	Node n22 = nl2.item(1); 
			     	NamedNodeMap nnp2 = n22.getAttributes(); 
			     	String content = nnp2.getNamedItem("text").getNodeValue(); 
					return content;
				}
			}
			
		}
		return "" ; 
	}
	
	
	public List<String> getAllTitle( ) throws Exception{
		URL url = new URL(CommonName.URL);  
		URLConnection ucon = url.openConnection(); 
		Properties prop = new Properties(); 
		
		prop.load(ucon.getInputStream()); 
		String articlepath = prop.getProperty( CommonName.ARTICLE_PATH ) ;
		
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder dbuild = dbfac.newDocumentBuilder(); 
		Document doc = dbuild.parse(new FileInputStream(articlepath)); 
		
		NodeList nodelist = doc.getElementsByTagName("article"); 
		List<String> listArticle = new ArrayList<>();
	
		for(int i = 0; i < nodelist.getLength(); i++){
		     Node n = nodelist.item(i);  
		     NamedNodeMap  nnp = n.getAttributes(); 
		     String title = nnp.getNamedItem("articleid").getNodeValue(); 
		     if(title != null && ! title.isEmpty()){
		     	listArticle.add(title);  
		     }
		}
		return listArticle; 
	}

}
