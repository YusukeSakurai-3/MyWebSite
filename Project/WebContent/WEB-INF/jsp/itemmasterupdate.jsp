<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<!DOCTYPE html>
<html lang="ja">
 <title>商品マスタ更新 </title>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />

<%
ItemDataBeans item = (ItemDataBeans)request.getAttribute("item");
int purchaseNum = (int)request.getAttribute("purchaseNum");
String itemUpdateError = (String)request.getAttribute("itemUpdateError");
%>
  <!--画像読み込み用-->
  <script src="js/fileupload.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <body>
    <br><br><br><br>
    <!-- body -->
    <div class="container">

      <h1 align="center">商品更新</h1>
      <div class="text-right">
        <a href="ItemMaster">商品マスタ一覧へ</a>
      </div>

 <%
 if(itemUpdateError!=null) {
 %>
  <div class="alert alert-danger" role="alert"><%=itemUpdateError %></div>
 <%} %>


      <br><br>


      <div class="container">

      	<div class="row">
            <div class="col-sm-12">
                  <form method="post" action="ItemMasterUpdate" class="form-horizontal" enctype="multipart/form-data">
                  <div class="col-sm-2">
                  <img src="<%="img/" + item.getFileName()%>"  width="250" height="250">
                  </div>
                  <div class="col-sm-10">
                  <input type="hidden" name="itemId" value="<%= item.getId()%>">
                    <div class="form-group">
                      <label class="control-label col-sm-2">商品名</label>
                      <div class="col-sm-6">
                        <input  type="text" class="form-control" name="itemName" value="<%= item.getName()%>"/>
                      </div>
                    </div>
                    <div class="form-group form-margin">
                      <label  class="control-label col-sm-2">価格</label>
                      <div class="col-sm-6">
                        <input type="text" name="itemPrice" value="<%=item.getPrice() %>" class="form-control" />
                      </div>
                    </div>
                    <div class="form-group form-margin">
                      <label  class="control-label col-sm-2">購入数</label>
                      <div class="form-control-static col-sm-6">
                       <%=purchaseNum %>
                       </div>
                        <!--  <input type="text" name="category-name" id="category-name" class="form-control" value="果物" />-->
                    </div>
                  </div>
            </div>


            <div class="form-group">
              <div class="col-sm-7">
              <br><label >商品詳細</label>
              <textarea rows="14" name="itemDetail" class="form-control"><%=item.getDetail()%></textarea>
            </div>

         <div class="col-md-3">
           <label >写真</label>
                   <div class="imagePreview"></div>
                   <div class="input-group">
                       <label class="input-group-btn">
                           <span class="btn btn-primary">
                               Choose File<input type="file" name="uploadFile" style="display:none" class="uploadFile">
                           </span>
                       </label>
                       <input type="text" class="form-control" readonly="">
                   </div><br><br>
           </div>
         </div>


      </div>
      <div class="text-center">
      <button class="btn btn-primary  col-sm-4 col-sm-offset-4" type="submit">商品更新</button>
     </div>
   </form>
   <br><br>
  </div>






  </body>
</html>