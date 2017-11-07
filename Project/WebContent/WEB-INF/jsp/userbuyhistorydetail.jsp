<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.UserDataBeans" %>
<%@ page import="beans.BuyDataBeans" %>
<%@ page import="beans.ItemDataBeans" %>
<%@page import=" java.util.ArrayList"%>
<%@page import=" java.util.HashMap"%>

<!DOCTYPE html>
<html lang="ja">
<title>購入詳細画面</title>
<jsp:include page="/baselayout/head.html" />
 <jsp:include page="/baselayout/header.jsp" />
  <head></head>
    <%
    BuyDataBeans bdb = (BuyDataBeans)request.getAttribute("buyDetail");
	String date = (String)request.getAttribute("formatDate");
	int virtualTotalPrice = (int)request.getAttribute("virtualTotalPrice");
	ArrayList<ItemDataBeans> iddb = (ArrayList<ItemDataBeans>)request.getAttribute("itemList");
	HashMap<Integer,Boolean> isReviewed = (HashMap<Integer,Boolean>)request.getAttribute("isReviewed");
  %>
  <body>

    <br><br><br>

    <!-- body -->
    <div class="container">

  <!--購入詳細-->
  <div class="container">
    <h1 align="center">購入詳細</h1>

          <div class="table-responsive">
               <table class="table table-striped " class="table table-bordered">
                 <thead>
                   <tr>
                     <th >購入日時</th>
                     <th >配送方法</th>
                     <th >合計金額</th>

                   </tr>
                 </thead>
                 <tbody>
                   <tr class="something">
                     <td class="col-md-8"><%=date%></td>
                       <td class="col-md-2"><%=bdb.getDeliveryMethodName() %></td>
                       <td class="col-md-2"><%=bdb.getTotalPrice() %>円</td>
                    </tr>





                 </tbody>
               </table>
             </div>



       </form>
     </div>




    <div class="container">
            <div class="table-responsive">
                 <table class="table table-striped " class="table table-bordered">
                   <thead>
                     <tr>
                       <th >商品名</th>
                       <th></th>
                       <th >単価</th>


                     </tr>
                   </thead>
                   <tbody>
                   <% for(ItemDataBeans item :iddb){%>
                     <tr class="something">
                       <td class="col-md-6"><%=item.getName() %></td>
                       <td>
                       <%if(!isReviewed.get(item.getId())){ %>
                       <form action="ItemReview" >
                       <input type="hidden" name="item_id" value="<%=item.getId() %>">
                       <button class="btn btn-primary col-sm-4" type="submit">レビューする</button>
                       <%}else{ %>
                       <button class="btn btn-default col-sm-4" >レビュー済</button>
                       <%} %>
                       </form>
                      </td>
                         <td class="col-md-2"><%=item.getPrice()%>円</td>
                         <%} %>
                      </tr>

                       <tr>
                         <td ></td>
                         <td ><%=bdb.getDeliveryMethodName() %></td>
                         <td ><%=bdb.getDeliveryMethodPrice() %>円</td>
                       </tr>
                       <tr>
                         <td ></td>
                         <td >合計</td>
                         <td ><%=bdb.getTotalPrice() %>円</td>
                       </tr>
                       <%
                       int realTotalPrice = bdb.getTotalPrice() ;
                       if(realTotalPrice == virtualTotalPrice) {
                       %>
                       <tr class="success">
                         <td ></td>
                         <td >獲得ポイント</td>
                         <td ><%=realTotalPrice/100 %>pt</td>
                       </tr>
                       <%}else{ %>
                       <tr class="info">
                         <td ></td>
                         <td >使用ポイント</td>
                         <td ><%=virtualTotalPrice-realTotalPrice  %>pt</td>
                       </tr>
                       <%} %>
                   </tbody>
                 </table>
               </div>
           </div>
        </div>
      </div>


  </body>
</html>