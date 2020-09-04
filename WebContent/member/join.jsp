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
	
	var idPass = false;
	var pwPass = false;
	var emailPass = false;
	var phonePass = false;
	
	$(document).ready(function(){
		
		// 1. 아이디 체크
		var regExpId = /^[a-z][a-z0-9]{4,19}$/;
		$('#mId').blur(function(){
			$.ajax({
				url: '/HP/idCheck.member',
				type: 'post',
				data: 'mId=' + $('#mId').val(),
				
				dataType: 'json',
				success: function(obj){
					if(regExpId.test($('#mId').val())){		// 정규식을 만족하면
						if (obj.isPossible){
							$('#idCheck').text('사용할 수 있는 아이디입니다.');
							$('#idCheck').css('color', 'blue').css('font-weight', 'bold');
							idPass = true;
						} else{
							$('#idCheck').text('이미 가입된 아이디입니다.');
							$('#idCheck').css('color', 'red');
							idPass = false;
						}
					} else{
						$('#idCheck').text('5~20자 사이 소문자와 숫자만 가능합니다.');
						$('#idCheck').css('color', 'red');
						idPass = false;
					}
				},
				error: function(){
					alert('error');
				}
			});
		});
		
		// 2. 비밀번호 체크
		// 8 ~ 20 사이의 대문자, 소문자, 숫자, 특수문자(~!@#$%^&*)를 사용한다.
		// 각각 1개 이상씩 포함해야만 한다.
		// var regExpPw = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*])[A-Za-z0-9~!@#$%^&*]{4,20}&/;
		var regExpPw = /^[0-9]+$/;
		function pwInputCheck() {
			if($('#mPw').val() != $('#mPw2').val()){
				$('#pw2Check').text('비밀번호가 일치하지 않습니다.');
				$('#pw2Check').css('color', 'red');
				pwPass = false;
			} else{
				$('#pw2Check').text('비밀번호가 일치합니다.');
				$('#pw2Check').css('color', 'blue').css('font-weight', 'bold');
				pwPass = true;
			}
		}
		
		$('#mPw').keyup(function(){
			pwInputCheck();
			if ( regExpPw.test($('#mPw').val())){
				$('#pwCheck').text('비밀번호 사용 가능합니다.');
				$('#pwCheck').css('color', 'blue').css('font-weight', 'bold');
			} else{
				$('#pwCheck').text('숫자만 사용가능합니다.');
				$('#pwCheck').css('color', 'red');
			}
		});
		$('#mPw2').keyup(pwInputCheck);
		
		// 3. 이메일 체크
		var regExpEmail = /^[a-z][a-z0-9\-\_]+@[A-Za-z0-9]{3,}(\.[A-Za-z]{2,6}){1,2}$/;
		$('#mEmail').keyup(function(){
			$.ajax({
				url: '/HP/emailCheck.member',
				type: 'post',
				data: 'mEmail=' + $('#mEmail').val(),
				
				dataType: 'json',
				success: function(obj){
					if(regExpEmail.test($('#mEmail').val())){
						if(obj.isPossible){
							$('#emailCheck').text('사용 가능한 이메일 입니다.');
							$('#emailCheck').css('color', 'blue').css('font-weight', 'bold');
							emailPass = true;
						} else{
							$('#emailCheck').text('이미 가입된 이메일 입니다.');
							$('#emailCheck').css('color', 'red');
							emailPass = false;
						}
					} else{
						$('#emailCheck').text('올바른 이메일 형식이 아닙니다.');
						$('#emailCheck').css('color', 'red');
						emailPass = false;	
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
		
		// Phone 체크
		var regExpPhone = /^01[0|1|6|9]-[0-9]{3,4}-[0-9]{4}$/;
		$('#mPhone').blur(function(){
			$.ajax({
				url: '/HP/phoneCheck.member',
				type: 'post',
				data: 'mPhone=' + $('#mPhone').val(),
				
				dataType: 'json',
				success: function(obj){
					if( regExpPhone.test($('#mPhone').val())){
						if(obj.isPossible){
							$('#phoneCheck').text('사용 가능한 전화번호 입니다.');
							$('#phoneCheck').css('color', 'blue').css('font-weight', 'bold');
							phonePass = true;
						} else{
							$('#phoneCheck').text('사용 중인 전화번호 입니다.');
							$('#phoneCheck').css('color', 'red');
							phonePass = false;
						}
					} else{
						$('#phoneCheck').text('올바른 전화번호가 아닙니다.');
						$('#phoneCheck').css('color', 'red');
						phonePass = false;
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
		
		$('#join').click(function(){
			if( idPass && pwPass && emailPass && phonePass){
				$.ajax({
					url: '/HP/join.member',
					type: 'post',
					data: $('#f').serialize(),		// 폼의 모든 요소 보낼때 사용
					
					dataType: 'json',
					success: function(obj){
						if(obj.isSuccess){
							alert('회원이 되신걸 축하드립니다.');
							location.href='/HP/loginPage.member';
						} else{
							alert('회원가입에 실패했습니다.');
						}
					},
					error: function(){
						alert('실패');
					}
				});
			} else{
				alert('아이디/비밀번호/이메일/휴대전화 체크가 필요합니다.');
			}
		});
		
		// 입력 취소
		$('#clean').click(function(){
			// 1. 모든 입력을 지운다.
			$('input[type=text], input[type=password]').each(function(){
				// 각 요소는 each 문내부에서 $(this)로 호출한다.
				$(this).val('');
			});
			// 2. 모든 결과 메시지를 지운다.
			$('.result').each(function(){
				$(this).text('');
			});
			$('#mId').focus();
		});
		
	});
	
</script>

</head>
<body>
	
	<br/>
	<div style="text-align: center;">
	
	<div><h1>HomePage</h1></div>
	
	<form id="f" method="post">
	
	아이디<br/>
	<input id="mId" type="text" name="mId" /><br/>
	<div id="idCheck" class="result"></div><br/><br/>
	
	비밀번호<br/>
	<input id="mPw" type="password" name="mPw" /><br/>
	<div id="pwCheck" class="result"></div><br/><br/>
	
	비밀번호 재확인<br/>
	<input id="mPw2" type="password" name="mPw2" /><br/>
	<div id="pw2Check" class="result"></div><br/><br/>
	
	이름<br/>
	<input id="mName" type="text" name="mName" /><br/><br/>
	
	이메일<br/>
	<input id="mEmail" type="text" name="mEmail" /><br/>
	<div id="emailCheck" class="result"></div><br/><br/>
	
	휴대전화<br/>
	<input id="mPhone" type="text" name="mPhone" /><br/>
	<div id="phoneCheck" class="result"></div><br/><br/>
	
	주소<br/>
	<input id="mAddress" type="text" name="mAddress" /><br/><br/>
	
	<input id="join" type="button" value="가입하기" />
	<input id="clean" type="button" value="입력취소" />
	
	</form>	
	</div>
	<br/><br/><br/><br/><br/>
<%@ include file="/template/footer.jsp" %>