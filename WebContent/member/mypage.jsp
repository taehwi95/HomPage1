<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내페이지</title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- jquery Migrate -->
<script src="https://code.jquery.com/jquery-migrate-3.3.1.min.js"></script>

<script type="text/javascript">
	


	$(document).ready(function(){


		// 1. 회원정보 수정하기
		$('#updateInfo').click(function(){
			if($('#mName').val() == '${loginUser.mName}' && $('#mEmail').val() == '${loginUser.mEmail}' &&
				$('#mPhone').val() == '${loginUser.mPhone}' && 	$('#mAddress').val() == '${loginUser.mAddress}'	
			) {
				alert('변경된 정보가 없습니다.');
				return;
			} else{
			$.ajax({
				url: '/HP/updateInfo.member',
				type: 'post',
				data: $('#f').serialize(),
			
				dataType: 'json',
				success: function(obj){
					if(obj.isSuccess){
						alert('회원정보가 수정되었습니다.')
					} else{
						alert('회원정보가 수정되지 않았습니다.')
					}
					location.href='/HP/myPage.member';
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
	
	<div><h1>MyPage</h1></div>
	<br/>
	
	<form id="f" method="post">
	
		아이디<br/>
		<input id="mId" type="text" name="mId" value="${loginUser.mId}" readonly /><br/><br/>
		
		이름<br/>
		<input id="mName" type="text" name="mName" value="${loginUser.mName}" /><br/><br/>
		
		이메일<br/>
		<input id="mEmail" type="text" name="mEmail" value="${loginUser.mEmail}" /><br/><br/>
		
		전화번호<br/>
		<input id="mPhone" type="text" name="mPhone" value="${loginUser.mPhone}" /><br/><br/>
		
		주소<br/>
		<input id="mAddress" type="text" name="mAddress" value="${loginUser.mAddress}" /><br/><br/>
		
		가입일<br/>
		<input id="mRegdate" type="text" name="mRegdate" value="${loginUser.mRegdate}" /><br/><br/>
		
		<input id="updateInfo" type="button" value="회원정보 수정" />
		<input type="button" value="홈페이지" onclick="location.href='index.jsp'" />
		<input type="button" value="비밀번호 수정" onclick="location.href='/HP/updatePwPage.member'" />
		<input type="button" value="회원 탈퇴" onclick="location.href='/HP/leavePage.member'" /> 
		
	</form>
	</div>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<%@ include file="/template/footer.jsp" %>