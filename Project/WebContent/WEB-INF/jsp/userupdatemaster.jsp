<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.UserDataBeans"%>
<!DOCTYPE html>
<html lang="ja">
  <head></head>
  <title>ユーザー情報更新</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
<%
int pageNum = (int)request.getAttribute("pageNum");
UserDataBeans user = (UserDataBeans)request.getAttribute("user");
int point = (int)request.getAttribute("point");
%>
  <body>


      <br><br><br>

    <!-- body -->
    <div class="container">
      <div class="panel panel-default">
        <div class="panel-body">
          <div class="panel-body">
            <form method="post" action="UserUpdateMaster" class="form-horizontal">
            <input type="hidden" name="user_id" value="<%=user.getId()%>">
              <div class="form-group">
                <label for="user-id" class="control-label col-sm-2">ログインID</label>
                <div  class="form-control-static col-sm-6">
                  <%=user.getLoginId() %>
                </div>
              </div>
              <div class="form-group">
                <label for="password" class="control-label col-sm-2">パスワード</label>
                <div class="col-sm-6">
                  <input type="password" name="password" class="form-control" />
                  <input type="hidden" name="prePassword" value=">">
                </div>
              </div>
              <div class="form-group form-margin">
                <label for="password-confirm" class="control-label col-sm-2">パスワード(確認)</label>
                <div class="col-sm-6">
                  <input type="password" name="passwordConfirm"  class="form-control" />
                </div>
              </div>
              <div class="form-group form-margin">
                <label for="user-name" class="control-label col-sm-2">ユーザ名</label>
                <div class="col-sm-6">
                  <input type="text" name="userName"  class="form-control" value="<%=user.getName() %>" />
                </div>
              </div>
              <div class="form-group form-margin">
                <label for="continent" class="control-label col-sm-2">生年月日</label>
                <div class="row">
                  <div class="col-sm-5">
                    <input  type="date" name="birthDate"  class="form-control" size="30" value="<%=user.getBirthDate() %>" required/>
                  </div>
              </div>
              </div>
              <div class="form-group form-margin">
                <label for="continent" class="control-label col-sm-2">住所</label>
                <div class="row">
                  <div class="col-sm-5">
                    <input  type="text" name="address"  class="form-control" size="30" value="<%=user.getAddress()%>" />
                  </div>
              </div>
              </div>
               <div class="form-group form-margin">
                <label for="continent" class="control-label col-sm-2">保有ポイント</label>
                <div class="row">
                  <div class="col-sm-5">
                    <input  type="text" name="point"  class="form-control" size="30" value="<%=point%>" />
                  </div>
              </div>
              </div>
              <div class="form-group form-margin">
                <label for="continent" class="control-label col-sm-2">ほしい物リスト</label>
                <div class="row">
                  <div class="col-sm-2">
                    <select class="form-control" name="isOpen" style="position: relative;top :4px">
                    <option value="open">公開</option>
                    <option value="close">非公開</option>
                    </select>
                  </div>
              </div>
              </div>

              <div>
                <button type="submit" value="更新" class="btn btn-primary center-block form-submit">更新</button>
              </div>

            </form>
          </div>
        </div>
      </div>

      <div class="col-xs-4">
        <a href="<%="UserList?page_num="+pageNum%>">戻る</a>
      </div>


    </div>
  </body>
</html>