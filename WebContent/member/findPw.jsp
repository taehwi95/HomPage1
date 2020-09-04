<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery Migrate -->
<script src="https://code.jquery.com/jquery-migrate-3.3.1.min.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		$('#findPw').click(function(){
			if($('#mId').val() == '' || $('#mPhone').val() == ''){
				alert('아이디와 전화번호를 입력하세요.');
				return;
			}else{
				$.ajax({
					url: '/HP/findPw.member',
					type: 'post',
					data: $('#f').serialize(),
					
					dataType: 'json',
					success: function(obj){
						if(obj.isMember){
							$('#findPwResult').text('임시 비밀번호는 ' + obj.randomPw + ' 입니다.');
							$('#findPwResult').css('color', 'blue');
						} else{
							$('#findPwResult').text('해당하는 회원을 찾을 수 없습니다.');
							$('#findPwResult').css('color', 'red');
						}
					},
					error: function(){
						alert('실패');
					}
				});
			}
		});
		
	});
	
</script>
</head>
<body>
	
<br/><br/>
	<div style="text-align: center;">
	
	<div><h1>SoloPag</h1></div>
	
	<form id="f" method="post">
		아이디<br/>
		<input id="mId" type="text" name="mId" /><br/><br/>
		가입 당시 휴대폰<br/>
		<input id="mPhone" type="text" name="mPhone" /><br/><br/>
		<input id="findPw" type="button" value="비밀번호찾기" />
		<input type="button" value="로그인" onclick="location.href='member/login.jsp'" />
	</form>
	
	<br/><br/>
	
	<div id="findPwResult"></div>
	
	</div>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<%@ include file="/template/footer.jsp" %>