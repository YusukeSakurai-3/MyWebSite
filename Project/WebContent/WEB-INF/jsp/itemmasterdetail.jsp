<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <title>商品マスター詳細 </title>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
<%
ItemDataBeans item = (ItemDataBeans) request.getAttribute("item");
int purchaseNum = (int)request.getAttribute("purchaseNum");

%>
  </head>
  <body>

    <br><br><br><br>
    <!-- body -->

    <div class="container">

      <h1 align="center">商品詳細</h1>
      <div class="text-right">
        <a href="ItemMaster">商品マスタ一覧へ</a>
      </div>
      <br><br>

    <div class="container">
      <div class="panel ">
        <div class="panel-body">
      <div class="col-md-6">
      <img src="<%="img/" + item.getFileName()%>"  width="400" height="400" />
      </div>
        <div class="col-md-6">
          <h2>商品名:<%=item.getName()%></h2>
          <br><br>
          <h4>価格:<%=item.getPrice() %>円</h4>
          <br><br>商品詳細:<%=item.getDetail() %>
         </div>
      </div>

      <div class="table-responsive">
           <table class="table table-striped table-bordered" class="table table-bordered">
             <thead>
               <tr>
                 <th>商品ID</th>
                 <th>登録日</th>
                 <th>更新日</th>

                 <th>購入数</th>
                 <th></th>
               </tr>
             </thead>
             <tbody>
               <tr>
                 <td><%=item.getId() %></td>

                   <td><%=item.getFormatCreateDate() %></td>
                   <td><%=item.getFormatUpdateDate() %></td>
                   <td><%=purchaseNum %></td>
                 <td> <a class="btn btn-success" href="ItemMasterUpdate?item_id=<%=item.getId()%>">更新</a>
                      <a class="btn btn-danger" href="ItemMasterDelete?item_id=<%=item.getId()%>">削除</a>
                  </td>
               </tr>


             </tbody>
           </table>
         </div>


   </div><br>







  </body>
</html>
