<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザー一覧</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
  </head>
  <body>
      <br><br><br>

    <!-- body -->
    <div class="container">

      <h1 align="center">ユーザー一覧</h1>


      <div class="panel-body">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="panel-title">検索条件</div>
            </div>
            <div class="panel-body">
              <form method="post" action="#" class="form-horizontal">
                <div class="form-group">
                  <label for="code" class="control-label col-sm-2">ログインID</label>
                  <div class="col-sm-6">
                    <input type="text" name="login-id" id="login-id" class="form-control"/>
                  </div>
                </div>
                <div class="form-group">
                  <label for="name" class="control-label col-sm-2">ユーザ名</label>
                  <div class="col-sm-6">
                    <input type="text" name="user-name" id="user-name" class="form-control"/>
                  </div>
                </div>

                <div class="form-group">
                  <label for="continent" class="control-label col-sm-2">生年月日</label>
                  <div class="row">
                    <div class="col-sm-2">
                      <input type="date" name="date-start" id="date-start" class="form-control" size="30"/>
                    </div>
                    <div class="col-xs-1 text-center">
                      ~
                    </div>
                    <div class="col-sm-2">
                      <input type="date" name="date-end" id="date-end" class="form-control"/>
                    </div>
                </div>
                </div>
                <div class="text-right">
                  <button type="submit" value="検索" class="btn btn-primary form-submit">検索</button>
                </div>
              </form>


          </div>
        </div>

        <div class="table-responsive">
             <table class="table table-striped table-bordered" class="table table-bordered">
               <thead>
                 <tr>
                   <th>ログインID</th>
                   <th>ユーザ名</th>
                   <th>生年月日</th>
                   <th></th>
                 </tr>
               </thead>
               <tbody>
                 <tr>
                   <td>id0001</td>
                   <td>田中太郎</td>
                   <td>1989年04月20日</td>
                   <td>
                     <a class="btn btn-primary" href="userDetail.html">詳細</a>
                     <a class="btn btn-success" href="userUpdate.html">更新</a>
                     <a class="btn btn-danger" href ="userDelete.html">削除</a>
                   </td>
                 </tr>
                 <tr>
                   <td>id0002</td>
                   <td>佐藤二朗</td>
                   <td>2001年11月12日</td>
                   <td>
                     <a class="btn btn-primary" href="userDetail.html">詳細</a>
                     <a class="btn btn-success" href="userUpdate.html">更新</a>
                     <a class="btn btn-danger" href ="userDelete.html">削除</a>
                   </td>
                 </tr>
                 <tr>
                   <td>id0003</td>
                   <td>佐川真司</td>
                   <td>2000年01月01日</td>
                   <td>
                     <a class="btn btn-primary" href="userDetail.html">詳細</a>
                     <a class="btn btn-success" href="userUpdate.html">更新</a>
                     <a class="btn btn-danger" href ="userDelete.html">削除</a>
                   </td>
                 </tr>
               </tbody>
             </table>
           </div>
         </div>
      </div>
    </div>

  </body>
</html>