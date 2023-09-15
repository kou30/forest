<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css1/css2.css">
<head>
<script src="js/javaScript1.js"></script>
<title>ログイン画面</title>
</head>
<body>
	<h1>ログイン画面</h1>
	<form action="ExecuteLogin" method="post">
		<p>
			ユーザーID：<br> <input type="text" ID="Id" name="USER_ID"
				maxlength="20"placeholder="ユーザーID" required >
		</p>
		<p>
			パスワード：<br> <input type="password" ID="Pass" name="PASSWORD"
				maxlength="20"placeholder="パスワード" required>
		</p>
		<div ID="center">
			<input type="submit" value="ログイン" onclick="return ale()"class="botton">

			
		</div>
	</form>
				<br>
			    <a href="ExecuteSignup"class="signup">サインアップする</a>
</body>
</html>