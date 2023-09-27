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
<!-- ... FullCalendarのスタイルシートとスクリプトをHTMLファイルにインクルード ... -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/5.10.1/main.min.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/5.10.1/main.min.js"></script>
</head>
<body>
	<header>
		<p class="HeaderTagline">贈り物・頂き物・記念日・年賀状送付管理・お年玉管理・弔慶事金額を一括管理</p>
		<div class="nav">
			<img src="./images/ENcounter.png" alt="ENcounter" class="img">
			<div class="menu">
				<a href="MainPage">TOP</a> <a href="MeiboEntry">名簿登録</a> <a
					href="ShowAllMeibo">名簿一覧</a> <a href="ShowAllShinamono">贈り物・貰い物一覧</a>
				<a href="MonthView7">カレンダー</a>
			</div>
			<a href="Logoutinfo" class="logout">ログアウト</a>
		</div>
	</header>
	<div class="image">
		<main>
			<h1 class=en>縁</h1>
			<h2 class="hello">
				こんにちは<%=userInfoOnSession.getUserName()%>
				さん。
			</h2>
			<nav>
				<ul>
					<ol>
						<a href="MeiboEntry" class="topmenu">名簿登録</a>
					</ol>
					<ol>
						<a href="ShowAllMeibo" class="topmenu">名簿一覧</a>
					</ol>
					<ol>
						<a href="ShowAllShinamono" class="topmenu">贈り物・貰い物一覧</a>
					</ol>
					<ol>
						<a href="MonthView7" class="topmenu">カレンダー</a>
					</ol>
				</ul>
			</nav>
		</main>

		<section class="main-content">

			<h2>大切な人との、ご縁をつづる。</h2>
			<p>贈り物・頂き物・記念日・年賀状送付管理・お年玉管理・弔慶事金額を一括管理</p>
		</section>


		<footer>
			<a href="Logoutinfo" "class="logout">ログアウト</a> <br>
			<p>&copy; team フォレスト</p>
		</footer>
</body>
</div>

</html>