<!--
/** 
 * Filename: mainpage.jsp
 * 
 * Description: 
 * logininfoでユーザーIDとパスワードを確認後「MainPage.java」が
 *実行されこのJSP(mainpage.jsp)へ遷移する
 * 
 * Author: nagai kosuke
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
<%@ page import="model.UserInfoDto"%>
<%
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/mainpage.css">
<title>TOPページ</title>
</head>
<body>
	<header>
		<p class="HeaderTagline">贈り物・頂き物・記念日・年賀状送付管理・お年玉管理・弔慶事金額を一括管理</p>
		<div class="container">
			<img src="./images/ENcounter.png" alt="ENcounter" class="img">
			<nav class="nav">
				<ul>
					<li><p class="name"><%=userInfoOnSession.getUserName()%>さんのページ
						</p></li>
					<li><a href="MainPage">TOP</a></li>
					<li><a href="MeiboEntry">名簿登録</a></li>
					<li><a href="ShowAllMeibo">名簿一覧</a></li>
					<li><a href="ShowAllShinamono">贈り物・頂き物一覧</a></li>
					<li><a href="MonthView">カレンダー</a></li>
					<li><a href="Logoutinfo" class="logout">ログアウト</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="image">
		<main>
			<h1 class=en>縁</h1>
			<h2 class="hello">
				こんにちは<%=userInfoOnSession.getUserName()%>
				さん。
			</h2>
			<nav class="topnav">
				<ul>
					<ol>
						<a href="MeiboEntry" class="topmenu">名簿登録</a>
					</ol>
					<ol>
						<a href="ShowAllMeibo" class="topmenu">名簿一覧</a>
					</ol>
					<ol>
						<a href="ShowAllShinamono" class="topmenu">贈り物・頂き物一覧</a>
					</ol>
					<ol>
						<a href="MonthView" class="topmenu">カレンダー</a>
					</ol>
				</ul>
			</nav>
		</main>

		<section class="main-content">

			<h2>大切な人との、ご縁をつづる。</h2>
			<p>贈り物・頂き物・記念日・年賀状送付管理・お年玉管理・弔慶事金額を一括管理</p>
		</section>
		<br> <br> <br> <br>


		<footer>
			<a href="Logoutinfo" "class="logout">ログアウト</a> <br>
			<p>&copy; team フォレスト</p>
		</footer>
	</div>
</body>

</html>