<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイルアップロード</title>
</head>
<body>
<form method="POST" enctype="multipart/form-data" action="UploadServlet">
<input type="file" name="file"/><br />
<input type="submit" value="アップロード" />
</form>
<img src="/WebContent/WEB-INF/uploadsample/fd401097.jpg" width="260" height="250"/>
<img  src="./img/fd400947.jpg"  width="260" height="250" />
</body>
</html>