<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.MeiboDTO"%>
<%@ page import="model.UserInfoDto"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.util.*, java.io.FileOutputStream"%>

<%-- 今日の日付を取得 --%>
<%@ page import="java.time.LocalDate" %>
<% LocalDate now = LocalDate.now(); %>



<%
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
MeiboDTO dto = (MeiboDTO) request.getAttribute("meibo");
%>
<%
SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
Date birthday = sdfInput.parse(dto.getBirthday());
%>
<%
if (dto.getImageData() != null) {
	String webContentPath = getServletContext().getRealPath("/img");
	String imageFileName = webContentPath + "/image_" + dto.getMeibo_id() + ".jpg";
	FileOutputStream outputStream = new FileOutputStream(imageFileName);
	outputStream.write(dto.getImageData());
	outputStream.close();
};
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/editmeibo.css">
<link rel="stylesheet" href="css/main.css">
<title>名簿編集</title>
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
			<h2 class="hero">名簿編集フォーム</h2>
			<form action="ExecuteEditMeibo" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="MEIBO_ID" value="<%=dto.getMeibo_id()%>"
					maxlength="10" readonly>
				<p>
					<input type="text" name="YOMI" value="<%=dto.getYomi()%>"
						maxlength="10" id="" placeholder="よみがな">
				</p>

				<p>
					<input type="text" name="NAME" value="<%=dto.getName()%>"
						maxlength="10" id="" placeholder="氏名">
				</p>


				
				  <p>
                    <label for="">成年月日:</label>
                    <input type="date" id="" name="BIRTHDAY" value="<%=sdfInput.format(birthday)%>"  min="1950-01-01" max="<%= now %>" />
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
				String[] relationships = {"選択なし", "父", "母", "兄", "姉", "弟", "妹", "義父", "義母", "義兄", "義姉", "義弟", "義妹", "義祖父", "義祖母",
						"義曽祖父", "義曾祖母", "義おじ", "義おば", "義いとこ", "義甥", "義姪", "夫", "妻", "息子", "娘"};
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
				<p>
					<br> 画像を選択：<input id="file-preview" type="file" name="IMAGE"
						accept="image/png,image/jpeg"
				</p>
				<br> <img id="file-preview" src="<%=request.getContextPath()%>/img/image_<%=dto.getMeibo_id()%>.jpg"
					alt="画像プレビュー">

				<script>
					document.getElementById('file-sample').addEventListener(
							'change',
							function() {
								const fileInput = this;
								const imgPreview = document
										.getElementById('file-preview');

								if (fileInput.files && fileInput.files[0]) {
									const reader = new FileReader();

									reader.onload = function(e) {
										imgPreview.src = e.target.result;
									};

									reader.readAsDataURL(fileInput.files[0]);
								}
							});
				</script>
				<br> <input type="submit" value="名簿登録" onclick="return itAgg()">
			</form>
		</main>
		<script src="js/script.js"></script>
		<footer>
			<p>&copy; team フォレスト</p>
		</footer>
	</div>
</body>
</html>