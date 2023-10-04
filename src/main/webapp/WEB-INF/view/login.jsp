<!--
/** 
 * Filename: login.jsp
 * 
 * Description: 
 * このJSPは、「Logininfo.java」からこのJSPへ遷移し、
 *①サインアップリンクで「ExecuteSignup.java」へ遷移し、
 　新しくログイン会員登録をする
 
 *②既に会員登録している場合は、ユーザーIDとパスワードを入力し、
 * SQL「user_info」テーブルに登録しているユーザーIDとパスワードと
   一致するか確認。「SignupBL.java」へ処理が移る
 * 
 * 
 * Author: tanaka kotaro
 * Creation Date: 2023-10-4
 * 
 * Modified By: 
 * Modification Date:  
 * Reason for Modification:  
 * 
 * Copyright (C) 2023 Forest All rights reserved. 
 * 
 * 
 */
 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css/Login.css">
<head>
<script src="js/javaScript1.js"></script>
<title>ログイン画面</title>
</head>
<body>
	<h1>ログイン画面</h1>
	<form action="Logininfo" method="post">
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