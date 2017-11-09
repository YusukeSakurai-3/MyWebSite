<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.UserDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
<title>ユーザー一覧</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
  </head>
  <body>
  <%
  ArrayList<UserDataBeans> userList = (ArrayList<UserDataBeans>) request.getAttribute("userList");
  String searchName = (String)session.getAttribute("searchName");
  String loginId = request.getAttribute("loginId")!=null?(String)request.getAttribute("loginId"):"__noId";
  String startDate = request.getAttribute("startDate")!=null?(String)request.getAttribute("startDate"):"nodate";
  String endDate = request.getAttribute("endDate")!=null?(String)request.getAttribute("endDate"):"nodate";
  int pageNum = (int) request.getAttribute("pageNum");
  int pageMax = (int)request.getAttribute("pageMax");
  %>
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
              <form  action="UserList" class="form-horizontal">
                <div class="form-group">
                  <label for="code" class="control-label col-sm-2">ログインID</label>
                  <div class="col-sm-6">
                    <input type="text" name="loginId"  class="form-control"/>
                  </div>
                </div>
                <div class="form-group">
                  <label for="name" class="control-label col-sm-2">ユーザ名</label>
                  <div class="col-sm-6">
                    <input type="text" name="searchName"  class="form-control"/>
                  </div>
                </div>

                <div class="form-group">
                  <label for="continent" class="control-label col-sm-2">生年月日</label>
                  <div class="row">
                    <div class="col-sm-2">
                      <input type="date" name="startDate"  class="form-control" size="30"/>
                    </div>
                    <div class="col-xs-1 text-center">
                      ~
                    </div>
                    <div class="col-sm-2">
                      <input type="date" name="endDate"  class="form-control"/>
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
                   <th>住所<th>
                 </tr>
               </thead>
               <tbody>
               <%for(UserDataBeans user: userList) {%>
                 <tr>
                   <td><%=user.getLoginId() %></td>
                   <td><%=user.getName() %></td>
                   <td><%=user.getFormatbirthDate() %></td>
                   <td><%=user.getAddress() %></td>
                   <td>
                     <a class="btn btn-primary" href="userDetailMaster?loginId=<%=user.getId()%>&page_num=<%= pageNum%>">詳細</a>
                     <a class="btn btn-success" href="userUpdateMaster?loginId=<%=user.getId()%>&page_num=<%= pageNum%>">更新</a>
                     <a class="btn btn-danger" href ="userDeleteMaster?loginId=<%=user.getId()%>&page_num=<%= pageNum%>">削除</a>
                   </td>
                 </tr>
                 <%} %>
               </tbody>
             </table>
           </div>
         </div>
      </div>



      <div class="text-center">
			<ul class="pagination">

				<%
				    boolean flagMin = true;
				    boolean flagMax = true;
				    String logId = !loginId.equals("__noId")?"&loginId="+loginId+"&":"";
				    String sDate = !startDate.equals("nodate")?"&startDate="+startDate+"&":"";
				    String eDate = !endDate.equals("nodate")?"&endDate="+endDate+"&":"";
					if (pageNum == 1) {
				%>
				<!-- １ページ戻るボタン  -->
				<li><a><font color="black">≪ 前へ</font></a></li>
				<% }else{%>
				<li><a href="<%="UserList?searchName=" + searchName+logId+sDate+eDate+ "&page_num=" + (pageNum - 1)%>">≪ 前へ</a></li>
				<%
					}
				%>
				<!-- ページインデックス -->
				<%
					for (int j = pageNum - 2; j <= pageNum + 2; j++) {
				%>
				<!-- マイナスページが生成されないように -->
				<%
					if (j <= 0) {
							j = 1;
						}
				%>
				<%
				if( pageNum > 3 && flagMin == true){
					flagMin = false;
				 %>
				<li><a href="<%="UserList?searchName=" + searchName +logId+sDate+eDate+ "&page_num=" + 1%>"><%=1 %></a></li>
				<%if(!(pageNum == 4)){ %>
				<li><a>⋯</a></li>
				<%}
				}
				%>
				<li <%if (pageNum == j) {%> class="active" <%}%>>
				<a href="<%="UserList?searchName=" + searchName +logId+sDate+eDate+ "&page_num=" + j%>"><%=j%></a></li>
				<!-- MAXを越さないように -->
				<%
					if (pageMax <= j) {
							break;
						}
				%>
				<%
					}
				%>
				<%
				if( pageNum < pageMax-2 && flagMax == true){
					flagMax = false;
				if(!(pageNum==pageMax-3)){%>
				<li><a>⋯</a></li>
				<%} %>
				<li><a href="<%="UserList?searchName=" + searchName +logId+sDate+eDate+ "&page_num=" + pageMax%>"><%=pageMax %></a></li>
				<%} %>
				<!-- 1ページ送るボタン -->
				<%
					if (pageNum == pageMax || pageMax == 0) {
				%><li><a><font color="black">次へ ≫</a></li>
				<%}else{ %>
				<li><a href="<%="UserList?searchName=" + searchName+logId+sDate+eDate+ "&page_num=" + (pageNum + 1)%>">次へ ≫</a></li>
				<%
					}
				%>
			</ul>
		</div>



  </body>
</html>