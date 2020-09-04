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
		
		$('#leave').click(function(){
			if($('#mId').val() == ''){
				alert('아이디를 입력하세요.');
				$('#mId').focus();
				return;
			} 
			
			if($('#mPw').val() == ''){
				alert('비밀번호를 입력하세요.');
				$('#mPw').focus();
				return;
			}
			
			if($('#mId').val() != '${loginUser.mId}'){
				alert('아이디가 틀립니다.');
				return;
			} else if($('#mPw').val() != '${loginUser.mPw}'){
				alert('비밀번호가 틀립니다.');
				return;
			}
			
			if(!confirm('탈퇴하시겠습니까?')){
				return;
			}
			
			$.ajax({
				url: '/HP/leave.member',
				dataType: 'json',
				success: function(obj){
					if(obj.isSuccess){
						alert('탈퇴되었습니다.');
						location.href="index.jsp";
					} else{
						alert('탈퇴되지 않았습니다.');
						history.back();
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
		
	});

</script>
</head>
<body>
	
<br/><br/>
	<div style="text-align: center;">
	
	<div><h1>${loginUser.mName}님의 페이지</h1></div>
	<br/>
	
	<div>탈퇴할 아이디와 비밀번호를 입력하세요</div>
	<br/>
	
	<form id="f" method="post">
		<table style="margin: auto;">
			<tr>
				<td>아이디</td>
				<td><input id="mId" type="text" name="mId" /></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input id="mPw" type="password" name="mPw" /></td>
			</tr>
			<tr>
				<td colspan="2"><input id="leave" type="button" value="회원탈퇴" /></td>
			</tr>
		</table>
	</form>
	</div>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<%@ include file="/template/footer.jsp" %>