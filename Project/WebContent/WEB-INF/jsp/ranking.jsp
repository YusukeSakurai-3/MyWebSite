<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
<title>ランキング</title>
<jsp:include page="/baselayout/head.html" />
 <jsp:include page="/baselayout/header.jsp" />
 <%
  ArrayList<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>) request.getAttribute("itemList");
 %>
  <body>

    <br><br><br><br>



    <!-- body -->

    <div class="container">

      <h1 align="center">今人気のある商品</h1>

        	 <div class="row">
        	 <%
        	 int i = 0;
        	 for(ItemDataBeans item : itemList){
             i++;
        	 %>


            <div class="col-md-3">
              <br>
              <div class="card">
                  <div class="card-image">
                    <a href="Item?item_id=<%=item.getId()%>">
               <%if(i==1){ %>
                  <div class="side-corner-tag">
                        <img src="<%="img/" + item.getFileName()%>" alt="" width="250" height="250"/>
                        <p><span style="background-color: #e6b422;">No.1</span></p>
                    </div>
                 <%} else if(i==2){%>
                 <div class="side-corner-tag">
                      <img src="<%="img/"+item.getFileName() %>" alt="" width="250" height="250"/>
                      <p><span style="background-color: #afafb0;">No.2</span></p>
                    </div>
                    <%}else if(i==3){ %>
                      <div class="side-corner-tag">
                          <img src="<%="img/"+item.getFileName() %>" alt="" width="250" height="250"/>
                          <p><span style="background-color:#ac6b25;">No.3</span></p>
                      </div>
                     <%}else { %>
                     <img  src="<%="img/"+item.getFileName() %>"  width="260" height="265" />
                    <%} %>
                    </a>
                   <div class="card-content">
                    <span class="card-title"><%=item.getName() %></span>
                    <%=item.getPrice()%>円
                  </div>
                 </div>
                </div>
              </div>
                <%
				 if (i % 4 == 0) {
				%>
				</div>
				<br><br><br>
				<div class="container">
				<div class="row">
				<%
				}
				%>
               <%} %>
  <br>
              </div><br><br><br>

             <br><br><br>





  </body>
</html>