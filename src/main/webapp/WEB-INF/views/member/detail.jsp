<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Day3[]- db 회원 등록</title>
<style>
	ul{
		list-style: none;
		padding-inline-start:0px;  /* 목록 들여쓰기 없애기 */
	}
	ul #name{
		padding : 5px  20px;
		margin : 5px 0px;
		background-color: tomato;
		color:white;
	}
	li {
		padding : 5px  20px;
	}
</style>
<link rel="stylesheet" href="./resources/css/mup.css?v=3"> 
</head>
<body>
<header><%@ include file="../top.jsp" %></header>

<input type="hidden" name="idx" value="${member.idx}" >  
	<div class="topbar"></div>
	<table style="width: 100%">
	<tr>
		<td class="name" id="name">이름 : ${member.name }님</td>
		<td class="nick">닉네임 : ${member.nick}</td>
		<td class="tel">전화번호 : ${member.tel}</td>
		<c:if test="${member.idx != 1}">
			<td class="poin">보유포인트 : ${member.point }</td>
		</c:if>
		<c:if test="${member.idx == 1}">
			<td class="poin">총 수익 : ${income}원</td>
		</c:if>
	</tr>
	</table>
 	<a class="mup" href="update">정보 수정</a>
 	<a class="mupo" href="../">돌아가기</a>

</body>
</html>