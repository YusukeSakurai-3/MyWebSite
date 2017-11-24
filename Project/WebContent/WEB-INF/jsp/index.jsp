<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <title>商品一覧</title>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
 <%
	ArrayList<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>) request.getAttribute("itemList");
%>
</head><br><br><br>
<body>

    <div class="container">
      <h1><font color="#ff9933">ECサイト</font></h1>
      <h1 align="center">商品一覧</h1>
    </div>

  <div class="container">
    <h4>オススメ商品<h4>
  	 <div class="row">
  	 <%
		for (ItemDataBeans item : itemList) {
		%>
				<div class="col-md-3">
					<div class="card">
						<div class="card-image">
							<a href="Item?item_id=<%=item.getId()%>"><img src="<%="img/" + item.getFileName()%>" width="260" height="250"/></a>
						</div>
						<div class="card-content">
							<span class="card-title"><%=item.getName()%></span>
							<%=item.getPrice()%>円
						</div>
					</div>
				</div>
				<%
					}
				%>
				</div>

<br>
<br>
<br>
          </div>





  </body>
</html>