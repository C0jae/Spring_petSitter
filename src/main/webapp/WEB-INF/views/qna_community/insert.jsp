<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>동아리 커뮤니티</title>
<script type="text/javascript">
function post_data() {
	//var subject = frm1.subject.value;  //유효성 검사
	frm1.submit();
}
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/q_board.css?v=3">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/top_bottom.css?v=3">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
</head>
<body>
<header>
<%@ include file="../top.jsp" %>
</header>
<section>
<h3>qna 게시판</h3>
<hr>
<form name="frm1" method="post" action="q_save">
 <input type="hidden" name="ip" value="${pageContext.request.remoteAddr}">  <!-- 클라이언트 ip -->
 <input type="hidden" name="idx" value="${member.idx}">
 <table style="width:60%;">
 	<tr><td width="20%" class="td1">제목</td>
 		<td class="left"><input type="text" name="title" size="70" class="input1" required="required"></td>
 	</tr>
 	<tr><td class="td1">작성자</td>
 		<td class="left"><input type="text" name="nick" value="${member.nick}" size="70" class="input1" required="required"></td>
 	</tr>
 	
 	<tr><td class="td1">내용</td>  <!-- textarea 의 크기 : rows="20" cols="80" -->
 		<td class="left"><textarea  rows="20" cols="80" name="content" class="input1" required="required"></textarea></td>
 	</tr>
 	<tr><td colspan="2" align="center">
 	<input type="submit" value="저장" class="btn" >
 	<!-- <a class="button" href="javascript:post_data();">저장</a> -->
 	<input type="reset"  value="다시쓰기" class="btn">
 	<input type="button" value="목록" onclick="location.href='q_list?page=${page}'" class="btn">
 <!-- 	<a class="button" href="#">목록</a>  -->
 	</td></tr>
 </table>
 </form>
 </section>
 <%-- <%@ include file="../bottom.jsp" %> --%>
</body>
</html>