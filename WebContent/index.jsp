<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:include page="/template/header.jsp">
	<jsp:param value="SoloPg" name="title"/>
</jsp:include>

	<h1>HomePage</h1>
	<a href="/HP/boardList.board">게시판</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="https://blog.naver.com/ym7011">블로그</a>

<%@ include file="/template/footer.jsp" %>