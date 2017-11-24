<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.UserDataBeans"%>
<%@page import="ec.EcHelper"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <title>ユーザー新規登録</title>
   <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  </head>
  <body>

    <!-- header -->
  <br><br><br>
    <!-- body -->
 <%
  String check1 = (String)request.getAttribute("check1");
 System.out.println(check1);
  if(check1!=null&&check1.equals("NG")){

 %>
  <div class="alert alert-danger" role="alert">入力された内容は正しくありません。</div>
<% }%>
    <div class="container">
        <h1 align="center">ユーザ新規登録</h1>
      <div class="panel panel-default">
        <div class="panel-body">
          <div class="panel-body">
            <form method="post" action="#" class="form-horizontal">
              <div class="form-group">
                <label for="user-id" class="control-label col-sm-2">ログインID</label>
                <div class="col-sm-6">
                  <input type="text" name="loginId"  class="form-control" value="<%= EcHelper.nullCheck((String)request.getParameter("loginId"))%>" />
                </div>
              </div>
              <div class="form-group">
                <label for="password" class="control-label col-sm-2">パスワード</label>
                <div class="col-sm-6">
                  <input type="password" name="pass" id="password" class="form-control" />
                </div>
              </div>
              <div class="form-group form-margin">
                <label for="password-confirm" class="control-label col-sm-2">パスワード(確認)</label>
                <div class="col-sm-6">
                  <input type="password" name="passConfirm" id="password-confirm" class="form-control"/>
                </div>
              </div>
              <div class="form-group form-margin">
                <label for="user-name" class="control-label col-sm-2">ユーザ名</label>
                <div class="col-sm-6">
                  <input type="text" name="userName" id="user-name" class="form-control" value="<%= EcHelper.nullCheck((String)request.getParameter("userName"))%>"/>
                </div>
              </div>
              <div class="form-group form-margin">
                <label for="continent" class="control-label col-sm-2">生年月日</label>
                <div class="row">
                  <div class="col-sm-5">
                    <input  type="date" name="birthDate" id="date-start" class="form-control" size="30" value="<%= EcHelper.nullCheck((String)request.getParameter("birthDate"))%>" />
                  </div>
              </div>
              </div>
              <div class="form-group form-margin">
                <label for="continent" class="control-label col-sm-2">住所</label>
                <div class="row">
                  <div class="col-sm-5">
                    <input  type="text" name="address"  class="form-control" size="30" value="<%= EcHelper.nullCheck((String)request.getParameter("userName"))%>" />
                  </div>
              </div>
              </div>
              <div class="row">
              <div class="col-sm-2 col-sm-offset-8">
                <button type="submit" value="" class="btn btn-primary center-block form-submit">登録</button>
              </div>
               <div class="col-sm-1 col-sm-offset-1">
                <a  href="Index" class="btn btn-danger  ">戻る</a>
              </div>
              </div>

            </form>
          </div>
        </div>
      </div>




    </div>
  </body>
</html>
