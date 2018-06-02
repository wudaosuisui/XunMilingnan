<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>XunMilingnan/radio/addradcat</h3>
<form action = "radio/addradcat" method="post"
enctype="multipart/form-data"> 
name: <input type="text" name="name" value="radcat1"><br>
sortNumber: <input type="text" name="sortNumber" value="0"><br>
img : <input type="file" name="img"><br>
<input type="submit"><br>
</form>
<br><br><br><br>

<h3>XunMilingnan/radio/updradcat</h3>
<form action = "radio/updradcat" method="post" 
enctype="multipart/form-data"> 
id: <input type="text" name="radCatId"><br>
name: <input type="text" name="name"><br>
sortNumber: <input type="text" name="sortNumber"><br>
img : <input type="file" name="img"><br>
<input type="submit"><br>
</form>



</body>
</html>