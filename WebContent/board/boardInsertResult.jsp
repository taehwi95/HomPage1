<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<script type="text/javascript">
	if(${param.result} > 0){
		alert('작성되었습니다.');
		location.href='/HP/boardList.board';
	} else{
		alert('작성 실패');
		history.back();
	}
</script>
</head>
<body>


<%@ include file="/template/footer.jsp" %>