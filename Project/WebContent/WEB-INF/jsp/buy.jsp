<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="beans.ItemDataBeans"%>
<%@	page import="beans.DeliveryMethodDataBeans"%>
<%@	page import="java.util.ArrayList"%>
<%@	page import="dao.PointDAO"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  </head>
  <title>購入</title>
  <%
	ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
	ArrayList<DeliveryMethodDataBeans> dmdbList = (ArrayList<DeliveryMethodDataBeans>) request.getAttribute("dmdbList");
	int userId = (int) session.getAttribute("userId");
	int userPoint = PointDAO.getInstance().getPointById(userId);
	String buyActionMessage = request.getAttribute("buyActionMessage")!=null?(String)request.getAttribute("buyActionMessage"):"";
%>

  <body>

    <!-- body -->
    <br><br><br>
    <div class="container">


      <h1 align="center">カートアイテム</h1>
       <div class="alert alert-danger" role="alert">ポイントを使用する場合、今回の購入でポイントはつきません</div>

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
                   <td class="col-md-4"><%=cartInItem.getName()%></td>
                     <td class="col-md-5"><%=cartInItem.getPrice()%>円</td>
                     <td class="col-md-3"><%=cartInItem.getPrice()%>円</td>
                  </tr>

                 <%} %>


               </tbody>
             </table>
           </div>


      <form class="form-horizontal col-sm-10" action="BuyConfirm" method="POST">
      <div class="form-group">
      <label for="delivery" class="control-label col-xs-3">配送方法 :</label>
        <div class="col-xs-2">
        <select class="form-control" name="delivery_method_id">
		<%
		 for (DeliveryMethodDataBeans dmdb : dmdbList) {
		%>
		<option value="<%=dmdb.getId()%>"><%=dmdb.getName()%></option>
		<%
		 }
		%>
		</select>
          </div>
        </div>
        <div class="form-group">
        <div class="row">
        <label  class="control-label col-sm-3">ポイントを使用する(1pt 1円):</label>
         <div class="col-xs-2">
              <input class="form-control"  type="text" name="point" placeholder="pt">
            </div>
            <label  class="control-label col-sm-3">所持ポイント:<%=userPoint%>pt</label>
          </div>
           <label class="col-sm-3"><font color="red"><%= buyActionMessage%></font></label>

      </div>

  </div>
        <div class="col-sm-12">
          <button href="buyconfirm.html" class="btn  btn-primary col-sm-4 col-sm-offset-4" type="submit" name="action">購入確認</button>
        </div>
          </form>

    </div>


  </body>
</html>