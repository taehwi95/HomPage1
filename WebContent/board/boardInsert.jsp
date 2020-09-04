<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<script type="text/javascript">
	function fn_boardInsert(f) {
		if(f.bTitle.value == ''){
			alert('제목을 입력하세요.');
			return;
		}
		f.action='/HP/boardInsert.board';
		f.submit();
	}
</script>
</head>
<body>
	<div style="text-align: center;">
	<form method="post" enctype="multipart/form-data">
		<h3>게시글 작성</h3>
		
		작성자<br/>
		<input type="text" name="mId" value="${loginUser.mId}" readonly /><br/><br/>
		
		제목<br/>
		<input type="text" name="bTitle" autofocus /><br/><br/>
		
		첨부파일<br/>
		<input type="file" name="bFilename" /><br/><br/>
		
		내용<br/>
		<textarea name="bContent" rows="5" cols="30"></textarea><br/><br/>
		
		<input type="button" value="작성하기" onclick="fn_boardInsert(this.form)" />
		<input type="reset" value="다시 작성하기" />
		<input type="button" value="게시판으로 이동" onclick="location.href='/HP/boardList.board'" />
	</form>
	</div>

<%@ include file="/template/footer.jsp" %>