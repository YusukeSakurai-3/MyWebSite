<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<title>ログインページ</title>
  <head>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  <link href="css/original/common2.css" rel="stylesheet">
  </head>
 <%
	String loginErrorMessage = (String) request.getAttribute("loginErrorMessage");
	String inputLoginId = (String) request.getAttribute("inputLoginId");
	String logoutcheck = (String) request.getAttribute("logoutCheck");
%>
 <br><br><br>
  <body>
<%
if (loginErrorMessage != null) {
%>
  <div class="alert alert-danger" role="alert"><%=loginErrorMessage %></div>
 <%}%>
 <%
 if(logoutcheck!=null && logoutcheck.equals("logout")) {
 %>
  <div class="alert alert-success" role="alert">ログアウトしました</div>
 <%} %>

    <div class="container">


        	<div class="row">
    			<div class="col-md-6 col-md-offset-3">
    				<div class="panel panel-login">
    					<div class="panel-heading">
                <form class="form-signin" action="LoginResult" method="post">
                <h1>Login<h1>
    					</div>
    					<div class="panel-body">
    						<div class="row">
    							<div class="col-lg-12">
    									<div class="form-group">
    										<input type="text" name="login_id" id="login_id" value="<%= inputLoginId %>" tabindex="1" class="form-control" placeholder="Login_Id" >
    									</div>
    									<div class="form-group">
    										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" >
    									</div>
                       <button  class="btn btn-lg btn-primary btn-block btn-signin"  type="submit">Login</button>
                       </form>
                       </div>
                     </div>
                          <div class="text-right">
                            <br><a href="UserCreate" class="btn btn-success">register</a>
                          </div><!-- /form -->
  </body>
</html>