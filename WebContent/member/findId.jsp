<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery Migrate -->
<script src="https://code.jquery.com/jquery-migrate-3.3.1.min.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		fn_findId();
	});
	
		function fn_findId(){
		$('#findId').click(function(){
			if($('#mEmail').val() == ''){
				alert('이메일을 입력하세요.');
				return;
			} else{
				$.ajax({
					url: '/HP/findid.member',
					type: 'post',
					data: 'mEmail=' + $('#mEmail').val(),
				
					dataType: 'json',
					success: function(result){
						if(result.resultId == 'none'){
							$('#findIdResult').text('일치하는 회원 정보가 없습니다.');
							$('#findIdResult').css('color', 'red');
						} else{
							$('#findIdResult').text('회원님의 아이디는 ' + result.resultId + '입니다.');
							$('#findIdResult').css('color', 'blue');
						}
					},
					error: function(){
						alert('실패');
					}
				});
			}
		});	
		}
</script>
</head>
<body>
	
<br/><br/>
	<div style="text-align: center;">
	
	<div><h1>SoloPag</h1></div>
	
	<form method="post">
		가입 당시 이메일<br/>
		<input id="mEmail" type="text" name="mEmail" autofocus /><br/><br/>
		<input id="findId" type="button" value="아이디 찾기" />
		<input type="button" value="로그인" onclick="location.href='member/login.jsp'" />
	</form>
	<br/><br/>
	
	<div id="findIdResult"></div>
	
	</div>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<%@ include file="/template/footer.jsp" %>