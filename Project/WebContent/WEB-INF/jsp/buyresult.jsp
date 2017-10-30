<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="beans.ItemDataBeans"%>
<%@ page import="beans.DeliveryMethodDataBeans"%>
<%@ page import="beans.BuyDataBeans"%>
<%@	page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="ja">
<title>購入完了</title>
  <head>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  </head>
  <%
	ArrayList<ItemDataBeans> buyIDBList = (ArrayList<ItemDataBeans>) request.getAttribute("buyIDBList");
	BuyDataBeans resultBDB = (BuyDataBeans) request.getAttribute("resultBDB");
	int point = (int)session.getAttribute("point");
%>
  <body>
    <!-- /header -->
    <br><br><br>

    <!-- body -->
    <div class="container">






      <h1 align="center">購入が完了しました</h1>
      <br><br>

      <div class="row">
        <div class="col-sm-4">
          <form align="right" action="Index">
            <input type="hidden" name="" value="">
            <button class="btn btn-success" type="submit" name="action" >
            <div style="width:120px;">買い物を続ける</div>
          </button>
        </form>
        </div>

        <div class="col-sm-6">
          <form align="right" action="userDetail.html" method="POST">
            <input type="hidden" name="item_id" value="">
            <button class="btn btn-success" type="submit" name="action"　>
            <div style="width:120px;">ユーザー情報へ</div>
          </button>
        </form>
        </div>
      </div>
    </div>
      <br><br>

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
                     <td class="col-md-8"><%=resultBDB.getFormatDate()%></td>
                       <td class="col-md-2"><%=resultBDB.getDeliveryMethodName()%></td>
                       <td class="col-md-2"><%=resultBDB.getTotalPrice()%>円</td>
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
                 <%
                 for (ItemDataBeans buyIDB : buyIDBList) {
                	 %>
                   <tr class="something">
                     <td class="col-md-6"><%=buyIDB.getName() %></td>
                     <td>  <a href="itemreview.html" class="btn  btn-primary col-sm-5 " type="submit" name="action">レビューする</a>
                    </td>
                       <td class="col-md-2"><%=buyIDB.getPrice() %>円</td>

                    </tr>
                    <%}%>
                     <tr>
                       <td ></td>
                       <td ><%=resultBDB.getDeliveryMethodName()%></td>
                       <td ><%=resultBDB.getDeliveryMethodPrice() %>円</td>
                     </tr>
                     <tr>
                       <td ></td>
                       <td >合計</td>
                       <td ><%=resultBDB.getTotalPrice()%>円</td>
                     </tr>
                     <%if(point==0){ %>
                     <tr class="success">
                       <td ></td>
                       <td >獲得ポイント</td>
                       <td ><%=resultBDB.getTotalPrice()/100%>pt</td>
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
