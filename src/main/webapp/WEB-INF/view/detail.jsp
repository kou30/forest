<!--
/** 
 * Filename: detail.jsp
 * 
 * Description: 
 * このJSPは、既にSQL「shinamono」「meibo」テーブルに登録されている情報を呼び出し
 *	個人ページに表示する
 * 
 * 
 * Author:　morioka shogo
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
<%@ page import="java.util.*, java.io.FileOutputStream"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShinamonoDTO"%>
<%@ page import="model.MeiboDTO"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="model.UserInfoDto"%>
<%
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
%>

<%
List<ShinamonoDTO> Sdto = (List<ShinamonoDTO>) request.getAttribute("Slist");
MeiboDTO Mdto = (MeiboDTO) request.getAttribute("Mlist");
%>
<%!String replaceEscapeChar(String inputText) {
		inputText = inputText.replace("&", "&amp;");
		inputText = inputText.replace("<", "&lt;");
		inputText = inputText.replace(">", "&gt;");
		inputText = inputText.replace("\"", "&quot;");
		inputText = inputText.replace("'", "&#039;");

		return inputText;
	}%>
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

<%
if (Mdto.getImageData() != null) {
	String webContentPath = getServletContext().getRealPath("/img");
	String imageFileName = webContentPath + "/image_" + Mdto.getMeibo_id() + ".jpg";
	FileOutputStream outputStream = new FileOutputStream(imageFileName);
	outputStream.write(Mdto.getImageData());
	outputStream.close();
} ;
%>
<%
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


<!DOCTYPE html>
<html lang="ja">
<head class="header">
<meta charset="UTF-8">
<title>個人ページ</title>
<link rel="stylesheet" href="css/detail.css">
<link rel="stylesheet" href="css/main.css">
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
			<h2>詳細情報</h2>
			<table border="1">
				<tr>
					<th>名前</th>
					<th>よみがな</th>
					<th>生年月日</th>
					<th>性別</th>
					<th>分類</th>
					<th>続柄</th>
					<th>備考</th>

				</tr>
				<tr>

					<td><%=replaceEscapeChar(Mdto.getName())%></td>
					<td><%=replaceEscapeChar(Mdto.getYomi())%></td>
					<td><%=Mdto.getBirthday()%> <%=calcAge(Mdto.getBirthday(), now)%>歳</td>
					<td><%=Mdto.getSex() == 1 ? "男性" : "女性"%></td>
					<td><%=replaceEscapeChar(Mdto.getBunrui())%></td>
					<td><%=replaceEscapeChar(Integer.toString(Mdto.getRelationship()))%></td>
					<td><%=replaceEscapeChar(Mdto.getMemo())%></td>

				</tr>
			</table>
			<h2>相手の画像</h2>
			<div ID="file">
				<img class="fileimage"
					src="<%=request.getContextPath()%>/img/image_<%=Mdto.getMeibo_id()%>.jpg"
					alt="Image" />
			</div>

			<h2>贈ったモノ</h2>

			<table border="1">
				<tr>
					<th>相手の名前</th>
					<th>品物送受日</th>
					<th>詳細項目</th>
					<th>品目名</th>
					<th>金額(項目：お金のみ)</th>
					<th>備考</th>
					<th>編集</th>
					<th>削除</th>
				</tr>

				<%
				for (int i = 0; i < Sdto.size(); i++) {
					ShinamonoDTO SHdto = Sdto.get(i);
				%>

				<%
				if (SHdto.getBunrui() == 1) {
				%>
				<tr>
					<td><%=replaceEscapeChar(SHdto.getAite_name())%></td>
					<td><%=SHdto.getRe_time()%></td>

					<%
					String a = "0";
					switch (SHdto.getCategory()) {
						case 0 :
							a = "未選択";
							break;
						case 1 :
							a = "品物";
							break;
						case 2 :
							a = "お金";
							break;
						case 3 :
							a = "手紙など";
							break;
					}
					%>
					<td><%=a%></td>
					<%
					if (SHdto.getCategory() == 0) {
						a = "未選択";
					}
					if (SHdto.getCategory() == 1) {
						switch (SHdto.getItem()) {
							case 0 :
						a = "未選択";
						break;
							case 1 :
						a = "お中元";
						break;
							case 2 :
						a = "お供え物";
						break;
							case 3 :
						a = "お祝いもの";
						break;
							case 4 :
						a = "お見舞い品";
						break;
							case 5 :
						a = "贈答品";
						break;
							case 6 :
						a = "お土産";
						break;
							case 7 :
						a = "記念品";
						break;
							case 8 :
						a = "誕生日プレゼント";
						break;
							case 9 :
						a = "結婚祝い品";
						break;
							case 10 :
						a = "出産祝い品";
						break;
							case 11 :
						a = "引っ越し祝い品";
						break;
							case 12 :
						a = "その他";
						break;
						}
					}
					if (SHdto.getCategory() == 2) {
						switch (SHdto.getItem()) {
							case 0 :
						a = "未選択";
						break;
							case 1 :
						a = "寄付金";
						break;
							case 2 :
						a = "贈与金";
						break;
							case 3 :
						a = "祝儀金";
						break;
							case 4 :
						a = "贈答金";
						break;
							case 5 :
						a = "報酬金";
						break;
							case 6 :
						a = "賞金";
						break;
							case 7 :
						a = "贈賄金";
						break;
							case 8 :
						a = "支援金";
						break;
							case 9 :
						a = "貢献金";
						break;
							case 10 :
						a = "ギフト券";
						break;
							case 11 :
						a = "仏教関連金";
						break;
							case 12 :
						a = "お祝い金";
						break;
							case 13 :
						a = "その他";
						break;
						}
					}
					if (SHdto.getCategory() == 3) {
						switch (SHdto.getItem()) {
							case 0 :
						a = "未選択";
						break;
							case 1 :
						a = "年賀状";
						break;
							case 2 :
						a = "招待状";
						break;
							case 3 :
						a = "お詫び状";
						break;
							case 4 :
						a = "通知状";
						break;
							case 5 :
						a = "その他";
						break;
						}
					}
					%>

					<td><%=replaceEscapeChar(SHdto.getShinamono_name())%></td>
					<td><%=SHdto.getShinamono_kingaku()%></td>
					<td><%=replaceEscapeChar(SHdto.getMemo())%></td>
					<td><a
						href="<%=request.getContextPath()%>/ExecuteEditShinamono?ID=<%=SHdto.getShinamono_id()%>">編集</a></td>
					<td><a href="#"
						onclick="deleteEvent(<%=SHdto.getShinamono_id()%>)">削除</a></td>
				</tr>
				<%
				}
				%>
				<%
				}
				%>
			</table>

			<table border="1">
				<h2>頂いたモノ</h2>
				<tr>
					<th>相手の名前</th>
					<th>品物送受日</th>
					<th>詳細項目</th>
					<th>品目名</th>
					<th>金額(項目：お金のみ)</th>
					<th>備考</th>
					<th>編集</th>
					<th>削除</th>
				</tr>
				<%
				for (int i = 0; i < Sdto.size(); i++) {
					ShinamonoDTO SHdto = Sdto.get(i);
					if (SHdto.getBunrui() == 2) {
				%>
				<tr>
					<td><%=replaceEscapeChar(SHdto.getAite_name())%></td>
					<td><%=SHdto.getRe_time()%></td>



					<%
					String a = "0";
					switch (SHdto.getCategory()) {
						case 0 :
							a = "未選択";
							break;
						case 1 :
							a = "品物";
							break;
						case 2 :
							a = "お金";
							break;
						case 3 :
							a = "手紙など";
							break;
					}
					%>
					<td><%=a%></td>
					<%
					if (SHdto.getCategory() == 0) {
						a = "未選択";
					}
					if (SHdto.getCategory() == 1) {
						switch (SHdto.getItem()) {
							case 0 :
						a = "未選択";
						break;
							case 1 :
						a = "お中元";
						break;
							case 2 :
						a = "お供え物";
						break;
							case 3 :
						a = "お祝いもの";
						break;
							case 4 :
						a = "お見舞い品";
						break;
							case 5 :
						a = "贈答品";
						break;
							case 6 :
						a = "お土産";
						break;
							case 7 :
						a = "記念品";
						break;
							case 8 :
						a = "誕生日プレゼント";
						break;
							case 9 :
						a = "結婚祝い品";
						break;
							case 10 :
						a = "出産祝い品";
						break;
							case 11 :
						a = "引っ越し祝い品";
						break;
							case 12 :
						a = "その他";
						break;
						}
					}
					if (SHdto.getCategory() == 2) {
						switch (SHdto.getItem()) {
							case 0 :
						a = "未選択";
						break;
							case 1 :
						a = "寄付金";
						break;
							case 2 :
						a = "贈与金";
						break;
							case 3 :
						a = "祝儀金";
						break;
							case 4 :
						a = "贈答金";
						break;
							case 5 :
						a = "報酬金";
						break;
							case 6 :
						a = "賞金";
						break;
							case 7 :
						a = "贈賄金";
						break;
							case 8 :
						a = "支援金";
						break;
							case 9 :
						a = "貢献金";
						break;
							case 10 :
						a = "ギフト券";
						break;
							case 11 :
						a = "仏教関連金";
						break;
							case 12 :
						a = "お祝い金";
						break;
							case 13 :
						a = "その他";
						break;
						}
					}
					if (SHdto.getCategory() == 3) {
						switch (SHdto.getItem()) {
							case 0 :
						a = "未選択";
						break;
							case 1 :
						a = "年賀状";
						break;
							case 2 :
						a = "招待状";
						break;
							case 3 :
						a = "お詫び状";
						break;
							case 4 :
						a = "通知状";
						break;
							case 5 :
						a = "その他";
						break;
						}
					}
					%>
					<td><%=replaceEscapeChar(SHdto.getShinamono_name())%></td>
					<td><%=SHdto.getShinamono_kingaku()%></td>
					<td><%=replaceEscapeChar(SHdto.getMemo())%></td>
					<td><a
						href="<%=request.getContextPath()%>/ExecuteEditShinamono?ID=<%=SHdto.getShinamono_id()%>">編集</a></td>
					<td><a href="#"
						onclick="deleteEvent(<%=SHdto.getShinamono_id()%>)">削除</a></td>
				</tr>
				<%
				}
				%>
				<%
				}
				%>
			</table>
		</main>
		<footer>
			<p>&copy; team フォレスト</p>
		</footer>
	</div>

</body>
<script>
function deleteEvent(shinamonoId){
	var result = confirm('本当に削除してよろしいですか？');

	if( result ) {
        var url = '<%=request.getContextPath()%>/ExecuteDeleteShinamono?ID=' + shinamonoId + '&pageID=2&MEIBO_ID=<%=Mdto.getMeibo_id()%>';
        window.location.href = url;
	}
	else {

	}
}

</script>
</html>