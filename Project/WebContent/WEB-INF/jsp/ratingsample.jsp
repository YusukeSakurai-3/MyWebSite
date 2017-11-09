<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="ec.EcHelper"%>
<!DOCTYPE html>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-stars.css"/ rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="js/jquery.barrating.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

     <style type="text/css">

     .br-theme-css-stars .br-widget a {
       font-size: 5rem;
       }


     .star-rating3 {
       font-size: 0;
       white-space: nowrap;
       display: inline-block;
       width: 50px;
       height: 10px;
       overflow: hidden;
       position: relative;
       background: url('data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4IiB3aWR0aD0iMjBweCIgaGVpZ2h0PSIyMHB4IiB2aWV3Qm94PSIwIDAgMjAgMjAiIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDIwIDIwIiB4bWw6c3BhY2U9InByZXNlcnZlIj48cG9seWdvbiBmaWxsPSIjREREREREIiBwb2ludHM9IjEwLDAgMTMuMDksNi41ODMgMjAsNy42MzkgMTUsMTIuNzY0IDE2LjE4LDIwIDEwLDE2LjU4MyAzLjgyLDIwIDUsMTIuNzY0IDAsNy42MzkgNi45MSw2LjU4MyAiLz48L3N2Zz4=');
       background-size: contain;
     }
     .star-rating3 i {
       opacity: 0;
       position: absolute;
       left: 0;
       top: 0;
       height: 100%;
       width: 20%;
       z-index: 1;
       background: url('data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4IiB3aWR0aD0iMjBweCIgaGVpZ2h0PSIyMHB4IiB2aWV3Qm94PSIwIDAgMjAgMjAiIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDIwIDIwIiB4bWw6c3BhY2U9InByZXNlcnZlIj48cG9seWdvbiBmaWxsPSIjRkZERjg4IiBwb2ludHM9IjEwLDAgMTMuMDksNi41ODMgMjAsNy42MzkgMTUsMTIuNzY0IDE2LjE4LDIwIDEwLDE2LjU4MyAzLjgyLDIwIDUsMTIuNzY0IDAsNy42MzkgNi45MSw2LjU4MyAiLz48L3N2Zz4=');
       background-size: contain;
     }
     .star-rating3 input {
       -moz-appearance: none;
       -webkit-appearance: none;
       opacity: 0;
       display: inline-block;
       width: 20%;
       height: 100%;
       margin: 0;
       padding: 0;
       z-index: 2;
       position: relative;
     }
     .star-rating3 input:hover + i,
     .star-rating3 input:checked + i {
       opacity: 1;
     }
     .star-rating3 i ~ i {
       width: 40%;
     }
     .star-rating3 i ~ i ~ i {
       width: 60%;
     }
     .star-rating3 i ~ i ~ i ~ i {
       width: 80%;
     }
     .star-rating3 i ~ i ~ i ~ i ~ i {
       width: 100%;
     }
     </style>

 <script>
     $(function() {
   	  $('#example').barrating({
   	    theme: 'bootstrap-stars'
   	  });
   	});
</script>

</head>
<body>
<strong >Choose a rating</strong>

<%
int star = request.getAttribute("star")!=null?(int)request.getAttribute("star"):2;
%>

<%=star %>
<form action="RatingSample" method="POST">

<select name="rating" id="example">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
</select>

<!-- <span class="star-rating3">
      <input type="radio" name="rating" value="1"><i></i>
      <input type="radio" name="rating" value="2"><i></i>
      <input type="radio" name="rating" value="3"><i></i>
      <input type="radio" name="rating" value="4"><i></i>
      <input type="radio" name="rating" value="5"><i></i>
</span>
-->

<button class="btn btn-primary" type="submit">送信</button>
</form>


</body>
</html>