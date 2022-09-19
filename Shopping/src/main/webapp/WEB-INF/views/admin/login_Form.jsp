<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], input[type=password], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	width: 30%;
	margin: auto;
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function(){
	$($("input[type='button']")[0]).click(function(){
		//로그인 post요청 (body 실어서 옮겨야, 노출되지 않음.)
		$.ajax({
			url:"/rest/admin/login",
			type:"post",
			data:{
				user_id:$("input[name='user_id']").val(),
				pass:$("input[name='pass']").val()
			},
			success:function(result, status, xhr){
				console.log(result);
				if(result=="1"){
					//관리자 모드 메인 페이진로 들어갈 수 있게 해주기
					location.href="/admin/main";
				}else{
					alert("로그인 정보가 올바르지 않습니다.")
				}
				
			}
		});
	});
	
});
</script>

</head>
<body>


	<div class="container">
		<h3>Admin Login</h3>
		<form>
			<input type="text" name="user_id" placeholder="Your id"> 
			<input type="password" name="pass" placeholder="Your password">
			<input type="button" value="관리자 로그인" >
			<input type="button" value="관리자 등록" onClick="location.href='/admin/registForm'">
		</form>
	</div>

</body>
</html>
