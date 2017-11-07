<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <title>商品マスター削除 </title>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  </head>
  <body>
  <%
  ItemDataBeans item = (ItemDataBeans)request.getAttribute("item");
  int purchaseNum = (int)request.getAttribute("purchaseNum");
  %>

    <br><br><br><br>
    <!-- body -->
    <div class="container">

      <div class="panel panel-default">
        <div class="panel-body">

          <p>商品ID:<%= item.getId() %>を本当に削除してもよろしいでしょうか。</p>


          <div class="row">
          <div class="col-sm-3 col-sm-offset-3">
            <a class="btn btn-default form-submit " href="ItemMaster">Cancel</a>
            </div>
            <div class="col-sm-3">
            <form action="ItemMasterDelete" method="POST" >
            <input type="hidden" name="deleteItemId" value="<%=item.getId() %>">
            <button class="btn btn-primary form-submit " type="submit">OK</button>
            </form>
            </div>
          </div>
        </div>
      </div>

      <div class="table-responsive">
             <table class="table table-striped table-bordered" class="table table-bordered">
               <thead>
                 <tr>
                   <th>商品ID</th>
                   <th>商品名</th>
                   <th>登録日</th>
                   <th>価格</th>
                   <th>購入数</th>
                 </tr>
               </thead>
               <tbody>
                 <tr>
                   <td><%=item.getId() %></td>
                   <%if(item.getName().length()<=20) {%>
                   <td><%=item.getName() %></td>
                   <%}else{ %>
                   <td><%=item.getName().substring(0,20) %>⋯</td>
                   <%} %>
                   <td><%=item.getFormatCreateDate() %></td>
                   <td><%=item.getPrice()%>円</td>
                   <td><%=purchaseNum%></td>
                 </tr>
               </tbody>
             </table>
           </div>


      <div class="col-xs-4">
        <a href="ItemMaster">戻る</a>
      </div>
    </div>

  </body>
</html>