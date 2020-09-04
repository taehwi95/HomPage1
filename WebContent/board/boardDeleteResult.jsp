<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if(${param.result} > 0){
		alert('삭제되었습니다.');
		location.href='/HP/boardList.board?page=' + ${param.page};
	} else{
		alert('삭제되지 않았습니다.');
		history.back();
	}
</script>
</head>
<body>


<%@ include file="/template/footer.jsp" %>