<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<%@page import=" java.util.HashMap"%>
<!DOCTYPE html>
<html lang="ja">
<title>商品マスタ一覧</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />

<%
ArrayList<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>) request.getAttribute("itemList");
String searchWordMaster = (String) session.getAttribute("searchWordMaster");
int pageMax = (int) request.getAttribute("pageMax");
int pageNum = (int) request.getAttribute("pageNum");
int itemCount = (int)request.getAttribute("itemCount");
int itemNum = (int)request.getAttribute("itemNum");
boolean searchCheck = (boolean)request.getAttribute("searchCheck");
HashMap<Integer, Integer> purchaseNum= (HashMap<Integer, Integer>)request.getAttribute("purchaseNum");
int itemId = request.getAttribute("itemId")!=null?(int)request.getAttribute("itemId"):-1;
int startPrice = request.getAttribute("startPrice")!=null?(int)request.getAttribute("startPrice"):-1;
int endPrice = request.getAttribute("endPrice")!=null?(int)request.getAttribute("endPrice"):-1;
String startDate = request.getAttribute("startDate")!=null?(String)request.getAttribute("startDate"):"nodate";
String endDate = request.getAttribute("endDate")!=null?(String)request.getAttribute("endDate"):"nodate";
String createMessage = (String)request.getAttribute("createMessage");
String deleteMessage = (String)request.getAttribute("deleteMessage");
String updateMessage = (String)request.getAttribute("updateMessage");
%>
  <body>

      <br><br><br>

    <!-- body -->
    <div class="container">



      <h1 align="center">商品マスタ一覧</h1>
      <div class="text-right">
        <a href="ItemMasterCreate">商品登録</a>
      </div>
 <%
 if(createMessage!=null) {
 %>
  <div class="alert alert-success"><%=createMessage %></div>
 <%} %>
  <%
 if(deleteMessage!=null) {
 %>
  <div class="alert alert-success"><%=deleteMessage %></div>
 <%} %>
  <%
 if(updateMessage!=null) {
 %>
  <div class="alert alert-success"><%=updateMessage %></div>
 <%} %>


      <div class="panel-body">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="panel-title">検索条件</div>
            </div>
            <div class="panel-body">
              <form action="ItemMaster" class="form-horizontal">
                <div class="form-group">
                  <label for="code" class="control-label col-sm-2">商品ID</label>
                  <div class="col-sm-6">
                    <input type="text" name="itemId"  class="form-control"  value="<%=itemId!=-1?""+itemId:""%>"/>
                  </div>
                </div>
                <div class="form-group">
                  <label for="name" class="control-label col-sm-2">商品名</label>
                  <div class="col-sm-6">
                    <input type="text" name="search_word"  class="form-control"  value="<%=searchWordMaster %>" />
                  </div>
                </div>
             <!-- 登録日 -->
                <div class="form-group">
                  <label for="continent" class="control-label col-sm-2">登録日</label>
                  <div class="row">
                    <div class="col-sm-2">
                      <input type="date" name="startDate"  class="form-control" size="30"/>
                    </div>
                    <div class="col-xs-1 text-center">
                      ~
                    </div>
                    <div class="col-sm-2">
                      <input type="date" name="endDate"  class="form-control" size="30"/>
                    </div>
                </div>
                </div>
                <!-- 価格 -->
                <div class="form-group">
                  <label for="continent" class="control-label col-sm-2">価格</label>
                  <div class="row">
                    <div class="col-sm-2">
                      <input type="text" name="startPrice"  class="form-control" size="30" value="<%=startPrice!=-1?""+startPrice:""%>"/>
                    </div>
                    <div class="col-xs-1 text-center">
                      ~
                    </div>
                    <div class="col-sm-2">
                      <input type="text" name="endPrice" class="form-control" value="<%=endPrice!=-1?""+endPrice:""%>" />
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
          商品総数:<%=itemNum %>   <%if(searchCheck){ %> 検索結果:<%=itemCount %> <% }%>
             <table class="table table-striped table-bordered" class="table table-bordered">
               <thead>
                 <tr>
                   <th>商品ID</th>
                   <th>商品名</th>
                   <th>登録日</th>
                   <th>価格</th>
                   <th>購入数</th>

                   <th></th>
                 </tr>
               </thead>
               <tbody>
  	           <%
					for (ItemDataBeans item : itemList) {
					int  cnt = purchaseNum.get(item.getId());
	            %>
                 <tr>
                   <td><%=item.getId() %></td>
                   <%if(item.getName().length()<=20) {%>
                   <td><%=item.getName() %></td>
                   <%}else{ %>
                   <td><%=item.getName().substring(0,20) %>⋯</td>
                   <%} %>
                   <td><%=item.getFormatCreateDate() %></td>
                   <td><%=item.getPrice()%>円</td>
                   <td><%=cnt %></td>
                   <td>
                     <a class="btn btn-primary" href="ItemMasterDetail?item_id=<%=item.getId()%>&page_num=<%= pageNum%>">詳細</a>
                     <%if(cnt==0) {%>
                     <a class="btn btn-success" href="ItemMasterUpdate?item_id=<%=item.getId()%>&page_num=<%= pageNum%>">更新</a>
                     <a class="btn btn-danger" href ="ItemMasterDelete?item_id=<%=item.getId()%>&page_num=<%= pageNum%>">削除</a>
                     <%} %>
                   </td>
                 </tr>
                 <%} %>
               </tbody>
             </table>
           </div>
         </div>
      </div>
      <br><br><br>
      <div class="text-center">
			<ul class="pagination">

				<%
				    String id = itemId!=-1?"&itemId="+itemId+"&":"";
				    String sPrice = startPrice!=-1?"&startPrice="+startPrice+"&":"";
				    String ePrice = endPrice!=-1?"&endPrice="+endPrice+"&":"";
				    String sDate = !startDate.equals("nodate")?"&startDate="+startDate+"&":"";
				    String eDate = !endDate.equals("nodate")?"&endDate="+endDate+"&":"";
				    boolean flagMin = true;
				    boolean flagMax = true;
					if (pageNum == 1) {
				%>
				<!-- １ページ戻るボタン  -->
				<li><a><font color="black">≪ 前へ</font></a></li>
				<% }else{%>
				<li><a href="<%="ItemMaster?search_word=" + searchWordMaster+id+ sDate+eDate+ sPrice+ePrice+ "&page_num=" + (pageNum - 1)%>">≪ 前へ</a></li>
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
				<li><a href="<%="ItemMaster?search_word=" + searchWordMaster +id+sDate+eDate+ sPrice+ePrice+ "&page_num=" + 1%>"><%=1 %></a></li>
				<%if(!(pageNum == 4)){ %>
				<li><a>⋯</a></li>
				<%}
				}
				%>
				<li <%if (pageNum == j) {%> class="active" <%}%>><a href="<%="ItemMaster?search_word=" + searchWordMaster+id+sDate+eDate+sPrice+ePrice+ "&page_num=" + j%>"><%=j%></a></li>
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
				<li><a href="<%="ItemMaster?search_word=" + searchWordMaster+ id+sDate+eDate+sPrice+ePrice+"&page_num=" + pageMax%>"><%=pageMax %></a></li>
				<%} %>
				<!-- 1ページ送るボタン -->
				<%
					if (pageNum == pageMax || pageMax == 0) {
				%><li><a><font color="black">次へ ≫</a></li>
				<%}else{ %>
				<li><a href="<%="ItemMaster?search_word=" + searchWordMaster+id+sDate+eDate+sPrice+ePrice+"&page_num=" + (pageNum + 1)%>">次へ ≫</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>




  </body>
</html>