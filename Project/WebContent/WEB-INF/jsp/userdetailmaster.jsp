<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@page import="beans.UserDataBeans"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <title>ユーザー情報</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
  </head>
  <%
  int userPoint = (int)request.getAttribute("userPoint");
	UserDataBeans user = (UserDataBeans)request.getAttribute("user");
	int pageNum =(int)request.getAttribute("pageNum");
  %>
  <body>

      <br><br><br>

    <!-- body -->
    <div class="container">
      <h1 align="center">ユーザー情報</h1>

      <div class="panel panel-default">
        <div class="panel-body">

          <div class="panel-body">


                <label for="user-id" class="control-label col-sm-2">ログインID</label>
                <p class=" static-padding">
                  <%=user.getLoginId()%>
                </p>


                <label for="user-name" class="control-label col-sm-2">ユーザ名</label>
                <p class=" static-padding">
                  <%=user.getName()%>
                </p>


                <label for="continent" class="control-label col-sm-2">生年月日</label>
                <p class=" static-padding">
                  <%=user.getFormatbirthDate()%>
                </p>


                <label  class="control-label col-sm-2">住所</label>
                <p class=" static-padding">
                  <%=user.getAddress()%>
                </p>


                <label  class="control-label col-sm-2">保有ポイント</label>
                <p class=" static-padding">
                 <%=userPoint %>pt
                </p>


              <label  class="control-label col-sm-2">ほしい物リスト</label>
              <p class=" static-padding">
                非公開
              </p>
              <div class="row">
         <!--   <div class="col-sm-2">
            <a class="btn btn-primary" href="userUpdate.html">更新</a>
          </div>-->
        </div>
        </div>

      </div>
    </div>


      <br><br>
      <div class="col-xs-4">
        <a href="<%="UserList?page_num="+pageNum%>">ユーザー一覧画面へ</a>
      </div>
      <br><br><br>
    </div>



  </body>
</html>