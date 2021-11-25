<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
@import url('https://fonts.googleapis.com/css2?family=Gugi&display=swap');
</style>

<link rel= "stylesheet" href="./resources/css/top_bottom.css?v=3">
<script>
//HeaderToggle
var prevScrollPos = window.pageYOffset;
console.log("first Y offset: "+prevScrollPos)	//first value : 0
window.onscroll = headerbarToggle
function headerbarToggle(){
	var header = document.getElementById("headerbar");
	
	var currentScrollPos = window.pageYOffset;	// current Y offset
	if (prevScrollPos < currentScrollPos) {
		header.style.opacity = '0.8';
	}else{
		header.style.opacity = '0';
	}
}
	
</script>
<header>
<div id="headerbar" onscroll="headerbarToggle()"></div>

<a class="home" href='home'>개잘돌봄</a>
<section>
<c:if test="${member ==null }">
	<a class="login" href="login">로그인</a><br>
	<a class="register" href="join?start=1">회원가입</a><br>
</c:if>
<c:if test="${sessionScope.member !=null }">
<a class="mlist" href="detail">나의 정보</a><br>
<a class="logout" href="logout">로그아웃</a><br>
	<a class="infor">${member.nick}님  </a>
	<a class="point"  href="updatepoint">포인트 : ${member.point} </a> 
</c:if>
</section>

   <nav class="navMenu">
   	<div style="display: flex;">
        <a class="menu" href="join">펫시터 찾기</a>
        <a class="menu" href="rblist">후기 게시판</a>
        <a class="menu" href="q_list">이용 문의</a>
        <a class="menu" href="https://forms.gle/pXuxMHSjdb5edSr5A">펫시터 지원</a>
	   	<a class="menu" style="width: 150px;" href="ps_boardWrite">펫시터 게시글 작성</a>
       	<div class="dot"></div>
   	</div>
   </nav>
   
</header>