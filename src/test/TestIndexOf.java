package test;

public class TestIndexOf {
	
	public static void main(String[] args){
		String msg = "sayang kamu adik ku"; 
		String temp = "ad"; 
		if(msg.toLowerCase().indexOf(temp.toLowerCase() ,0) !=-1 ){
			System.out.println("terima kasih"); 
		}
	}
}
