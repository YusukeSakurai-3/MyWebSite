<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.ItemDataBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="beans.BuyDataBeans"%>
<%@ page import="ec.EcHelper"%>
<!DOCTYPE html>
<html lang="ja">
 <head>
 </head>
 <title>購入確認</title>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  <body>
 <%
  ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
  BuyDataBeans bdb = (BuyDataBeans) session.getAttribute("bdb");
  int point = (int)session.getAttribute("point");
  %>

    <!-- /header -->
    <br><br><br>

    <!-- body -->
    <div class="container">

      <h1 align="center">カートアイテム</h1>

        <div class="table-responsive">
             <table class="table table-striped " class="table table-bordered">
               <thead>
                 <tr>
                   <th >商品名</th>
                   <th >単価</th>
                   <th >小計</th>

                 </tr>
               </thead>
               <tbody>
               <%
			      for (ItemDataBeans cartInItem : cart) {
				%>
                 <tr class="something">
                   <td class="col-md-8"><%=cartInItem.getName()%></td>
                     <td class="col-md-2"><%=cartInItem.getPrice()%>円</td>
                     <td class="col-md-2"><%=cartInItem.getPrice()%>円</td>
                  </tr>
                  <%
                  }
                  %>
                  <%if(point!=0){ %>
                 <tr class="info">
                   <td >使用ポイント</td>
                   <td ></td>
                   <td >-<%=point %>円</td>
                 </tr>
                 <%} %>

                   <tr>
                     <td ><%=bdb.getDeliveryMethodName()%></td>
                     <td ></td>
                     <td ><%=bdb.getDeliveryMethodPrice()%>円</td>
                   </tr>
                   <tr>
                     <td ></td>
                     <td >合計</td>
                     <td ><%=bdb.getTotalPrice()%>円</td>

                   </tr>
                   <%if(point==0){ %>
                   <tr class="success">
                     <td >獲得ポイント</td>
                     <td ></td>
                     <td ><%=bdb.getTotalPrice()/100%>pt</td>
                   </tr>
                   <%} %>
               </tbody>
             </table>
           </div>




  </div>
        <div class="col-sm-12">
        <form action="BuyResult" method="post">
          <button  class="btn  btn-primary col-sm-4 col-sm-offset-4" type="submit" name="buyresult">購入</button>
          </form>
        </div>

    </div>


  </body>
</html>