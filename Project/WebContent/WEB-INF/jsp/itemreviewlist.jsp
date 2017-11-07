<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ReviewDataBeans"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
<head></head>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
<title>商品レビュー一覧</title>
<%
ArrayList<ReviewDataBeans> rdb = (ArrayList<ReviewDataBeans>)request.getAttribute("rdb");
String reviewActionMessage = (String)request.getAttribute("reviewActionMessage");
%>

    <!-- body -->
    <br>
    <br>
    <br>
    <br>


    <div class="container">
      <h1 align="center">ユーザーさんのレビュー一覧</h1>
    </div>

 <%
 if(reviewActionMessage!=null) {
 %>
  <div class="alert alert-success" role="alert"><%=reviewActionMessage %></div>
 <%} %>

  <div class="container">
    <h4>レビューした商品一覧<h4>
  	 <div class="row">
  	 <%
  	 int i = 0;
  	 for(ReviewDataBeans review:rdb){
  	 i++;
  	 %>

      <div class="col-md-3">
        <br>
        <div class="card">
            <div class="card-image">
              <a href="ItemReviewDetail?review_id=<%=review.getId()%>">
            <img   src="<%="img/" + review.getFileName()%>"   width="260" height="250" />
              </a>
             <div class="card-content">
              <br>
              <span class="card-title"><%=review.getTitle() %>
              <br>
              <%=review.getReviewText() %></span>
              <div class="star-rating2">
                  <div class="star-rating-front2" style="width: <%=review.getEvaluation()*20 %>%">★★★★★</div>
                  <div class="star-rating-back2">★★★★★</div>
              </div>


            </div>
           </div>
          </div>
        </div>
         <%
		 if (i % 4 == 0) {
		%>
		</div>
		<br><br><br>
		<div class="container">
		<div class="row">
		<%
		}
		%>
        <%} %>
        </div>


          <br><br><br>





  </body>
</html>
