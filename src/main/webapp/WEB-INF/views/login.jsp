<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<style type="text/css">
div {
	width: 200px;
	height: 200px;
	background-color: white;color:white;
	padding: 50px;
	margin: 100px auto;
}
input{ 	padding: 7px;
		margin: 15px auto; }
input[type=text],input[type=password]{
	border-radius: 4px; border: 2px solid #ccc;
}
input[type=submit], input[type=button] {
	padding: 7px 15px;
	margin: 7px 10px;
	background:  #dcedc8;
	color: white;
	border: none;
	cursor: pointer;
	width:35%;
	
}
</style>
<link rel="stylesheet" href="resources/css/flexbox2.css?v=3">
</head>
<body>
<div align="center">
	<h1>로그인</h1>
	<form method="post" action="login">
	<table>
		<input type="text" name="id" placeholder="아이디 입력하세요.">
		<input type="password" name="password" placeholder="비밀번호 입력하세요.">
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="로그인">
				
				<input type="button" value="처음으로" 
onclick="location.href='${pageContext.request.contextPath}'"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>