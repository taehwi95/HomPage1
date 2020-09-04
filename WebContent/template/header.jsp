<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	if( title == null || title.isEmpty() ){
		title = "defaultPage";
	}
%>
<title><%=title%></title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery Migrate -->
<script src="https://code.jquery.com/jquery-migrate-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">

<script type="text/javascript">
	function fn_logout() {
		if(confirm('로그아웃 하시겠습니까?')){
			location.href="/HP/logout.member";
		}
	}
</script>
</head>
<body>

	<div style="text-align: center; padding: 10px;" id="wrap">
		<div id="header-wrap">
			<!-- 1. 로그인이 안된 상태 -->
			<c:if test="${sessionScope.loginUser eq null}">
				<h3 style="text-align: right;"><input type="button" value="로그인" onclick="location.href='/HP/loginPage.member'" />
				</h3>
			</c:if>
			<!-- 2. 로그인이 된 상태 -->
			<c:if test="${sessionScope.loginUser ne null }">
				${loginUser.mName}님 반갑습니다.
				<input type="button" value="로그아웃" onclick="fn_logout()" />
				<input type="button" value="내정보&수정하기" onclick="location.href='/HP/myPage.member'" />
			</c:if>	
		</div>
		
		<div style="text-align: center;" id="main-wrap">