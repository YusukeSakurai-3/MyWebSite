<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.UserDataBeans" %>
<%@ page import="beans.BuyDataBeans" %>
<%@page import=" java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ユーザー情報画面</title>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  <%
  UserDataBeans udb = (UserDataBeans)request.getAttribute("updateuser");
  ArrayList<BuyDataBeans> bdbs = (ArrayList<BuyDataBeans>)request.getAttribute("bdb");
  String updateMessage = (String)request.getAttribute("updateMessage");
  %>
  </head>
  <body>

      <br><br><br>
 <%
 if(updateMessage!=null) {
 %>
  <div class="alert alert-success"><%=updateMessage %></div>
 <%} %>

    <!-- body -->
    <div class="container">
      <h1 align="center">ユーザー情報</h1>

      <div class="panel panel-default">
        <div class="panel-body">

          <div class="panel-body">


                <label for="user-id" class="control-label col-sm-2">ログインID</label>
                <p class=" static-padding">
                  <%= udb.getLoginId()%>
                </p>


                <label for="user-name" class="control-label col-sm-2">ユーザ名</label>
                <p class=" static-padding">
                  <%= udb.getName()%>
                </p>


                <label for="continent" class="control-label col-sm-2">生年月日</label>
                <p class=" static-padding">
                  <%= udb.getFormatbirthDate()%>
                </p>


                <label  class="control-label col-sm-2">住所</label>
                <p class=" static-padding">
                  <%= udb.getAddress()%>
                </p>


                <label  class="control-label col-sm-2">保有ポイント</label>
                <p class=" static-padding">
                  <%= (int)request.getAttribute("point") %>pt
                </p>


              <label  class="control-label col-sm-2">ほしい物リスト</label>
              <p class=" static-padding">
                <%= udb.getIs_open() == true ? "公開":"非公開"  %>
              </p>
              <div class="row">
            <div class="col-sm-2 col-sm-offset-8">
              <a class="btn btn-primary" href="ItemReviewList">投稿したレビューを見る</a>
            </div>
          <div class="col-sm-2">
            <a class="btn btn-primary" href="UserUpdate">更新</a>
          </div>
        </div>
        </div>

      </div>
    </div>

<!--購入履歴-->
<div class="container">
  <h1 align="center">購入履歴</h1>


          <div class="table-responsive">
               <table class="table table-striped " class="table table-bordered">
                 <thead>
                   <tr>
                     <th></th>
                     <th >購入日時</th>
                     <th >配送方法</th>
                     <th >合計金額</th>
                   </tr>
                 </thead>
                 <tbody>
                 <%for(BuyDataBeans bdb: bdbs) {%>
                   <tr class="something">
                   <form action="UserBuyHistoryDetail" method="POST" style=" display: inline; ">
                       <th><button class="btn btn-info" type="submit">詳細</button>
                      <td class="col-md-5 col-md-offset-2"><%=bdb.getFormatDate() %></td>
                       <td class="col-md-2"><%=bdb.getDeliveryMethodName() %></td>
                       <td class="col-md-2"><%= bdb.getTotalPrice()%>円</td>
                       <input type="hidden"  name="id" value=<%= bdb.getId()%>>
					   <input type="hidden"  name="formatDate" value=<%= bdb.getFormatDate()%>>
					   <input type="hidden"  name="deliveryMethodName" value=<%= bdb.getDeliveryMethodName()%>>
					   <input type="hidden"  name="deliveryMethodPrice" value=<%= bdb.getDeliveryMethodPrice()%>>
					   <input type="hidden"  name="totalPrice" value=<%= bdb.getTotalPrice()%>>
					</form>
                    </tr>
                    <%} %>


                 </tbody>
               </table>
             </div>

      <div class="col-xs-1">
        <a class="btn btn-primary" href="Index">戻る</a>
      </div>
      <br><br>
      <%if(udb.getLoginId().equals("admin")) {%>
      <div class="col-xs-4">
        <a href="UserList">ユーザー一覧画面へ</a>
        <a href="ItemMaster">商品マスタ一覧画面へ</a>
      </div>
      <%} %>
      <br><br><br>
    </div>



  </body>
</html>