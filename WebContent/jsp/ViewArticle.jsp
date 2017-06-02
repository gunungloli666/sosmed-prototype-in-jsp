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
<script type="text/javascript" src="<%=path%>/script/jquery-ui.min.js" > </script>

<link rel="stylesheet" href="<%=path%>/css/main.css" type="text/css" >

<script type="text/javascript"> 
$(document).ready(
  function(){
	$('#updateButton').click(function(){
		var status = $('#updateArea').val() ; 
		var rgx = /\n/g;  
		var sts = status.replace(rgx , "<br />" ); 
		if( !sts){
			return; 
		}
		$.ajax({
			url : 'updateArticle', 
			data : {content : status}, 
			type: 'post' , 
			cache : false , 
			success : function( response){
				 $("#statusArea").prepend("<div  class=\"container-status\" id=\""
					+response+"\"><div class=\"area-status\" >"+sts+"</div>"+
					"<div align=\"right\" class=\"area-button-status\" ><button class=\"buttonHapus\" value=\""+
					response+"\">HAPUS</button></div>" +
					"</div>" );
				 $("#updateArea").val("");
				 $(".buttonHapus").click( function(){
					var idx = $(this).val(); 
					$.ajax({
						url : 'hapusArticle', 
						data : {idArticle : idx  } ,
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
	
	$("#autoComplete").on("change keyup paste" , function (){
		var text = $(this).val(); 
		$("#divAutocomplete").empty(); 
		$.ajax({
			url : 'searchArticle' , 
			data : {keyword : text }, 
			type : 'post', 
			cache: false, 
			success : function(respon){ 
				var res = JSON.parse(respon); 
				$.each(res , function(index, isi){
					$("#divAutocomplete").prepend("<div style=\"margin-top: 2px; \" >"+
					"<a href=\"#\" >"+isi.content+"</a></div>"); 
				}); 
			}
		});
	}); 
	
	// 	function cekPesan( ){
	// 		$.ajax({
	// 			url : 'cekPesan', 
	// 			data : { user : 'fajar'},
	// 			type : 'post', 
	// 			cache: false, 
	// 			success : function(respon){
	// 				console.log(respon); 
	// 				$("#kotakPesan").append("<div>"+respon+"</div>"); 
	// 				cekPesan();  // recurse call after message received
	// 				return; 
	// 			}
	// 		});
	// 	}
	// 	cekPesan(); 

}); 
</script>

</head>
<body>


<%
@SuppressWarnings("unchecked")
List<Article> listArticle =(List<Article>)  request.getAttribute("listArticle" ) ; 
%>
<div>

	<div style="float: left ; width: 100%;">
		<span> 
		<b><a href="viewArticle" >Home</a></b>
		<b>
		<a href="">Profil</a>
		</b>
		</span>
	</div>

<div class="left-container" > 

	<div class="area-input-status"   >
		<textarea  style="width: 99%;" rows="5" cols="60" id="updateArea" ></textarea>
	</div>
	<div style="width: 50% ;"> 
		<button id="updateButton" >UPDATE</button>
		<button>FOTO</button>
	</div>
	
	<div id="statusArea">
		<%
			for(int i= listArticle.size() - 1; i>=0 ; i-- ){
				Article art = listArticle.get(i); 
		%>
		<div class="container-status"  id="<%=art.getTitle()%>" >
			<div class="area-status" ><%=art.getContent()%></div>
			<div align="right" class="area-button-status"  >
				<button class="buttonHapus"  value="<%=art.getTitle()%>" >HAPUS</button>
			</div>
			
		</div>
		<%
			}
		%>
	</div>
	
	<div id="test" ></div>
</div>

<div class="right-container"  >
	<input style="width:100" id="autoComplete" >
	<div id="divAutocomplete" >
	</div>
		
	<div id="kotakPesan"  style="position: absolute; top:  12em ">
	<div>Daftar Pesan</div>
	</div>
</div>


</div>

</body>
</html>