<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="ja">
  <head>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  </head>
  <br><br><br>
  <body>
  <%
  ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
  String cartActionMessage = (String) request.getAttribute("cartActionMessage");
  %>


  <!-- body -->
    <div class="container">


      <h1 align="center">買い物かご</h1>
			<br><br>
			<div class="container">

			<%=cartActionMessage%>
			<br>

			<div class="row">
			  <div class="col-sm-4">
					<!--  <form align="right" action="#staticModal" method="POST">-->
						<input type="hidden" name="delete" value="">
						<!--<button class="btn btn-info" type="submit" name="action" >-->
						   <form action="ItemDelete" method="POST">
						   <button  class="btn btn-info col-sm-8 col-sm-offset-5" type="submit" >削除</button>
							<!-- <button type="button" class="btn btn-info col-sm-8 col-sm-offset-5" data-toggle="modal" data-target="#staticModal" >削除</button>
							-->
					</button>
				</div>
				<!-- モーダルダイアログ -->
				<div class="modal" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" aria-hidden="true" data-show="true" data-keyboard="false" data-backdrop="static">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">

								<h4 class="modal-title">削除</h4>
							</div><!-- /modal-header -->
							<div class="modal-body">
								<p class="recipient">削除しました</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
							</div>
						</div> <!-- /.modal-content -->
					</div> <!-- /.modal-dialog -->
				</div>




			  <div class="col-sm-4 col-sm-offset-4" >
						<a href="Buy" align="right" class="btn btn-info" >
						<div style="width:200px;">レジに進む</div>
					</a>
				</div>
			</div>
		</div>
      <br><br>




			     <div class="container">
			       <h4>カートの商品<h4>
			     	<div class="row">
	   <%
	    int i = 0;
		for (ItemDataBeans item : cart) {
		i++;

		%>

			<div class="col-md-3">
				<div class="card">
					<div class="card-image">
							<a href="Item?item_id=<%=item.getId()%>"><img src="<%="img/" + item.getFileName()%>" width="260" height="250"/></a>
					</div>
					<div class="card-content">
						<span class="card-title"><%=item.getName()%></span>
						<%=item.getPrice()%>円
					<br>
					    <input type="checkbox" id="<%=i%>" name="delete_item_id_list" value="<%=item.getId()%>" /> <label for=<%=i%>>削除</label>
						<!--  <input type="checkbox"  name="delete_item_id_list" value="" /> 削除-->
					</div>
				</div>
			</div>
		<%
		}
		%>
				</div>
				</form>



  </body>
</html>