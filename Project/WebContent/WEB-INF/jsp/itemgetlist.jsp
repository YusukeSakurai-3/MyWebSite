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
   String userName = session.getAttribute("userName")!=null?(String) session.getAttribute("userName"):"";
   String listActionMessage = (String)request.getAttribute("listActionMessage");
   %>
  </head>

  <body>
    <!-- /header -->
    <br><br><br><br>



    <!-- body -->

    <div class="container">


      <h1 align="center"><%=userName %>さんの欲しいものリスト</h1>
      <%if(listActionMessage!=null){%>
      <div class="alert alert-success" ><%=listActionMessage%></div>
      <%} %>
      <div class="row">
      <div class="col-sm-11">
        <a  class=" btn btn-success col-sm-2" href="UserItemList">他の人のリストを見る</a>

         <form action="UserItemDelete" method="POST">
         <button  class="btn btn-danger col-sm-2 col-sm-offset-1" type="submit">リストから削除する</button>
       </div>
     </div>

      <!--<button class="btn btn-success" href="#staticModal" data-toggle="modal">他の人のリストを見る</button>
-->

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
                    <input type="checkbox" id="<%=i%>" name="delete_item_id_list" value="<%=item.getId()%>" /> <label for=<%=i%>>削除</label>
                  </p>
                  </div>
                 </div>
                </div>
              </div>
                 <%} %>

             </div>
             </form>
             <br><br><br>







  </body>
</html>