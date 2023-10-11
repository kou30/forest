<!--
/** 
 * Filename:shinamono_entry.jsp
 * 
 * Description: 「ShinamonoEntry.jsp」が実行され、
 * このページ(shinamono_entry.jsp)へ遷移する、
 * 品物登録フォームに入力された情報を「InsertShinamonoBL.java」へ
 * パラメーターを送る
 * 

 * 
 * Author:kuroda yukie
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
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.UserInfoDto"%>
<%
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
%>

<%-- 今日の日付を取得 --%>
<%@ page import="java.time.LocalDate" %>
<% LocalDate now = LocalDate.now(); %>

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
		<div class="container">
			<img src="./images/ENcounter.png" alt="ENcounter" class="img">
			<nav class="nav">
				<ul>
					<li><p class="name"><%=userInfoOnSession.getUserName()%>さんのページ</p></li>
					<li><a href="MainPage">TOP</a></li>
					<li><a href="MeiboEntry">名簿登録</a></li>
					<li><a href="ShowAllMeibo">名簿一覧</a></li>
					<li><a href="ShowAllShinamono">贈り物・貰い物一覧</a></li>
					<li><a href="MonthView">カレンダー</a></li>
					<li><a href="Logoutinfo" class="logout">ログアウト</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="image">
		<main>
			<h2 class="hero">贈り物・頂き物登録フォーム</h2>
			<form action="ShinamonoEntry" method="post" ID="ShinamonoEntry">
				<p>
					<input type="hidden" name="MEIBO_ID" value="<%=dto.getMeibo_id()%>"
						maxlength="10" readonly> <input type="hidden"
						name="AITENAME" value=<%=dto.getName()%>>
				<p>
					名前:<%=dto.getName()%>
				</p>
		

       			<p>
                    <label for="start">贈った、または頂いた日時:</label>
                    <input type="date" id="inputDate" name="DATE" value="<%=now%>" min="1950-01-01" max="<%= now %>" />
                </p>

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
					品目名：<input type="text" name="SHINAMONONAME" maxlength="20" id=""
						required>
				</p>
				<div id="amountField" style="display: none;">
					金額：<input type="text" name="KINGAKU" value=0 maxlength="20" id="">
				</div>

				<p>
					備考:<br>
					<textarea name="MEMO" rows="4" cols="50" maxlength="250" oninput="removeEmoji(this)"></textarea>
				</p>
				<br> <input type="submit" value="贈り物・頂き物登録"> <input
					type="reset" value="入力し直す">

			</form>

		</main>
		<footer>
			<br>
			<p>&copy; team フォレスト</p>
		</footer>
	</div>

</body>
<script src="js/script.js"></script>
<script>
document.getElementById('ShinamonoEntry').addEventListener('submit', function(event) {
    var selectedValue = document.getElementById('bunruiSelect').value;
    if (selectedValue === '0') {
        alert('分類を選択してください');
        event.preventDefault(); // フォームの送信をキャンセル
        return; // フォームの送信をキャンセルした場合、ここで処理を終了します
    }

    var categoryValue = document.getElementById('nextOptionSelect').value;
    if (categoryValue === '0') {
        alert('項目選択を選択してください');
        event.preventDefault();
        return;
    }

    var itemValue = document.getElementById('thirdOptionSelect').value;
    if (itemValue === '0') {
        alert('詳細項目選択を選択してください');
        event.preventDefault();
        return;
    }

    // 品目名のバリデーション
    var shinamonoName = document.getElementsByName('SHINAMONONAME')[0].value;
    if (shinamonoName.trim() === '') {
        alert('品目名を入力してください');
        event.preventDefault();
        return;
    }

    // 金額のバリデーション
    var kingaku = document.getElementsByName('KINGAKU')[0].value;
    if (isNaN(kingaku) || kingaku < 0) {
        alert('金額を正しい形式で入力してください');
        event.preventDefault();
        return;
    }

    // 備考のバリデーション
    var memo = document.getElementsByName('MEMO')[0].value;

    // 絵文字のバリデーション
    var emojiRegex = /[\uD800-\uDBFF][\uDC00-\uDFFF]/g;
    if (emojiRegex.test(memo) || emojiRegex.test(shinamonoName)) {
        alert('品目名と備考に絵文字を使用しないでください');
        event.preventDefault();
        return;
    }

    // 文字数のバリデーション
    if (memo.length > 100) {
        alert('備考は100文字以内で入力してください');
        event.preventDefault();
        return;
    }

    // ここで他のバリデーションルールを追加

    // バリデーションが成功した場合、フォームが送信されます
});
</script>


</html>