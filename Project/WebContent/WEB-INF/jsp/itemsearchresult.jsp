<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
 <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  <title>検索結果</title>
<head>
</head>
<body>
<%
	ArrayList<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>) request.getAttribute("itemList");
	String searchWord = (String) session.getAttribute("searchWord");
	int pageMax = (int) request.getAttribute("pageMax");
	int pageNum = (int) request.getAttribute("pageNum");
	int itemCount = (int) request.getAttribute("itemCount");
	String select = (String)request.getAttribute("select");
	int morePrice = request.getAttribute("morePrice")!=null?(int)request.getAttribute("morePrice"):-1;
	int lessPrice = request.getAttribute("lessPrice")!=null?(int)request.getAttribute("lessPrice"):-1;

%>

    <!-- body -->
    <br>
    <br>
    <br>
    <br>
  <div class="container">
    <h1 align="center">検索結果</h1>
    <%System.out.println(request.getRequestURI()); %>
    <h4 align="center">検索結果<%=itemCount%>件</h4>
  	 <div class="row">
  	 <%
					int i = 0;
					for (ItemDataBeans item : itemList) {
						i++;
	%>

      <div class="col-md-3">
        <br>
        <div class="card">
            <div class="card-image">
              <a href="Item?item_id=<%=item.getId()%>&page_num=<%= pageNum%>">
            <img  src="<%="img/" + item.getFileName()%>"  width="260" height="250" />
              </a>
             <div class="card-content">
              <span class="card-title"><%=item.getName()%></span>
              <%=item.getPrice()%>円
            </div>
           </div>

          </div>
        </div>
        <%
		 if (i % 4 == 0) {
		%>
		</div>
		<br><br><br>
		<div class="container">
		<div class="row">
		<%
		}
		%>
        <%
        }
		%>
	</div>
  </div>

<br>
<br>
<br>

      <div class="text-center">
			<ul class="pagination">
				<!-- １ページ戻るボタン  -->
				<%
				    String check = select.equals("and")?"&select=and&":"";
				    String mPrice = morePrice!=-1?"&morePrice="+morePrice+"&":"";
				    String ePrice = lessPrice!=-1?"&lessPrice="+lessPrice+"&":"";
				    boolean flagMin = true;
				    boolean flagMax = true;
					if (pageNum == 1) {
				%><li><a><font color="black">≪ 前へ</font></a></li>
				<% }else{%>
				<li><a href="<%="ItemSearchResult?search_word=" + searchWord+check+mPrice+ePrice + "&page_num=" + (pageNum - 1)%>">≪ 前へ</a></li>
				<%
					}
				%>
				<!-- ページインデックス -->
				<%
					for (int j = pageNum - 2; j <= pageNum + 2; j++) {
				%>
				<!-- マイナスページが生成されないように -->
				<%
					if (j <= 0) {
							j = 1;
						}
				%>
				<%
				if( pageNum > 3 && flagMin == true){
					flagMin = false;
				 %>
				<li><a href="<%="ItemSearchResult?search_word=" + searchWord+ check +mPrice+ePrice + "&page_num=" + 1%>"><%=1 %></a></li>
				<%if(!(pageNum == 4)){ %>
				<li><a>⋯</a></li>
				<%}
				}
				%>
				<li <%if (pageNum == j) {%> class="active" <%}%>><a href="<%="ItemSearchResult?search_word=" + searchWord+ check +mPrice +ePrice+ "&page_num=" + j%>"><%=j%></a></li>
				<!-- MAXを越さないように -->
				<%
					if (pageMax <= j) {
							break;
						}
				%>
				<%
					}
				%>
				<%
				if( pageNum < pageMax-2 && flagMax == true){
					flagMax = false;
				if(!(pageNum==pageMax-3)){%>
				<li><a>⋯</a></li>
				<%} %>
				<li><a href="<%="ItemSearchResult?search_word=" + searchWord + check +mPrice +ePrice + "&page_num=" + pageMax%>"><%=pageMax %></a></li>
				<%} %>
				<!-- 1ページ送るボタン -->
				<%
					if (pageNum == pageMax || pageMax == 0) {
				%><li><a><font color="black">次へ ≫</a></li>
				<%}else{ %>
				<li><a href="<%="ItemSearchResult?search_word=" + searchWord + check +mPrice+ePrice + "&page_num=" + (pageNum + 1)%>">次へ ≫</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>

  </body>
</html>