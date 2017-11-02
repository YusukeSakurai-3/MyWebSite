<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品レビュー</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
<%
 ItemDataBeans item = (ItemDataBeans)request.getAttribute("item");
%>


  <!--画像インプット用-->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.imagePreview {
  width: 100%;
  height: 320px;
  margin-top: 5px;
  background-position: center center;
  background-size: cover;
  -webkit-box-shadow: 0 0 1px 1px rgba(0, 0, 0, .3);
  display: inline-block;
}
</style>
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
  </script>
  </head>

  <body>

    <br><br><br><br>




    <!-- body -->

    <div class="container">

      <h1 align="center">商品レビュー</h1>


      <br><br>


      <div class="container">

      	<div class="row">
          <div class="col-md-3">
            <br>
            <div class="card">
                <div class="card-image">
                  <a href="Item?item_id=<%=item.getId()%>">
                 <img  src="<%="img/" + item.getFileName()%>"  width="260" height="250" />
                  </a>
                  <div class="card-content">
                  <span class="card-title"><%=item.getName() %></span>
                  <%=item.getPrice() %>円
                </div>
               </div>
              </div>
            </div>
          <div class="col-md-5">
            <form action="ItemReviewAdd" method="post" enctype="multipart/form-data">
            <input type="hidden" name="itemReviewId" value="<%=item.getId()%>">
            <div class="form-group">
              <label for="review">レビュー
                  <input  type="text" class="form-control" placeholder="reviewTitle" name="reviewTitle"/>
                  <div class="star-rating">
                      <div class="star-rating-front" style="width: <%=item.getId() %>%">★★★★★</div>
                      <div class="star-rating-back">★★★★★</div>
                    </div>

                  <!--</div>-->
              </label>
              <textarea rows="14" class="form-control" id="review" name="reviewText"></textarea>
            </div>


         </div>
         <div class="col-md-3">
           <br>写真を投稿する<br>
               <!--<form action="" method="post" enctype="">-->
                   <div class="imagePreview"></div>
                   <div class="input-group">
                       <label class="input-group-btn">
                           <span class="btn btn-primary">
                               Choose File<input type="file" name="reviewPicture" style="display:none" class="uploadFile">
                           </span>
                       </label>
                       <input type="text" class="form-control" readonly="">
                   </div><br><br><br>

               <button class="btn btn-primary" type="submit">レビュー送信</button>
            </form>
           </div>


      </div>






  </body>
</html>
