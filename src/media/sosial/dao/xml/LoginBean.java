package media.sosial.dao.xml;


import java.io.FileInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import media.sosial.media.util.CommonName;

public class LoginBean {
	
	private Role role = Role.User; 
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public  boolean isValidUser(String userid , String userpass
	) throws Exception{
		
		URL url = new URL(CommonName.URL); 
		URLConnection ucon = url.openConnection(); 
		
		Properties  properties = new Properties(); 
		properties.load(ucon.getInputStream()); 
		
		String userpath = properties.getProperty("userlistpath"); 
		DocumentBuilderFactory docbfact = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder docb = docbfact.newDocumentBuilder(); 
		Document doc = docb.parse(new FileInputStream(userpath));  
		
		NodeList nl1 = doc.getElementsByTagName("user"); 
		
		for(int i  = 0; i < nl1.getLength() ; i++){
			Node node = nl1.item(i); 
			NamedNodeMap nodemap = node.getAttributes(); 
			
			Node nodename = nodemap.getNamedItem("name"); 
			Node nodepass = nodemap.getNamedItem("password");
//			
			if(nodename.getNodeValue().equals(userid) &&
					nodepass.getNodeValue().equals(userpass)){ 
				Node noderole = nodemap.getNamedItem("role"); 
				if(noderole.getNodeValue().equals("administrator")){
					setRole(Role.Administrator); 
				}else{
					setRole(Role.User); 
				}
				return true;
			}	
		}
		return false; 		
	}
}
