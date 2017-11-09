<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.UserDataBeans"%>
<!DOCTYPE html>
<html lang="ja">
<head></head>
<title>ユーザー情報削除</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
<%
int pageNum = (int)request.getAttribute("pageNum");
UserDataBeans user = (UserDataBeans)request.getAttribute("user");
%>


    <!-- body --><br><br><br><br>
    <div class="container">

      <div class="panel panel-default">
        <div class="panel-body">

          <p>ログインID:<%=user.getLoginId() %>を本当に削除してもよろしいでしょうか。</p>


          <div class="row">
          <div class="col-sm-4 col-sm-offset-2">
            <a class="btn btn-default form-submit" href="<%="UserList?page_num="+pageNum%>">Cancel</a>
            </div>
            <div class="col-sm-4 ">
            <form action="UserDeleteMaster" method="POST">
            <input type="hidden" name="user_id" value="<%=user.getId() %>">
            <button class="btn btn-primary form-submit" type="submit" >OK</button>
            </form>
            </div>
          </div>
        </div>
      </div>


      <div class="col-xs-4">
        <a href="<%="UserList?page_num="+pageNum%>">戻る</a>
      </div>
    </div>
  </body>
</html>