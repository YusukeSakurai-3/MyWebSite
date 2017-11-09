<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>システムエラー</title>
<jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
   <link href="css/original/common2.css" rel="stylesheet">
<%
	String errorMessage = (String) request.getAttribute("errorMessage");
%>
</head>
<body><br><br><br>

<div class="alert alert-danger" role="alert"><%=errorMessage %></div>
    <div class="container">


        	<div class="row">
    			<div class="col-md-6 col-md-offset-3">
    				<div class="panel panel-danger">
    					<div class="panel-heading ">

                <h3>システムエラー<h3>

    					</div>
    					<div class="panel-body">
    						<div class="row">
    							<div class="col-lg-12">
    							  <div class="text-right">
                              <br><a href="Index" class="btn btn-success col-sm-8 col-sm-offset-2">TOPへ</a>
                            </div>
                       </div>
                     </div>



</body>
</html>