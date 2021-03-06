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
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import media.sosial.media.util.CommonName;

public class Modify {
	
	public boolean  modifyArticle(String id , String article) throws Exception{
		URL url = new URL(CommonName.URL); 
		URLConnection ucon = url.openConnection(); 
		Properties prop = new Properties(); 
		
		prop.load(ucon.getInputStream()); 
		String articlepath = prop.getProperty(CommonName.ARTICLE_PATH ); 
		
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder dbbuilder = dbfac.newDocumentBuilder();
		Document doc = dbbuilder.parse(new FileInputStream(articlepath)); 
		
		NodeList nodelist = doc.getElementsByTagName(CommonName.ARTICLE_TAG_NAME) ; 
		
		for( int i = 0; i < nodelist.getLength(); i++ ){
			Node node = nodelist.item(i); 
			NamedNodeMap nnp = node.getAttributes(); 
			
			String art_id  = nnp.getNamedItem(CommonName.ARTICLE_ID).getNodeValue() ;
			if( id .equals(art_id)) {
				NodeList nl2 = node.getChildNodes(); 
				Node contentNode = nl2.item(1); 
				node.removeChild(contentNode); 
				
				// Buat Element Baru
				Element elementBaru = doc.createElement(CommonName.DETAIL_ARTICLE); 
				elementBaru.setAttribute("text" , article); 
				
				node.appendChild(elementBaru); 
			}
		}
		TransformerFactory tfactory = TransformerFactory.newInstance(); 
		Transformer transformer = tfactory.newTransformer(); 
		
		transformer.setOutputProperty(OutputKeys.INDENT , "yes"); 
		
		DOMSource source = new DOMSource(doc); 
		StreamResult result = new StreamResult(new PrintStream(new FileOutputStream(articlepath)));
		
		transformer.transform(source, result);  
		
		
		return true; 
	}

}
