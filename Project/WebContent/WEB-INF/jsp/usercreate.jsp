<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <div class="container">
        <h1 align="center">ユーザ新規登録</h1>
      <div class="panel panel-default">
        <div class="panel-body">
          <div class="panel-body">
            <form method="post" action="#" class="form-horizontal">
              <div class="form-group">
                <label for="user-id" class="control-label col-sm-2">ログインID</label>
                <div class="col-sm-6">
                  <input type="text" name="user-id" id="user-id" class="form-control" value="id0001" required/>
                </div>
              </div>
              <div class="form-group">
                <label for="password" class="control-label col-sm-2">パスワード</label>
                <div class="col-sm-6">
                  <input type="password" name="password" id="password" class="form-control" required/>
                </div>
              </div>
              <div class="form-group form-margin">
                <label for="password-confirm" class="control-label col-sm-2">パスワード(確認)</label>
                <div class="col-sm-6">
                  <input type="password" name="password-confirm" id="password-confirm" class="form-control" required/>
                </div>
              </div>
              <div class="form-group form-margin">
                <label for="user-name" class="control-label col-sm-2">ユーザ名</label>
                <div class="col-sm-6">
                  <input type="text" name="user-name" id="user-name" class="form-control" value="田中太郎" required/>
                </div>
              </div>
              <div class="form-group form-margin">
                <label for="continent" class="control-label col-sm-2">生年月日</label>
                <div class="row">
                  <div class="col-sm-5">
                    <input  type="date" name="birthDate" id="date-start" class="form-control" size="30" value="1989-04-26" />
                  </div>
              </div>
              </div>
              <div class="form-group form-margin">
                <label for="continent" class="control-label col-sm-2">住所</label>
                <div class="row">
                  <div class="col-sm-5">
                    <input  type="text" name="birthDate" id="date-start" class="form-control" size="30" value="" />
                  </div>
              </div>
              </div>

              <div>
                <button type="submit" value="" class="btn btn-primary center-block form-submit">登録</button>
              </div>

            </form>
          </div>
        </div>
      </div>




    </div>
  </body>
</html>
