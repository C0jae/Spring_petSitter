<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Gugi&display=swap');
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/join.css" > 
</head>
<body>
	<h3>본인 인증</h3>
<script type="text/javascript">
	function check(){
		const obj = document.frmAuth;
		if(obj.auth.checked == true){
			obj.submit();
		}else {
			alert('휴대폰 인증이 필요합니다.');
		}
	}
</script>
<form action="join" method="post" name="frmAuth">
	<input type="hidden" name="step" value="1">
	<input type="text" placeholder="이름을 입력하세요" name="name">
	<input type="tel" placeholder="휴대폰 번호를 입력하세요" name="tel">
	<input type="checkbox" name="auth">
	<input type="button" value="다음" onclick="check()"> 
</form>
	
</body>
</html>