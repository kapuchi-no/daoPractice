<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Users, java.util.Objects" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<style>
		body {
			padding: 0px 20px;
		}
		
		.result-label {
			margin-bottom: 5px;
		}
	</style>
</head>

<body>
	<h1>ユーザー検索</h1>
	
	<form action="connection" method="post">
		<div>
			<Label for="id">検索したいユーザIDを入力してください</Label></br>
			<input id="id" type="number" name="id" required></input>
		</div>
		<input type="submit" value="検索"></input>
	</form>
	
	<!-- 出力 -->
	<%
		Users user = (Users)request.getAttribute("user");
		Boolean isDisplayResult = Objects.nonNull(user);
	%>
	
	<% if (isDisplayResult){ %>
		<h2 class="result-label">検索結果</h2>
		<div>
			ID: <%= user.getId() %></br>
			名前： <%= user.getName() %></br>
			e-mail: <%= user.getEmail() %></br>
			パスワード: <%= user.getPassword() %></br>
		</div>
	<% } %>
</body>
</html>