package media.sosial.dao;

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

public class Write {
	
	private Document document; 
	
	private static Write instance; 
	private Write(){}
	
	public static  Write getNewInstance(){
		if(instance == null){
			instance = new Write(); 
		}
		return instance; 
	}
	
	public boolean  writeArticle( String content) throws Exception{
		boolean success = false; 
		
		URL url = new  URL(CommonName.URL); 
		URLConnection ucon = url.openConnection(); 
		Properties prop = new Properties(); 
		prop.load(ucon.getInputStream()); 
		
		String articlepath = prop.getProperty(CommonName.ARTICLE_PATH); 
		
		DocumentBuilderFactory dbfactori = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder = dbfactori.newDocumentBuilder(); 
		
		Document document = builder.parse(new FileInputStream(articlepath));
		this.document = document; 
		
		Element el = document.getDocumentElement(); 
		
		Element artikel_baru = document.createElement("article") ; 
		
		long idnum = getId(document); 
		idnum = idnum + 1 ;
		artikel_baru.setAttribute("articleid", Long.toString(idnum));  
		setId(document, Long.toString(idnum));   
		
		Element detail = document.createElement("detail"); 
		
		detail.setAttribute("text" , content); 
		
		artikel_baru.appendChild(detail); 
		el.appendChild(artikel_baru);  
		
		TransformerFactory tfactory = TransformerFactory.newInstance(); 
		Transformer transformer = tfactory.newTransformer(); 
		transformer.setOutputProperty(OutputKeys.INDENT , "yes"); 
		
		DOMSource source = new DOMSource(document); 
		StreamResult result = new StreamResult( new PrintStream
				(new FileOutputStream(articlepath))); 
		
		transformer.transform(source, result); 
		success = true; 
		return success; 

	}
	
	
	public void setId(Document doc, String  newId){
		NodeList nodelist = doc.getElementsByTagName(CommonName.LIST_ARTICLE_TAG) ; 
		Node root  = nodelist.item(0); 
		NodeList nl1 = root.getChildNodes(); 
		for(int i = 0; i< nl1.getLength(); i++){
			Node n1 = nl1.item(i); 
			if(n1.getNodeName().equals("dataNumber")){
				root.removeChild(n1); 
				Element elementBaru = doc.createElement("dataNumber" ); 
				elementBaru.setAttribute( "num" ,  newId );  
				root.appendChild(elementBaru); 
			}
		}
	}
	
	public long getId(Document doc){
		NodeList nl = doc.getElementsByTagName("dataNumber"); 
		Node n = nl.item(0); 
		if(n!= null){
			NamedNodeMap nnp = n.getAttributes(); 
			String v = nnp.getNamedItem("num").getNodeValue(); 
			long  id = Long.parseLong(v);  
			return id; 
		}
		return 0; 
	}
	
	public long getId( ){
		if(document != null){
			return getId(document) ; 
		}
		return 0; 
	}

}
