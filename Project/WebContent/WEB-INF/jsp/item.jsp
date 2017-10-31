<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import="beans.ReviewDataBeans"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="ja">
  <head>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
   </head>
<%
	ItemDataBeans item = (ItemDataBeans) request.getAttribute("item");
    ArrayList<ReviewDataBeans> reviewList = (ArrayList<ReviewDataBeans>)request.getAttribute("reviewList");
	//String searchWord = (String) session.getAttribute("searchWord");
	int pageNum = (int) request.getAttribute("pageNum");
%>
    <br><br><br><br>



    <!-- body -->

    <div class="container">

      <h1 align="center">商品詳細</h1>

      <div class="row">

       <form class="col-sm-2 col-sm-offset-8" action="ItemAdd" method="POST" >
			<input type="hidden" name="item_id" value="<%=item.getId()%>" >
			  <button class=" btn btn-primary" type="submit" name="action" >
				買い物かごに追加
				  </button>
	 </form>


     <form class="col-sm-2" action="ItemListAdd" method="POST" >
		<input type="hidden" name="itemget_id" value="<%=item.getId()%>" >
			<button class="btn btn-success" type="submit" name="action" >
				ほしい物リストに追加
			</button>
		</form>

      </div>

      <br><br>

    <div class="container">
      <div class="panel ">
        <div class="panel-body">
      <div class="col-md-6">
      <img src="<%="img/" + item.getFileName()%>"  width="400" height="400">
	      </div>
	        <div class="col-md-6">
	          <h2><%=item.getName()%></h2>
	          <h4><%=item.getPrice()%>円</h4>
	          <%=item.getPrice()/100%>pt (1%)<br><br>
	          <%=item.getDetail() %>
	         </div>
	      </div>
	   </div><br>

   <div class="container">
     <h4>同じカテゴリの商品<h4>
    <div class="row">

       <div class="col-md-3">
         <br>
         <div class="card">
             <div class="card-image">
               <a href="item.html">
              <img  src="./img/matsutake.jpg"  width="260" height="250" 　/>
               </a>
               <div class="card-content">
               <br>
               <span class="card-title">美味しいもも</span>
               <br><br>
               10000000円
             </div>
            </div>
           </div>
         </div>


  
       <div class="col-md-3">
         <br>
         <div class="card">
             <div class="card-image">
               <a href="item.html">
             <img src="./img/fd401266.jpg"  width="260" height="250" 　/>
              </a>
              <div class="card-content">
               <br>
               <span class="card-title">ぶどう</span>
               <br><br>
               10000000円
             </div>
            </div>
           </div>
         </div>


           <div class="col-md-3">
             <br>
             <div class="card">
                 <div class="card-image">
                   <a href="item.html">
                 <img   src="./img/fd401097.jpg"  width="260" height="250" 　/>
                  </a>
                  <div class="card-content">
                   <br>
                   <span class="card-title">美味しいもも</span>
                   <br><br>
                   10000000円
                 </div>
                </div>
               </div>
             </div>

             <div class="col-md-3">
               <br>
               <div class="card">
                   <div class="card-image">
                     <a href="item.html">
                   <img   src="./img/fd400947.jpg"  width="260" height="250" 　/>
                     </a>
                     <div class="card-content">
                     <br>
                     <span class="card-title">美味しいもも</span>
                     <br><br>
                     10000000円
                   </div>
                  </div>
                 </div>
               </div>
             </div>
           </div>

<%for(ReviewDataBeans review:reviewList) {%>
   <div class="container">
        <h4>ユーザーさんのレビュー<h4>


     <div class="panel ">
       <div class="panel-body">
     <div class="col-md-6">
     <img  src="./img/fd400947.jpg"  width="400" height="400" />
     </div>
       <div class="col-md-6">
         <h2><%=review.getTitle() %></h2>
         <div class="star-rating">
             <div class="star-rating-front" style="width: 80%">★★★★★</div>
             <div class="star-rating-back">★★★★★</div>
        </div>
        <br>
        <%= review.getReviewText()%>

        </div>
     </div>
  </div>
  <%} %><br>
<br>





  </body>
</html>