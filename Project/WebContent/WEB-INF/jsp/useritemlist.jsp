<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.UserDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <title>公開ユーザー一覧</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
  </head>
  <%
  ArrayList<UserDataBeans> udb = (ArrayList<UserDataBeans>) request.getAttribute("udb");
  int pageNum = (int) request.getAttribute("pageNum");
  int pageMax = (int)request.getAttribute("pageMax");
  %>
  <body>
      <br><br><br>

    <!-- body -->
    <div class="container">






      <h1 align="center">ほしい物リストを公開しているユーザー</h1>


      <div class="panel-body">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="panel-title">検索条件</div>
            </div>
            <div class="panel-body">
              <form  action="UserItemList" class="form-horizontal">
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

                   <th></th>
                 </tr>
               </thead>
               <tbody>
               <% for(UserDataBeans user : udb){ %>
                 <tr>
                   <td><%=user.getLoginId() %></td>
                   <td><%=user.getName() %></td>

                   <td>
                     <a class="btn btn-primary" href="ItemGetList?listUserId=<%=user.getId()%>&page_num=<%= pageNum%>">ほしい物リストを見る</a>

                   </td>
                 </tr>
                 <%} %>
               </tbody>
             </table>
           </div>
         </div>
      </div>
    </div>

  </body>
</html>
