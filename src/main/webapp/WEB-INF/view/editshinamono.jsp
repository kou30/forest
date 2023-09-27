<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShinamonoDTO"%>
<%
ShinamonoDTO dto = (ShinamonoDTO) request.getAttribute("shinamono");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/editshinamono.css">
<link rel="stylesheet" href="css/main.css">
<title>贈り物、貰い物編集</title>
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
			<h2 class="hero">贈り物・頂き物編集フォーム</h2>
			<form action="ExecuteEditShinamono" method="post" enctype="">
				<input type="hidden" name="SHINAMONO_ID"
					value="<%=dto.getShinamono_id()%>" maxlength="10" readonly>
				<input type="hidden" name="AITE_NAME" value=<%=dto.getAite_name()%>>

				<p>
					名前:<%=dto.getAite_name()%>
				</p>
				<label for="DATE">贈った、または頂いた日時:</label> <input type="date"
					id="inputDate" value="<%=dto.getRe_time()%>" name="DATE">


				<!-- 第一分類 -->
				<p>
					分類 <select name="BUNRUI" id="bunruiSelect">
						<option value="0" <%=dto.getBunrui() == 0 ? "checked" : ""%>>選択してください</option>
						<option value="1" <%=dto.getBunrui() == 1 ? "checked" : ""%>>贈り物</option>
						<option value="2" <%=dto.getBunrui() == 2 ? "checked" : ""%>>頂き物</option>
					</select>
				</p>


				<!-- 第二分類 -->
				<p>
					項目選択 <select name="CATEGORY" id="nextOptionSelect" disabled>
						<option value="0" <%=dto.getCategory() == 0 ? "checked" : ""%>>選択してください</option>
						<option value="1" <%=dto.getCategory() == 1 ? "checked" : ""%>>品物</option>
						<option value="2" <%=dto.getCategory() == 2 ? "checked" : ""%>>お金</option>
						<option value="3" <%=dto.getCategory() == 3 ? "checked" : ""%>>手紙など</option>
					</select>
				</p>


				<!-- 第三分類 -->
				<p>
					詳細項目選択 <select name="ITEM" id="thirdOptionSelect" disabled>
						<option value="0">選択してください</option>
					</select>
				</p>


				品目名：<input type="text" name="SHINAMONONAME"
					value="<%=dto.getShinamono_name()%>" maxlength="20" id="">
				</p>
				<div id="amountField" style="display: none;">
					金額：<input type="text" name="KINGAKU"
						value="<%=dto.getShinamono_kingaku()%>" maxlength="20" id="">
				</div>






				<p>
					備考:<br>
					<textarea name="MEMO" rows="4" cols="50" maxlength="250"><%=dto.getMemo()%></textarea>
					<br> <input type="submit" value="贈り物・頂き物登録"
						onclick="return itAgg()">
			</form>
			<script src="js/script.js"></script>
		</main>
		<footer>
			<p>&copy; team フォレスト</p>
		</footer>
	</div>
</body>
</html>