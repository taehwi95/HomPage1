<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery Migrate -->
<script src="https://code.jquery.com/jquery-migrate-3.3.1.min.js"></script>

<script type="text/javascript">
	function fn_login(f){
		if(f.mId.value == '' || f.mPw.value==''){
			alert('아이디, 비밀번호를 입력하세요');
			return;
		}
		f.action='/HP/login.member';
		f.submit();
	}
</script>
</head>
<body>
	
<br/><br/>
	<div style="text-align: center;">
	
	<div><h1>HomePage</h1></div>
	
	<form method="post">
	<div><h1><input type="text" name="mId" placeholder="아이디" /></h1></div>
	<div><h1><input type="password" name="mPw" placeholder="비밀번호" /></h1></div>
	<div><h1><input type="button" value="로그인" onclick="fn_login(this.form)" /></h1></div>
	</form>
	
	<a href="/HP/findIdPage.member">아이디 찾기</a>&nbsp;&nbsp;
	<a href="/HP/findPwPage.member">비밀번호 찾기</a>&nbsp;&nbsp;
	<a href="/HP/joinPage.member">회원가입</a>
	
	</div>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<%@ include file="/template/footer.jsp" %>