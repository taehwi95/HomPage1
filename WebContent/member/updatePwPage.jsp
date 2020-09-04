<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery Migrate -->
<script src="https://code.jquery.com/jquery-migrate-3.3.1.min.js"></script>
<script type="text/javascript">

	var pwPass = false;
	
	$(document).ready(function(){
		
		// 1. 현재비밀번호 확인
		$('#mPw').blur(function(){
			if($('#mPw').val() != '${loginUser.mPw}'){
				$('#mPwCheck').text('현재 비밀번호가 틀립니다.');
				$('#mPwCheck').css('color', 'red');
				pwPass = false;
			} else{
				$('#mPwCheck').text('현재비밀번호 일치');
				$('#mPwCheck').css('color', 'blue').css('font-weight', 'bold');
				pwPass = true;
			}
		});
		
		// 2. 새 비밀번호 입력
		var regExpPw = /^[0-9]+$/;
		$('#NewmPw').keyup(function(){
			if($('#NewmPw').val() == '${loginUser.mPw}'){
				$('#NewmPwCheck').text('현재 비밀번호가 동일한 비밀번호입니다. 수정바랍니다.');
				$('#NewmPwCheck').css('color', 'red');
				pwPass = false;
			} else{
			if(regExpPw.test($('#NewmPw').val())){
				$('#NewmPwCheck').text('사용 가능한 비밀번호 입니다.');
				$('#NewmPwCheck').css('color', 'blue').css('font-weight', 'bold');
				pwPass = true;
			} else{
				$('#NewmPwCheck').text('숫자만 가능합니다.');
				$('#NewmPwCheck').css('color', 'red');
				pwPass = false;
			}
			}
		});
		
		// 3. 새 비밀번호 확인
		$('#NewmPw2').keyup(function(){
			if($('#NewmPw').val() == $('#NewmPw2').val()){
				$('#NewmPw2Check').text('비밀번호가 일치합니다.');
				$('#NewmPw2Check').css('color', 'blue').css('font-weight', 'bold');
				pwPass = true;
			} else{
				$('#NewmPw2Check').text('비밀번호가 일치하지 않습니다.');
				$('#NewmPw2Check').css('color', 'red');
				pwPass = false;
			}
		});
		
		// 4. 비밀번호 수정
		$('#UpdatePwbtn').click(function(){
			if(pwPass){
				$.ajax({
					url: '/HP/updatePw.member',
					type: 'post',
					data: 'NewmPw=' + $('#NewmPw').val(),
					
					dataType: 'json',
					success: function(obj){
						if(obj.isSuccess){
							alert('비밀번호가 수정되었습니다.');
							location.href='index.jsp';
						} else{
							alert('비밀번호가 수정되지 않았습니다.');
							history.back();
						}
					},
					error: function(){
						alert('실패');
					}
				});
			} else{
				alert('비밀번호를 확인하세요.');
			}
			
		});
	});

</script>

</head>
<body style="text-align: center;">

	<form method="post">
	
		${loginUser.mName}님 반갑습니다.<br/><br/>
		현재 비밀번호<br/>
		<input id="mPw" type="password" name="mPw" /><br/>
		<div id="mPwCheck"></div><br/><br/>
		
		새 비밀번호<br/>
		<input id="NewmPw" type="password" name="NewmPw" /><br/>
		<div id="NewmPwCheck"></div><br/><br/>
		
		새 비밀번호 확인<br/>
		<input id="NewmPw2" type="password" name="NewmPw2" /><br/>
		<div id="NewmPw2Check"></div><br/><br/>
		
		<input id="UpdatePwbtn" type="button" value="비밀번호 변경" />
		
	</form>

</body>
</html>