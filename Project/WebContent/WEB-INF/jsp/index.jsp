<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <title>商品一覧</title>
  <jsp:include page="/baselayout/head.html" />

  <jsp:include page="/baselayout/header.jsp" />
 <%
	ArrayList<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>) request.getAttribute("itemList");
%>
</head><br><br><br>
<body>

    <div class="container">
      <h1><font color="#ff9933">ECサイト</font></h1>
      <h1 align="center">商品一覧</h1>
    </div>

  <div class="container">
    <h4>オススメ商品<h4>
  	 <div class="row">
  	 <%
		for (ItemDataBeans item : itemList) {
		%>
				<div class="col-md-3">
					<div class="card">
						<div class="card-image">
							<a href="Item?item_id=<%=item.getId()%>"><img src="<%="./img/" + item.getFileName()%>"></a>
						</div>
						<div class="card-content">
							<span class="card-title"><%=item.getName()%></span>
							<p><%=item.getPrice()%>円
							</p>
						</div>
					</div>
				</div>
				<%
					}
				%>

      <!--  <div class="col-md-3">
        <br>
        <div class="card">
            <div class="card-image">
              <a href="item.html">
            <img   src="./img/matsutake.jpg"  width="260" height="250" 　/>
              </a>
             <div class="card-content">
              <br>
              <span class="card-title">美味しいもも</span>
              <br><br>
              10000000円
            </div>
           </div>
          </div>
        </div>



      <div class="col-md-3">
        <br>
        <div class="card">
            <div class="card-image">
              <a href="item.html">
            <img   src="./img/fd401266.jpg"  width="260" height="250" 　/>
             </a>
             <div class="card-content">
              <br>
              <span class="card-title">ぶどう</span>
              <br><br>
              10000000円
            </div>
           </div>
          </div>
        </div>




          <div class="col-md-3">
            <br>
            <div class="card">
                <div class="card-image">
                  <a href="item.html">
                <img   src="./img/fd401097.jpg"  width="260" height="250" 　/>
                 </a>
                 <div class="card-content">
                  <br>
                  <span class="card-title">美味しいもも</span>
                  <br><br>
                  10000000円
                </div>
               </div>
              </div>
            </div>

            <div class="col-md-3">
              <br>
              <div class="card">
                  <div class="card-image">
                    <a href="item.html">
                   <img  src="./img/fd400947.jpg"  width="260" height="250" 　/>
                    </a>
                    <div class="card-content">
                    <br>
                    <span class="card-title">美味しいもも</span>
                    <br><br>
                    10000000円
                  </div>
                 </div>
                </div>
              </div>
          </div>
          -->
        </div>

<br>
<br>
<br>


  <div class="container">
    <h4>最近見た商品<h4>
  	<div class="row">

      <div class="col-md-3">
        <br>
        <div class="card">
            <div class="card-image">
              <a href="item.html">
             <img  src="./img/matsutake.jpg"  width="260" height="250" 　/>
              </a>
              <div class="card-content">
              <br>
              <span class="card-title">美味しいもも</span>
              <br><br>
              10000000円
            </div>
           </div>
          </div>
        </div>



      <div class="col-md-3">
        <br>
        <div class="card">
            <div class="card-image">
              <a href="item.html">
            <img  src="./img/fd401266.jpg"  width="260" height="250" 　/>
             </a>
             <div class="card-content">
              <br>
              <span class="card-title">ぶどう</span>
              <br><br>
              10000000円
            </div>
           </div>
          </div>
        </div>


          <div class="col-md-3">
            <br>
            <div class="card">
                <div class="card-image">
                  <a href="item.html">
                <img  src="./img/fd401097.jpg"  width="260" height="250" 　/>
                 </a>
                 <div class="card-content">
                  <br>
                  <span class="card-title">美味しいもも</span>
                  <br><br>
                  10000000円
                </div>
               </div>
              </div>
            </div>

            <div class="col-md-3">
              <br>
              <div class="card">
                  <div class="card-image">
                    <a href="item.html">
                  <img  src="./img/fd400947.jpg"  width="260" height="250" 　/>
                    </a>
                    <div class="card-content">
                    <br>
                    <span class="card-title">美味しいもも</span>
                    <br><br>
                    10000000円
                  </div>
                 </div>
                </div>
              </div>
            </div>
          </div>





  </body>
</html>