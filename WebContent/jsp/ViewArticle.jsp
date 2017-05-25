<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="media.sosial.dao.Article , java.util.List " %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
String path = request.getContextPath(); 
%>
<script type="text/javascript" src="<%=path%>/script/jquery-3.2.1.min.js" ></script>
<script type="text/javascript"> 
$(document).ready(
  function(){
	$('#updateButton').click(function(){
		var status = $('#updateArea').val() ; 
		var rgx = /\n/g;  
		var sts = status.replace(rgx , "<br />" ); 
		$.ajax({
			url : 'updateArticle', 
			data : {content : status}, 
			type: 'post' , 
			cache : false , 
			success : function( response){
				$("#statusArea").prepend("<div style=\"width: 100%; background-color: #F8FAF6 ; float: left;  position: relative; top: 2px; border: 2px solid #DDE682; margin :2px;  \" id=\""
					+response+"\"><div style=\"position: relative ; float :left ; width: 50%;  \" >"+sts+"</div>"+
					"<div align=\"right\" style=\"position :relative; float: right; width: 50%;\"><button class=\"buttonHapus\" value=\""+
					response+"\">HAPUS</button></div>" +
					"</div>" );
				 $("#updateArea").val("");
				 
				 $(".buttonHapus").click( function(){
						var idx = $(this).val(); 
						$.ajax({
							url : 'hapusArticle', 
							data : {idArticle : idx} ,
							type : 'post', 
							cache : false, 
							success : function( response){
								$("#" + idx ).remove(); 
							}
						}); 
				}); 
			}	
		}); 
	}); 
	
	$(".buttonHapus").click( function(){
		var idx = $(this).val(); 
		$.ajax({
			url : 'hapusArticle', 
			data : {idArticle : idx} ,
			type : 'post', 
			cache : false, 
			success : function( response){
				$("#" + idx ).remove(); 
			}
		}); 
	}); 
	
}); 
</script>

</head>
<body>

<span> 
<b><a href="viewArticle" >Home</a></b>
<b>
<a href="">Profil</a>
</b>
</span>

<%
List<Article> listArticle =(List<Article>)  request.getAttribute("listArticle" ) ; 
%>
<div style="width: 58% ; background-color: #E8D2E8; "> 
	<div style="width: 100% ; background-color: #FBA1A6; "  >
		<textarea  style="width: 99%;" rows="5" cols="60" id="updateArea" ></textarea>
	</div>
	<div style="width: 50% ;"> 
		<button id="updateButton" >UPDATE</button>
	</div>
	
	<div id="statusArea">
		<%
			for(int i= listArticle.size() - 1; i>=0 ; i-- ){
				Article art = listArticle.get(i); 
		%>
		<div style="width: 100%; background-color: #F8FAF6 ;float: left; position: relative; top: 2px; border: 2px solid #DDE682; margin: 2px;  " id="<%=art.getTitle()%>">
			<div style="position: relative ; float :left ; width: 50%;" ><%=art.getContent()%></div>
			<div align="right" style="position :relative; float: right; width: 50%; ">
				<button class="buttonHapus"  value="<%=art.getTitle()%>" >HAPUS</button>
			</div>
			
		</div>
		<%
			}
		%>
	</div>
	
	<div id="test" ></div>
</div>

</body>
</html>