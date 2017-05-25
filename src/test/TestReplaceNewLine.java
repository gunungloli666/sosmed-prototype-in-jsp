package test;

public class TestReplaceNewLine {
	public static void main(String[] args){
		
		String m = "dsdsd\nadsdsd\n";
		String x = m.replaceAll("\n", "<br />"); 
		System.out.println(x); 
	}
}
