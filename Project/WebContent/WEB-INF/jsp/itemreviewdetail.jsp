<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ReviewDataBeans"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
  <head></head>
  <title>レビュー詳細</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
  <body>
<%
ReviewDataBeans review = (ReviewDataBeans)request.getAttribute("review");
%>

    <br><br><br><br>



    <!-- body -->

    <div class="container">

      <h1 align="center">商品レビュー詳細</h1>

      <form align="right" action="ItemReviewUpdate" >
						<input type="hidden" name="update_review_id" value="<%=review.getId() %>">
						<input type="hidden" name="update_item_id" value="<%=review.getItemId() %>">
						<button class="btn btn-primary" type="submit" name="action">
							レビューを変更する
						</button>
					</form>
          <br>

                  <form align="right" action="#staticModal" >
						<input type="hidden" name="delete" value="">
							<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#staticModal" >レビューを削除する</button>
					</button>
				</form>
				</div>
				<!-- モーダルダイアログ -->
				<div class="modal" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" aria-hidden="true" data-show="true" data-keyboard="false" data-backdrop="static">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">削除</h4>
							</div><!-- /modal-header -->
							<div class="modal-body">
								<p class="recipient">本当に削除しますか？</p>
							</div>
							<div class="modal-footer">

						<form action="ItemReviewDelete" method="POST">
						<input type="hidden" name="delete_item_id" value="<%=review.getId() %>">
						<button class="btn btn-danger" type="submit" name="action" >
							レビューを削除する
						</button>
					</form>
							</div>
						</div> <!-- /.modal-content -->
					</div> <!-- /.modal-dialog -->
				</div>


      </div>
      <br><br>

    <div class="container">

   <div class="container">
        <h4>ユーザーさんのレビュー<h4>
     <div class="panel ">
       <div class="panel-body">
     <div class="col-md-6">
     <img  src="<%="img/" + review.getFileName()%>"  width="400" height="400" />
     </div>
       <div class="col-md-6">
         <h2><%=review.getTitle() %></h2>
         <div class="star-rating2">
             <div class="star-rating-front2" style="width: <%= review.getEvaluation()*20 %>%">★★★★★</div>
             <div class="star-rating-back2">★★★★★</div>
        </div>
        <br><br>
        <%=review.getReviewText() %>
        </div>
     </div>
  </div><br>
  </div>




  </body>
</html>