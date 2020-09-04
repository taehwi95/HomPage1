<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery Migrate -->
<script src="https://code.jquery.com/jquery-migrate-3.3.1.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css" rel="stylesheet">
<script type="text/javascript">
	
	<!-- 전체 게시글 보기 -->
	function fn_boardList(f) {
		f.action='/HP/boardList.board';
		f.submit();
	}
	
	function fn_boardInsertPage(f){
		f.action='/HP/boardInsertPage.board';
		f.submit();
	}
	
	function fn_queryBoardList(f){
		f.action='/HP/queryBoardList.board';
		f.submit();
	}
	
	function fn_myBoardList(f){
		f.action='/HP/myBoardList.board';
		f.submit();
	}
	
	
</script>
</head>
<body>
	
<br/><br/>
	<div style="text-align: center;">
	
	<div><h1>HomePage</h1></div>
	
	<form>
		<!-- 게시판 작성 버튼 : 로그인해야 작성할 수 있다. -->
		<input type="hidden" name="page" value="${page}" />
		<input type="button" value="홈페이지" onclick="location.href='index.jsp'" />
		<input type="button" value="전체 게시글 보기" onclick="fn_boardList(this.form)" />
		<c:if test="${loginUser eq null }">
			<input type="button" value="로그인" onclick="location.href='/HP/loginPage.member'" />
		</c:if>
		<c:if test="${loginUser ne null }">
			<input type="button" value="새 게시글 작성" onclick="fn_boardInsertPage(this.form)" />
			<input type="button" value="내 게시글 보기" onclick="fn_myBoardList(this.form)" />
			<input type="hidden" name="mId" value="${loginUser.mId}" />
		</c:if>
	</form>
	<br/>
	
	<form>
	<!-- 게시판 검색 -->
	게시글찾기
	<select name = "column">
		<option value="BTITLE">제목</option>
		<option value="BCONTENT">내용</option>
		<option value="BOTH">제목+내용</option>
	</select>
	<input type="text" name="query" />
	<input type="button" value="검색" onclick="fn_queryBoardList(this.form)" />
	<input type="hidden" name="page" value="${page}" />
	</form>
	<br/><br/>
	
	<!-- 게시판 목록 -->
	전체 ${totalRecord}개의 게시글<br/>
	<table style="margin: auto; border-collapse: collapse;" border="1" >
		<thead>
			<tr>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>첨부파일</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5">작성된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="bDto" items="${list}">
					<tr>
						<td>
							<!-- 1. 댓글은 bDepth 만큼 들여쓰끼 -->
							<c:forEach begin="1" end="${bDto.bDepth}" step="1">
								&nbsp;&nbsp;&nbsp;
							</c:forEach>
							
							<!-- 2. 댓글은 제목 앞에 ㄴRe. 를 표시한다. -->
							<c:if test="${bDto.bDepth ne 0}">
								ㄴRe.
							</c:if>
							
							<!-- 3. 삭제되지 않은 게시글 -->
							<c:if test="${bDto.bDelete eq 0}">
								<a style="text-decoration: none;" href="/HP/boardView.board?bNo=${bDto.bNo}&page=${page}">${bDto.bTitle}</a>
							</c:if>
							
							<!-- 3. 삭제된 게시글(링크를 제공하지 않는다.) -->
							<c:if test="${bDto.bDelete eq -1}">
								${bDto.bTitle}(삭제된 게시글)
							</c:if>
						</td>
						
						<td>${bDto.mId}</td>
						<td>${bDto.bRegdate}</td>
						<td>${bDto.bHit}</td>
						<td>
							<c:if test="${bDto.bFilename eq null }">없음</c:if>
							<c:if test="${bDto.bFilename ne null }">
								<c:if test="${bDto.bDelete eq 0 }">
									<a style="text-decoration: none;" href="/HP/download.board?bFilename=${bDto.bFilename}">${bDto.bFilename}&nbsp;<i class="fas fa-file"></i></a>
								</c:if>
								<c:if test="${bDto.bDelete eq -1 }">
									${bDto.bFilename}
								</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">${paging}</td>
			</tr>
		</tfoot>
	</table>
		
	</div>

<%@ include file="/template/footer.jsp" %>