<!--
/** 
 * Filename: editmeibo.jsp
 * 
 * Description: 
 * このJSPは、「ExecuteEditMeibo.java」からこのJSPへ遷移し、既にSQL「meibo」テーブルに
 *登録されている情報を呼び出し、再編集後「UpdateMeiboBL」へ処理が移り、
 *SQL「meibo」テーブルへ上書き登録をする
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
<%@ page import="model.MeiboDTO"%>
<%@ page import="model.UserInfoDto"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.util.*, java.io.FileOutputStream"%>

<%-- 今日の日付を取得 --%>
<%@ page import="java.time.LocalDate"%>
<%
LocalDate now = LocalDate.now();
%>



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
} ;
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
					<label for="">生年月日:</label> <input type="date" id=""
						name="BIRTHDAY" value="<%=sdfInput.format(birthday)%>"
						min="1950-01-01" max="<%=now%>" />
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
						<%=dto.getBunrui().equals("知人") ? "checked" : ""%>>知人 
						
				</p>
				<p>
					続柄:
							<%
String[] relationships = {"選択なし", "父", "母", "兄", "姉", "弟", "妹", "義父", "義母", "義兄", "義姉", "義弟", "義妹", "義祖父", "義祖母",
		"義曽祖父", "義曾祖母", "義おじ", "義おば", "義いとこ", "義甥", "義姪", "夫", "妻", "息子", "娘","友人","同僚","取引先","隣人","顧客","先生","仕事仲間","趣味仲間","関係者"};
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
				</p>
				
				<script type="text/javascript">
    const radio = document.querySelectorAll('input[name="BUNRUI"]');
    const relationshipSelect = document.querySelector('select[name="RELATIONSHIP"]');

    radio.forEach(function (radioButton) {
        radioButton.addEventListener("change", function () {
            if (radioButton.value === "知人") {
                // 「知人」が選択された場合、プルダウンの選択肢を変更
                relationshipSelect.innerHTML = ""; // 既存の選択肢をクリア

                const newOptions = [
                    { value: "1", text: "選択なし" },
                    { value: "27", text: "友人" },
                    { value: "28", text: "同僚" },
                    { value: "29", text: "取引先" },
                    { value: "30", text: "隣人" },
                    { value: "31", text: "顧客" },
                    { value: "32", text: "先生" },
                    { value: "33", text: "仕事仲間" },
                    { value: "34", text: "趣味仲間" },
                    { value: "35", text: "関係者" },
                    // 他の選択肢を追加
                ];

                newOptions.forEach(function (option) {
                    const newOption = document.createElement("option");
                    newOption.value = option.value;
                    newOption.text = option.text;
                    relationshipSelect.appendChild(newOption);
                });
            } else {
                // それ以外の場合、通常の選択肢に戻す
                relationshipSelect.innerHTML = ""; // 既存の選択肢をクリア

                const commonOptions = [
                    { value: "1", text: "選択なし" },
                    { value: "2", text: "父" },
                    { value: "3", text: "母" },
                    { value: "4", text: "兄" },
                    { value: "5", text: "姉" },
                    { value: "6", text: "弟" },
                    { value: "7", text: "妹" },
                    { value: "8", text: "義父" },
                    { value: "9", text: "義母" },
                    { value: "10", text: "義兄" },
                    { value: "11", text: "義姉" },
                    { value: "12", text: "義弟" },
                    { value: "13", text: "義妹" },
                    { value: "14", text: "義祖父" },
                    { value: "15", text: "義祖母" },
                    { value: "16", text: "義曽祖父" },
                    { value: "17", text: "義曾祖母" },
                    { value: "18", text: "義おじ" },
                    { value: "19", text: "義おば" },
                    { value: "20", text: "義いとこ" },
                    { value: "21", text: "義甥" },
                    { value: "22", text: "義姪" },
                    { value: "23", text: "夫" },
                    { value: "24", text: "妻" },
                    { value: "25", text: "息子" },
                    { value: "26", text: "娘" },
                   
                    // 他の共通の選択肢を追加
                ];

                commonOptions.forEach(function (option) {
                    const newOption = document.createElement("option");
                    newOption.value = option.value;
                    newOption.text = option.text;
                    relationshipSelect.appendChild(newOption);
                });
            }
        });
    });
</script>
				<p>
					備考:<br>
					<textarea name="MEMO" rows="4" cols="50" maxlength="250"><%=dto.getMemo()%></textarea>
				</p>
				<p>
					<br> 画像を選択：<input id="file-sample" type="file" name="IMAGE"
						accept="image/png,image/jpeg" onchange="setImage(this);" onclick="this.value = '';"
				</p>
				<br> <img id="file-preview"
					src="<%=request.getContextPath()%>/img/image_<%=dto.getMeibo_id()%>.jpg"
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