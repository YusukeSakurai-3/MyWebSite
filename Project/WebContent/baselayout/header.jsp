<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="ec.EcHelper" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% boolean isLogin = session.getAttribute("isLogin")!=null?(boolean) session.getAttribute("isLogin"):false; %>
<% boolean isIndex = session.getAttribute("isIndex")!=null?(boolean) session.getAttribute("isIndex"):false; %>
<% String userName = session.getAttribute("userName")!=null?(String) session.getAttribute("userName"):"no"; %>
    <!-- header -->

    <div id="navbar-main">
  <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <img src="img/logo.png" class="logo"/>
        </button>
      </div>
      <div class="navbar-collapse collapse" >
        <ul class="nav navbar-nav">
        <li><a href="Index">EC</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu <span class="caret"></span></a>
          <ul class="dropdown-menu">
              <li><a href="cart.html">買い物かご</a></li>
            <li><a href="ranking.html">ランキング</a></li>
            <li> <a href="itemgetList.html">ほしい物リスト</a></li>
            <li> <a href="history.html">最近見た商品</a></li>
            <li> <a href="item.html">ランダムページ</a></li>
          </ul>
        </li>

     <%if ("/MyWebSiteEC/WEB-INF/jsp/index.jsp".equals(request.getRequestURI())){%>
      <li>
      <form class="navbar-form navbar-right search" action="itemsearchresult.html" role="search" >
        <div class="form-group ">
        <input type="text" class="form-control" placeholder="Search" size="50">
        </div>
        <button type="submit" class="btn btn-primary" >
          検索
        </button>
      </form>
        </li>


      <button class="btn btn-success " href="#staticModal" data-toggle="modal" style="position: relative;top :8px">詳細</button>

      <%} %>

      </ul>


         <ul class="nav navbar-nav navbar-right">
         <%if(!isLogin) {%>
          <li class="dropdown"><a href="UserCreate"  >新規登録</a></li>
          <li class="dropdown"><a href="Login" class="navbar-link login-link">ログイン</a></li>
          <%}else{ %>
          <li class="dropdown"><a href="userDetail.html"><%=userName %>さん</a></li>
          <li class="dropdown"><a href="Logout" class="navbar-link logout-link">ログアウト</a></li>
           <%} %>
         </ul>
      </div><!--/.nav-collapse -->
      </div>
    </div>
  </div>



      <!-- モーダルダイアログ -->
      <div class="modal" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" aria-hidden="true" data-show="true" data-keyboard="false" data-backdrop="static">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header " style="background-color:#6495ed; border-radius: 5px 5px 5px 5px;"  >
              <button type="button" class="close" data-dismiss="modal">
                <span aria-hidden="true">&#215;</span><span class="sr-only">閉じる</span>
              </button>
              <h4 class="modal-title ">詳細検索</h4>
            </div><!-- /modal-header -->
            <div class="modal-body">
              <!--<p class="recipient">検索</p>-->


                        <div class="panel-title"></div>
                    </div>
                    <div class="panel-body">
                      <form method="post" action="itemsearchresult.html" class="form-horizontal">
                        <div class="form-group">
                          <label for="code" class="control-label col-sm-3">カテゴリ</label>
                          <div class="col-sm-6">
                              <select class="form-control"  style="position: relative;top :4px">
                              <option value="カテゴリ1">カテゴリ1</option>
                              <option value="カテゴリ2">カテゴリ2</option>
                              <option value="カテゴリ3">カテゴリ3</option>
                              <option value="カテゴリ4">カテゴリ4</option>
                              <option value="カテゴリ5">カテゴリ5</option>
                              </select>
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="name" class="control-label col-sm-3">キーワード</label>
                          <div class="col-sm-6">
                            <input type="text" name="word" id="user-name" class="form-control"/>
                            and<input type="radio" name="select" value="and">
                             or<input type="radio" name="select" value="or">
                          </div>
                        </div>

                        <div class="form-group">
                          <label for="continent" class="control-label col-sm-3">価格</label>
                          <div class="row">
                            <div class="col-sm-3">
                              <input type="text" name="price-start" id="date-start" class="form-control" size="30"/>
                            </div>
                            <div class="col-xs-1 text-center">
                              ~
                            </div>
                            <div class="col-sm-3">
                              <input type="text" name="price-end" id="date-end" class="form-control"/>
                            </div>
                        </div>
                        </div>


                  </div>


      <div class="modal-footer">
        <a type="button" class="btn btn-primary" href="itemsearchresult.html">検索</a>
        <button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
       </form>
      </div>
    </div> <!-- /.modal-content -->
  </div> <!-- /.modal-dialog -->
</div> <!-- /.modal -->