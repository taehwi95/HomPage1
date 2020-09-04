<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글정보</title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery Migrate -->
<script src="https://code.jquery.com/jquery-migrate-3.3.1.min.js"></script>

<script type="text/javascript">
	function fn_boardDelete(f) {
		if(confirm('해당 게시글을 삭제 하시겠습니까?')){
			f.action = '/HP/boardDelete.board?bNo=${bDto.bNo}&page=${page}';	
			f.submit();
		}
	}
	
	function fn_updateBoard(f) {
		if(confirm('해당 게시글을 수정 하시겠습니까?')){
			f.action = '/HP/updateBoard.board';	
			f.submit();
		}
	}
	
	function fn_deleteFile(f) {
		if(confirm('파일을 삭제하시겠습니까?')){
			f.action = '/HP/deleteFile.board';
			f.submit();
		}
	}
	
	function fn_replyInsertPage(f) {
		f.action = "/HP/replyInsertPage.board?bNo=${bDto.bNo}&page=${page}";
		f.submit();
	}
</script>
</head>
<body>
	<div style="text-align: center;">
	<form method="post" enctype="multipart/form-data">
	<c:if test="${bDto.bDepth ne 0}">
		<h3>${bDto.bNo}번 게시글 댓글</h3>
	</c:if>	
	<c:if test="${bDto.bDepth eq 0}">
		<h3>${bDto.bNo}번 게시글</h3>
	</c:if>	
		작성자<br/>
		<input type="text" name="mId" value="${bDto.mId}" readonly /><br/><br/>
		
		제목<br/>
		<input type="text" name="bTitle" value="${bDto.bTitle}" readonly /><br/><br/>
		
		<c:if test="${bDto.bFilename eq null}">
			[기존첨부 : 없음]
		</c:if>
		<c:if test="${bDto.bFilename ne null}">
			[기존첨부 : <a href="/HP/download.board?bFilename=${bDto.bFilename}">${bDto.bFilename}</a>]
			&nbsp;&nbsp;
			<c:if test="${loginUser.mId eq bDto.mId }">
			<input type="button" value="첨부삭제" onclick="fn_deleteFile(this.form)" />
			</c:if>
		</c:if><br/><br/>
		
		<c:if test="${loginUser.mId eq bDto.mId }">
		새로운 첨부파일<br/>
		<input type="file" name="bFilename" />
		</c:if>
		<br/><br/>
		내용<br/>
		<input type="text" name="bContent" value="${bDto.bContent}" /><br/><br/>
		
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="bNo" value="${bDto.bNo}" />
		<input type="button" value="게시판으로 이동" onclick="location.href='/HP/boardList.board'" />
	
		<!-- 댓글달기 -->
		<!-- 로그인을 해야만 댓글을 달 수 있다. -->
		<!-- 대댓글 방지를 위해 원글에만 댓글달기를 표시한다. -->
		<c:if test="${loginUser ne null and bDto.bDepth eq 0}">
			<input type="button" value="댓글달기" onclick="fn_replyInsertPage(this.form)" />
		</c:if>
		
		<!-- 게시글 작성자만 수정/삭제할 수 있다. -->
		<c:if test="${loginUser.mId eq bDto.mId}">
			<input type="button" value="수정하기" onclick="fn_updateBoard(this.form)" />
			<input type="button" value="삭제하기" onclick="fn_boardDelete(this.form)" />
		</c:if>
	</form>
	</div>

<%@ include file="/template/footer.jsp" %>