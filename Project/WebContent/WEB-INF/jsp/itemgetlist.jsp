<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
<title>ほしい物リスト</title>
  <head>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  <%
   ArrayList<ItemDataBeans> userItemList = (ArrayList<ItemDataBeans>) request.getAttribute("userItemList");
   String listUser = (String)request.getAttribute("listUser");
   int listUserId = (int)request.getAttribute("listUserId");
   String userName = (String)session.getAttribute("userName");
	int pageMax = (int) request.getAttribute("pageMax");
	int pageNum = (int) request.getAttribute("pageNum");
   String listMessage = (String)request.getAttribute("listMessage");
   %>
  </head>

  <body>
    <!-- /header -->
    <br><br><br><br>



    <!-- body -->

    <div class="container">


      <h1 align="center"><%=listUser %>さんの欲しいものリスト</h1>
      <%if(listMessage!=null){%>
      <div class="alert alert-success" ><%=listMessage%></div>
      <%} %>
      <%if(userItemList.size()==0) {%>
      <%=listUser %>さんの欲しいものリストは0件です<%} %>
      <div class="row">
      <div class="col-sm-11">
        <a  class=" btn btn-success col-sm-2" href="UserItemList">他の人のリストを見る</a>

        <%if(listUser.equals(userName)) {%>
         <form action="UserItemDelete" method="POST">
         <button  class="btn btn-danger col-sm-2 col-sm-offset-1" type="submit">リストから削除する</button>
       </div>
       <%} %>
     </div>

        	 <div class="row">
        	 <%
        	 int i = 0;
        	 for (ItemDataBeans item: userItemList){
        	  i++;
        	 %>
            <div class="col-md-3">
              <br>
              <div class="card">
                  <div class="card-image">
                    <a href="Item?item_id=<%=item.getId()%>">
                  <img   src="<%="img/" + item.getFileName()%>"  width="260" height="250" />
                    </a>
                   <div class="card-content">
                    <span class="card-title"><%= item.getName()%></span>
                    <%=item.getPrice() %>円
                    <p>
                    <%if(listUser.equals(userName)) {%>
                    <input type="checkbox" id="<%=i%>" name="delete_item_id_list" value="<%=item.getId()%>" /> <label for=<%=i%>>削除</label>
                    <%} %>
                  </p>
                  </div>
                 </div>
                </div>
              </div>
                 <%} %>

             </div>
             </form>
             <br><br><br>
             <div class="text-center">
			<ul class="pagination">

				<%
				    boolean flagMin = true;
				    boolean flagMax = true;
					if (pageNum == 1) {
				%>
				<!-- １ページ戻るボタン  -->
				<li><a><font color="black">≪ 前へ</font></a></li>
				<% }else{%>
				<li><a href="<%="ItemGetList?listUserId="+listUserId+"&page_num=" + (pageNum - 1)%>">≪ 前へ</a></li>
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
				<li><a href="<%="ItemGetList?listUserId="+listUserId+"&page_num=" + 1%>"><%=1 %></a></li>
				<%if(!(pageNum == 4)){ %>
				<li><a>⋯</a></li>
				<%}
				}
				%>
				<li <%if (pageNum == j) {%> class="active" <%}%>><a href="<%="ItemGetList?listUserId="+listUserId+"&page_num=" + j%>"><%=j%></a></li>
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
				<li><a href="<%="ItemGetList?listUserId="+listUserId+"&page_num=" + pageMax%>"><%=pageMax %></a></li>
				<%} %>
				<!-- 1ページ送るボタン -->
				<%
					if (pageNum == pageMax || pageMax == 0) {
				%><li><a><font color="black">次へ ≫</a></li>
				<%}else{ %>
				<li><a href="<%="ItemGetList?listUserId="+listUserId+"&page_num=" + (pageNum + 1)%>">次へ ≫</a></li>
				<%
					}
				%>
			</ul>
		</div>







  </body>
</html>