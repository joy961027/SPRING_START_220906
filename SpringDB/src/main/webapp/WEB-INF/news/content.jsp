<%@page import="com.academy.springdb.model.domain.News"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
News news = (News) request.getAttribute("news");
%>
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

input[type=text], select, textarea {
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
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script>
function edit(){
	if(confirm("수정하시겠습니까?")){
		form1.action="/news/update";
		form1.method="post";
		form1.submit();
	}
}

function delete(){
	if(confirm("삭제하시겠습니까?")){
		location.href="/news/delete?news_id=<%=news.getNews_id()	%>"
	}
}

</script>
</head>
<body>

	<h3>뉴스 상세보기</h3>

	<div class="container">
		<form name="form1">
			<input type="hidden" name="title" value="<%=news.getNews_id()%>">
			<input type="text" name="title" value="<%=news.getTitle()%>">

			<input type="text" name="writer" value="<%=news.getWriter()%>">

			<textarea name="content" style="height: 200px"><%=news.getContent()%></textarea>


			<input type="button" value="목록" onClick="location.href='/news/list;">
			<input type="button" value="수정" onClick="edit();"> 
			<input type="button" value="삭제" onClick="delete();">
		</form>
	</div>

</body>
</html>
