<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイルアップロード</title>
</head>
<body>
<form method="POST" enctype="multipart/form-data" action="UploadServlet">
<input type="file" name="file"/><br />
<input type="hidden" name="success" value="succeeded!">
<input type="submit" value="アップロード" />
</form>
<%
String name = (String)request.getAttribute("name");
System.out.println();
if(name!=null){
	Thread.sleep(10000);
%>
<img src="<%="img/" +request.getAttribute("name") %>" width="260" height="250"/>
<%} %>
<!--  <img src="./img/leopard-2895448_1280.jpg" width="260" height="250"/>
<img  src="./img/fd400947.jpg"  width="260" height="250" />
-->
</body>
</html>