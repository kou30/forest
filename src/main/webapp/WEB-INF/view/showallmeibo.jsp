<!--
/** 
 * Filename:showallmeibo.jsp
 * 
 * Description: 「ShowAllMeibo.java」が実行され、
 *「ShowAllMeiboBL.java」が実行され、SQL「meibo」テーブルに
 *既に登録されている、ログインユーザーが登録したデータのみの
 *一覧を抽出し、このページ(showallmeibo.jsp)へ表示する

 * 

 * 
 * Author:morioka shogo
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
<%@ page import="java.util.List"%>
<%@ page import="model.MeiboDTO"%>
<%@ page import="model.ShowAllMeiboBL"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>

<%!String replaceEscapeChar(String inputText) {
		inputText = inputText.replace("&", "&amp;");
		inputText = inputText.replace("<", "&lt;");
		inputText = inputText.replace(">", "&gt;");
		inputText = inputText.replace("\"", "&quot;");
		inputText = inputText.replace("'", "&#039;");

		return inputText;
	}%>

<%
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
String userId = userInfoOnSession.getUserId();
List<MeiboDTO> MeiboDTOlist = (List<MeiboDTO>) request.getAttribute("MEIBO");
String msg = (String) request.getAttribute("msg");
%>
<%
if (msg != null) {
%>
<div class="alert alert-success" role="alert">
	<%=msg%>
</div>
<%
}
%>
<%
Date now = new Date();
%>

<%!public static int calcAge(String Birthday, Date now) throws ParseException {

		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = sdfInput.parse(Birthday);

		SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy/MM/dd");
		String formattedBirthday = sdfOutput.format(birthday);

		Calendar calendarBirth = Calendar.getInstance();
		Calendar calendarNow = Calendar.getInstance();

		calendarBirth.setTime(birthday);
		calendarNow.setTime(now);

		// （現在年ー生まれ年）で年齢の計算
		int age = calendarNow.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR);
		// 誕生月を迎えていなければ年齢-1
		if (calendarNow.get(Calendar.MONTH) < calendarBirth.get(Calendar.MONTH)) {
			age -= 1;
		} else if (calendarNow.get(Calendar.MONTH) == calendarBirth.get(Calendar.MONTH)) {
			// 誕生月は迎えているが、誕生日を迎えていなければ年齢−１
			if (calendarNow.get(Calendar.DATE) < calendarBirth.get(Calendar.DATE)) {
				age -= 1;
			}
		}

		return age;
	}%>


<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/showallmeibo.css">
<link rel="stylesheet" href="css/main.css">
<title>名簿一覧</title>
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
					<li><a href="ShowAllShinamono">贈り物・貰い物一覧</a></li>
					<li><a href="MonthView">カレンダー</a></li>
					<li><a href="Logoutinfo" class="logout">ログアウト</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="image">
	<main>
		<h2>名簿一覧</h2>
		<table border="1">
			<tr>
				<th>よみがな</th>
				<th>氏名</th>
				<th>生年月日</th>
				<th>性別</th>
				<th>分類</th>
				<th>続柄</th>
				<th>品物登録</th>
				<th>編集</th>
				<th>削除</th>
				<th>個人ページ</th>
			</tr>
			<%
			for (int i = 0; i < MeiboDTOlist.size(); i++) {
				MeiboDTO dto = MeiboDTOlist.get(i);
			%>
			<%
String[] relationships = {"選択なし", "父", "母", "兄", "姉", "弟", "妹", "義父", "義母", "義兄", "義姉", "義弟", "義妹", "義祖父", "義祖母",
		"義曽祖父", "義曾祖母", "義おじ", "義おば", "義いとこ", "義甥", "義姪", "夫", "妻", "息子", "娘","友人","同僚","取引先","隣人","顧客","先生","仕事仲間","趣味仲間","関係者"};
%>
			<%
			String[] gender = {"選択なし", "男", "女" //「選択なし」を選択することはできないが、追加する可能性を加味しこうしている。
			};
			%>

			<tr>
				<td><%=replaceEscapeChar(dto.getYomi())%></td>
				<td><%=replaceEscapeChar(dto.getName())%></td>
				<td><%=dto.getBirthday()%> <%
 String birthday = dto.getBirthday();
 if (birthday != null) {
 %><%=calcAge(dto.getBirthday(), now)%>歳<%
 }
 %></td>
				<td><%=gender[dto.getSex()]%></td>
				<td><%=dto.getBunrui()%></td>
				<td><%=relationships[dto.getRelationship() - 1]%></td>
				<td><a
					href="<%=request.getContextPath()%>/ShinamonoEntry?MEIBO_ID=<%=dto.getMeibo_id()%>">品物登録</a></td>
				<td><a
					href="<%=request.getContextPath()%>/ExecuteEditMeibo?MEIBO_ID=<%=dto.getMeibo_id()%>">編集</a></td>
				<td><a href="#" onclick="deleteEvent(<%=dto.getMeibo_id()%>)">削除</a></td>

				<td><a
					href="<%=request.getContextPath()%>/Detail?MEIBO_ID=<%=dto.getMeibo_id()%>">個人ページ</a></td>
			</tr>
			<%
			}
			%>
		</table>
		<br>
	</main>
	<footer>
		<p>&copy; team フォレスト</p>
	</footer>
	</div>
</body>
<script>
function deleteEvent(meiboId){
	var result = confirm('本当に削除してよろしいですか？');

	if( result ) {
        var url = '<%=request.getContextPath()%>/ExecuteDeleteMeibo?MEIBO_ID=' + meiboId;
        window.location.href = url;
	}
	else {

	}
}

</script>
</html>
