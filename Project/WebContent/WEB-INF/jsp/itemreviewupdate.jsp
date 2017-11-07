<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ReviewDataBeans"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html >
<html lang="ja">
  <title>レビュー更新</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
<%
ReviewDataBeans reviewItem = (ReviewDataBeans)request.getAttribute("reviewItem");
ItemDataBeans item = (ItemDataBeans)request.getAttribute("item");
%>
<link href="css/bootstrap-stars.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="js/jquery.barrating.min.js"></script>
  <head>
  <!--画像インプット用-->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script>
  $(document).on('change', ':file', function() {
      var input = $(this),
      numFiles = input.get(0).files ? input.get(0).files.length : 1,
      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
      input.parent().parent().next(':text').val(label);

      var files = !!this.files ? this.files : [];
      if (!files.length || !window.FileReader) return; // no file selected, or no FileReader support
      if (/^image/.test( files[0].type)){ // only image file
          var reader = new FileReader(); // instance of the FileReader
          reader.readAsDataURL(files[0]); // read the local file
          reader.onloadend = function(){ // set image data as background of div
              input.parent().parent().parent().prev('.imagePreview').css("background-image", "url("+this.result+")");
          }
      }
  });

  $(function() {
	  $('#star').barrating({
	    theme: 'bootstrap-stars'
	  });
	});
  </script>
  </head>
  <body>
    <!-- /header -->
    <br><br><br><br>
    <!-- body -->
    <div class="container">

      <h1 align="center">商品レビュー更新</h1>
      <br><br>
      <div class="container">

      	<div class="row">
          <div class="col-md-3">
            <br>
            <div class="card">
                <div class="card-image">
                 <img  src="<%="img/" + item.getFileName()%>"  width="260" height="250" />
                  <div class="card-content">
                  <span class="card-title"><%=item.getName()%></span>
                  <%=item.getPrice() %>円
                </div>
               </div>
              </div>
            </div>
          <div class="col-md-5">
            <form action="ItemReviewUpdate" method="post" enctype="multipart/form-data">
             <input type="hidden" name="itemReviewId" value="<%=reviewItem.getId()%>">
            <div class="form-group">
              <label for="review">レビュー
                  <input  type="text" class="form-control" name="reviewTitle" value="<%=reviewItem.getTitle()%>"/>
              </label>
                    <select name="evaluation" id="star">
					  <option value="1">1</option>
					  <option value="2">2</option>
					  <option value="3">3</option>
					  <option value="4">4</option>
					  <option value="5">5</option>
					</select>
              <textarea rows="14" class="form-control" name="reviewText" ><%=reviewItem.getReviewText()%>
              </textarea>
            </div>


         </div>
         <div class="col-md-3">
           <br>写真を変更する<br>

                   <div class="imagePreview"></div>
                   <div class="input-group">
                       <label class="input-group-btn">
                           <span class="btn btn-primary">
                               Choose File<input type="file" name="reviewPicture" style="display:none" class="uploadFile" >
                           </span>
                       </label>
                       <input type="text" class="form-control" readonly="" >
                   </div><br><br><br>

               <button class="btn btn-primary" type="submit">レビュー更新</button>
            </form>
           </div>


      </div>






  </body>
</html>