package media.sosial.dao.xml;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import media.sosial.media.util.CommonName;

public class DeleteArticle {
	
	private static DeleteArticle delete; 
	
	private DeleteArticle(){	 }
	
	public static DeleteArticle getNewInstance(){
		if( delete == null){
			delete= new DeleteArticle(); 
		}
		return delete; 
	}
	
	
	public boolean deleteArticle(String id)throws Exception {
		boolean sukses = false; 
		
		URL url = new URL(CommonName.URL); 
		URLConnection ucon = url.openConnection(); 
		Properties prop = new Properties(); 
		
		prop.load(ucon.getInputStream()); 
		String articlepath = prop.getProperty(CommonName.ARTICLE_PATH ); 
		
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder dbbuilder = dbfac.newDocumentBuilder();
		Document doc = dbbuilder.parse(new FileInputStream(articlepath)); 
		
		NodeList nodelist = doc.getElementsByTagName(CommonName.LIST_ARTICLE_TAG) ; 
		Node list1 = nodelist.item(0); 
		
		NodeList nl1 = list1.getChildNodes(); 
		for(int i =0 ; i < nl1.getLength(); i++){
			Node n2 = nl1.item(i);
			if(n2.getNodeName().equals("article")){
				NamedNodeMap nnp = n2.getAttributes(); 
				String idx = nnp.getNamedItem(CommonName.ARTICLE_ID).getNodeValue();
				if(id.equals(idx)) {
					list1.removeChild(n2);  
					sukses = true; 
				}
			}
			
		}

		TransformerFactory tfactory = TransformerFactory.newInstance(); 
		Transformer transformer = tfactory.newTransformer(); 
		transformer.setOutputProperty(OutputKeys.INDENT , "yes" ); 
		
		DOMSource source = new DOMSource(doc); 
		StreamResult result = new StreamResult(new PrintStream(new FileOutputStream(articlepath)));
		transformer.transform(source , result ); 
		
		return sukses; 	
	}
}
