<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="beans.ItemDataBeans"%>
  <%@page import="ec.EcHelper"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <title>商品マスタ登録 </title>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
<%
String itemCreateError = (String)request.getAttribute("itemCreateError");
ItemDataBeans idb = (ItemDataBeans)request.getAttribute("idb");
%>


      <!--画像読み込み用-->
  <script src="js/fileupload.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

  </head>
  <body>

    <br><br><br><br>
    <!-- body -->
    <div class="container">
      <h1 align="center">商品登録</h1>
      <div class="text-right">
        <a href="ItemMaster">商品マスタ一覧へ</a>
      </div>
 <%
 if(itemCreateError!=null) {
 %>
  <div class="alert alert-danger" role="alert"><%=itemCreateError %></div>
 <%} %>


      <br><br>


      <div class="container">

      	<div class="row">
            <div class="col-sm-12">
                  <form method="POST" action="ItemMasterCreate" class="form-horizontal" enctype="multipart/form-data">
                    <!--  <div class="form-group">
                     <label  class="control-label col-sm-2">商品ID</label>
                      <div class="col-sm-6">
                        <input type="text" name="id" id="id" class="form-control" value="" required/>
                      </div>
                    </div>-->
                    <div class="form-group">
                      <label  class="control-label col-sm-2">商品名</label>
                      <div class="col-sm-6">
                        <input  type="text"  name="itemName" class="form-control"  value="<%=idb.getName()%>"/>
                      </div>
                    </div>
                    <div class="form-group form-margin">
                      <label  class="control-label col-sm-2">価格</label>
                      <div class="col-sm-6">
                        <input type="text" name="price" value="<%= String.valueOf(idb.getPrice()).equals("-1")? "":String.valueOf(idb.getPrice())%>" class="form-control" />
                      </div>
                    </div>
                    <!--  <div class="form-group form-margin">
                      <label class="control-label col-sm-2">カテゴリ名</label>
                      <div class="col-sm-6">
                        <input type="text" name="category-name" id="category-name" class="form-control" value="" />
                      </div>
                    </div>
                    -->
                    </div>


            </div>

            <div class="form-group">
              <div class="col-sm-6">
              <label >商品詳細</label>
              <textarea rows="16" class="form-control" name="itemDetail" ><%= idb.getDetail() %></textarea>
            </div>

         <div class="col-md-6">
           <label >写真</label>
                   <div class="imagePreview"></div>
                   <div class="input-group">
                       <label class="input-group-btn">
                           <span class="btn btn-primary">
                               Choose File<input type="file"  name="uploadFile" style="display:none" class="uploadFile">
                           </span>
                       </label>
                       <input type="text" class="form-control" readonly="">
                   </div><br><br>
           </div>
         </div>


      </div>
      <div class="text-center">
      <button class="btn btn-primary  col-sm-4 col-sm-offset-4" type="submit">商品登録</button>
     </div>
  </form>
   <br><br>
  </div>






  </body>
</html>