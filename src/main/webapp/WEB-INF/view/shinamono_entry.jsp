<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.MeiboDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
MeiboDTO dto = (MeiboDTO) request.getAttribute("meibo");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/shinamonoentry.css">
<link rel="stylesheet" href="css/main.css">
<title>贈り物、貰い物登録</title>

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
	<main>
		<h2 class="hero">贈り物・頂き物登録フォーム</h2>
		<form action="ShinamonoEntry" method="post">
			<p>
				<input type="hidden" name="MEIBO_ID" value="<%=dto.getMeibo_id()%>"
					maxlength="10" readonly> <input type="hidden"
					name="AITENAME" value=<%=dto.getName()%>>
			<p>
				名前:<%=dto.getName()%>
			</p>
			<!--名簿一覧に登録した個人の氏名と続柄を表示-->
			<label for="DATE">贈った、または頂いた日時:</label> <input type="date"
				id="inputDate" name="DATE">


			<!-- 第一分類 -->
			<p>
				分類 <select name="BUNRUI" id="bunruiSelect">
					<option value="0">選択してください</option>
					<option value="1">贈り物</option>
					<option value="2">頂き物</option>
				</select>
			</p>


			<!-- 第二分類 -->
			<p>
				項目選択 <select name="CATEGORY" id="nextOptionSelect" disabled>
					<option value="0">選択してください</option>
					<option value="1">品物</option>
					<option value="2">お金</option>
					<option value="3">手紙など</option>
				</select>
			</p>


			<!-- 第三分類 -->
			<p>
				詳細項目選択 <select name="ITEM" id="thirdOptionSelect" disabled>
					<option value="0">選択してください</option>
				</select>
			</p>

			<p>
				品目名：<input type="text" name="SHINAMONONAME" maxlength="20" id="">
			</p>
			<div id="amountField" style="display: none;">
				金額：<input type="text" name="KINGAKU" value=0 maxlength="20" id="">
			</div>

			<p>
				備考:<br>
				<textarea name="MEMO" rows="4" cols="50" maxlength="250"></textarea>
			</p>
			<br> <input type="submit" value="贈り物・頂き物登録">
		</form>
		<a href="Logoutinfo">ログアウト</a>

	</main>
	<footer>
		<script src="js/script.js"></script>
		<br>
		<p>&copy; team フォレスト</p>
	</footer>

</body>
</html>