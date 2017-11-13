<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="ec.EcHelper" %>
<%@page import="dao.ItemDAO" %>
<!DOCTYPE html >

<% boolean isLogin = session.getAttribute("isLogin")!=null?(boolean) session.getAttribute("isLogin"):false;
 boolean isIndex = session.getAttribute("isIndex")!=null?(boolean) session.getAttribute("isIndex"):false;
 String userName = session.getAttribute("userName")!=null?(String) session.getAttribute("userName"):"no";
 int userId = session.getAttribute("userId")!=null?(int)session.getAttribute("userId"):-1;
 String searchWord = ("/MyWebSiteEC/WEB-INF/jsp/itemsearchresult.jsp".equals(request.getRequestURI()))? (String)session.getAttribute("searchWord"):"";
%>
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
              <li><a href="Cart">買い物かご</a></li>
            <li><a href="Ranking">ランキング</a></li>
           <%if(isLogin){%> <li> <a href="ItemGetList?listUserId=<%=userId%>">ほしい物リスト</a></li><%} %>
            <!--  <li> <a href="Index">最近見た商品</a></li> -->
            <li> <a href="Item?item_id=<%=ItemDAO.getRandomItemID() %>">ランダムページ</a></li>
          </ul>
        </li>

     <%if ("/MyWebSiteEC/WEB-INF/jsp/index.jsp".equals(request.getRequestURI())
    		 ||"/MyWebSiteEC/WEB-INF/jsp/itemsearchresult.jsp".equals(request.getRequestURI())){%>
      <li>

      <form  class="navbar-form navbar-right" action="ItemSearchResult">

        <input type="text" name="search_word" class="form-control" placeholder="Search" value="<%=searchWord%>" size="50">
        <button type="submit" class="btn btn-primary" >
          検索
        </button>
      </form>
     </li>

      <li>
      <button class="btn btn-success " href="#staticModal" data-toggle="modal" style="position: relative;top :8px">詳細</button>
      </li>

      <%} %>

      </ul>


         <ul class="nav navbar-nav navbar-right">
         <%if(!isLogin) {%>
          <li class="dropdown"><a href="UserCreate"  >新規登録</a></li>
          <li class="dropdown"><a href="Login" class="navbar-link login-link">ログイン</a></li>
          <%}else{ %>
          <li class="dropdown"><a href="UserDetail"><%=userName %>さん</a></li>
          <li class="dropdown"><a href="Logout" class="navbar-link logout-link">ログアウト</a></li>
           <%} %>
         </ul>
      </div><!--/.nav-collapse -->
      </div>
    </div>
  </div>


<%if ("/MyWebSiteEC/WEB-INF/jsp/index.jsp".equals(request.getRequestURI())
		||"/MyWebSiteEC/WEB-INF/jsp/itemsearchresult.jsp".equals(request.getRequestURI())){%>
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
                      <form  action="ItemSearchResult" class="form-horizontal">

                       <!--  <div class="form-group">
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
                        </div>-->
                        <div class="form-group">
                          <label  class="control-label col-sm-3">キーワード</label>
                          <div class="col-sm-6">
                            <input type="text" name="search_word"  class="form-control" value="<%=searchWord%>"/>
                            and<input type="radio" name="select" value="and">
                             or<input type="radio" name="select" value="or">
                          </div>
                        </div>

                        <div class="form-group">
                          <label for="continent" class="control-label col-sm-3">価格</label>
                          <div class="row">
                            <div class="col-sm-3">
                              <input type="text" name="morePrice"  class="form-control" size="30"/>
                            </div>
                            <div class="col-xs-1 text-center">
                              ~
                            </div>
                            <div class="col-sm-3">
                              <input type="text" name="lessPrice"  class="form-control"/>
                            </div>
                        </div>
                        </div>
                  </div>

      <div class="modal-footer">
        <button type="submit" class="btn btn-primary" >検索</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
       </form>
      </div>
    </div> <!-- /.modal-content -->
  </div> <!-- /.modal-dialog -->
</div> <!-- /.modal -->
<%} %>
