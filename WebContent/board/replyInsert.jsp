<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글작성</title>
<script>
	function fn_replyInsert(f) {
		if(f.bTitle.value == ''){
			alert('제목을 입력하세요');
			return;
		}
		if(confirm('댓글을 등록하시겠습니까?')){
			f.action='/HP/replyInsert.board';
			f.submit();
		}
	}
</script>
</head>
<body>
	<div style="text-align: center;">
		<form method="post">
			<h3>댓글 작성</h3>
			
			작성자<br/>
			<input type="text" name="mId" value="${loginUser.mId}" readonly /><br/><br/>
			
			제목<br/>
			<input type="text" name="bTitle" autofocus /><br/><br/>
			
			내용<br/>
			<textarea name="bContent" rows="5" cols="30"></textarea><br/><br/>
			
			<input type="hidden" name="page" value="${param.page}" />	<!-- boardView.jsp에서 hidden으로 넘긴 값 -->
			<input type="hidden" name="bNo" value="${param.bNo}" />		<!-- boardView.jsp에서 hidden으로 넘긴 값 -->
			
			<input type="button" value="댓글등록" onclick="fn_replyInsert(this.form)" />
			<input type="reset" value="다시 작성하기" />
			<input type="button" value="목록으로 이동" onclick="location.href='/HP/boardList.board'" />
			
		</form>
	</div>

<%@ include file="/template/footer.jsp" %>