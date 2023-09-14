<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.MeiboDTO"%>
<%@ page import="model.UserInfoDto"%>

<%
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
MeiboDTO dto = (MeiboDTO) request.getAttribute("meibo");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="styles.css">
<title>名簿編集</title>
</head>
<body>
	<header>
		<h1>名簿編集</h1>
		<nav>
			<ul>
				<ol>
					<a href="MainPage">TOP</a>
				</ol>
				<ol>
					<a href="MeiboEntry">名簿登録</a>
				</ol>
				<ol>
					<a href="ShowAllMeibo">名簿一覧</a>
				</ol>
				<ol>
					<a href="ShowAllShinamono">贈り物・貰い物一覧</a>
				</ol>
			</ul>
		</nav>
	</header>
	<main>
		<h1 class="hero">名簿編集フォーム</h1>
		<form action="ExecuteEditMeibo" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="MEIBO_ID" value="<%=dto.getMeibo_id()%>"
				maxlength="10" readonly>
			<p>
				よみがな:<input type="text" name="YOMI" value="<%=dto.getYomi()%>"
					maxlength="10" id="">
			</p>

			<p>
				氏名:<input type="text" name="NAME" value="<%=dto.getName()%>"
					maxlength="10" id="">
			</p>

			<p>
				生年月日(西暦):<input type="date" name="BIRTHDAY"
					value="<%=dto.getBirthday()%> maxlength=" 10" id="">
			</p>

			<p>
				性別:<input type="radio" name="SEX" value="1"
					<%=dto.getSex() == 1 ? "checked" : ""%>>男性 <input
					type="radio" name="SEX" value="2"
					<%=dto.getSex() == 2 ? "checked" : ""%>>女性
			</p>
			<p>
				分類: <input type="radio" name="BUNRUI" value="親族"
					<%=dto.getBunrui().equals("親族") ? "checked" : ""%>>親族 <input
					type="radio" name="BUNRUI" value="知人"
					<%=dto.getBunrui().equals("知人") ? "checked" : ""%>>知人 <input
					type="radio" name="BUNRUI" value="なし"
					<%=dto.getBunrui().equals("なし") ? "checked" : ""%>>なし
			</p>
			<p>
				続柄:
				<%
			String[] relationships = {
					"選択なし", "父", "母", "兄", "姉", "弟", "妹", "義父", "義母", "義兄",
					"義姉", "義弟", "義妹", "義祖父", "義祖母", "義曽祖父", "義曾祖母", "義おじ", "義おば",
					"義いとこ", "義甥", "義姪", "夫", "妻", "息子", "娘"
			};
			%>
				<select name="RELATIONSHIP">
					<%
					for (int i = 0; i < relationships.length; i++) {
					%>
					<option value="<%=i + 1%>"
						<%=dto.getRelationship() == (i + 1) ? "selected" : ""%>><%=relationships[i]%></option>
					<%
					}
					%>
				</select>
			<p>
				備考:<br>
				<textarea name="MEMO" rows="4" cols="50" maxlength="250"><%=dto.getMemo()%></textarea>
			</p>
			<input type="file" name="IMAGE" accept="image/png,image/jpeg"
				src="<%=dto.getImageData()%>">
			</p>
			<input type="submit" value="名簿登録" onclick="return itAgg()">
			<!--itAgg()は仮-->
		</form>
		<br> <a href="Logoutinfo">ログアウト</a>
	</main>
	<script src="js/script.js"></script>
	<footer>
		<p>&copy; team フォレスト</p>
	</footer>
</body>
</html>