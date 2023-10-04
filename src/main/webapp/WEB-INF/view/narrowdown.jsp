<!--
/** 
 * Filename: narrowdown.jsp
 * 
 * Description: 「showallshinamono.jsp」左上検索画面の絞り込むをクリックすると
 *「NarrowDown.java」が実行され、商品一覧をさらに検索条件で絞り込んだ結果が
 * 遷移後のこのページ(narrowdown.jsp)に表示される

 * 
 * Author: morioka shogo
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
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShinamonoDTO"%>


<%!String replaceEscapeChar(String inputText) {
		inputText = inputText.replace("&", "&amp");
		inputText = inputText.replace("<", "&lt");
		inputText = inputText.replace(">", "&gt");
		inputText = inputText.replace("'", "&#039");
		inputText = inputText.replace("\"", "&quot");
		return inputText;
	}%>
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
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/showallmeibo.css">
<link rel="stylesheet" href="css/main.css">
<title>贈ったモノ・頂いたモノ全件一覧</title>
</head>
<body>

<div class="search-area">
<script src="js/search.js"></script>
    <!-- <label>年を入力</label> -->
    <a href="ShowAllShinamono">全表示</a>
</div>

	<header>
		<p class="HeaderTagline">贈り物・頂き物・記念日・年賀状送付管理・お年玉管理・弔慶事金額を一括管理</p>
		<div class="nav">
			<img src="./images/ENcounter.png" alt="ENcounter" class="img">
			<div class="menu">
				<a href="MainPage">TOP</a> <a href="MeiboEntry">名簿登録</a> <a
					href="ShowAllMeibo">名簿一覧</a> <a href="ShowAllShinamono">贈り物・貰い物一覧</a>
				<a href="MonthView7">カレンダー</a>
				<a href="Logoutinfo" class="logout">ログアウト</a>
			</div>
			
		</div>
	</header>
	<main>
		<h2>贈ったモノ</h2>
		<%
		/* List<ShinamonoDTO> list = (List<ShinamonoDTO>) request.getAttribute("list"); */
		List<ShinamonoDTO> yearlist = (List<ShinamonoDTO>) request.getAttribute("yearlist");
		%>


		<table border="1" class="my-table">
			<tr>
				<th>相手の名前</th>
				<th>品物送受日</th>
				<th>詳細項目1</th>
				<th>詳細項目2</th>
				<th>品目名</th>
				<th>金額(項目：お金のみ)</th>
				<th>備考</th>
				<th>編集</th>
				<th>削除</th>
			</tr>
			<%
			for (int i = 0; i < yearlist.size(); i++) {
				ShinamonoDTO dto = yearlist.get(i);
				if (dto.getBunrui() == 1) {
			%>

			<tr>
				<td><%=dto.getAite_name()%></td>
				<td><%=dto.getRe_time()%></td>

				<%
				String a = "0";
				switch (dto.getCategory()) {
				case 0:
					a = "未選択";
					break;
				case 1:
					a = "品物";
					break;
				case 2:
					a = "お金";
					break;
				case 3:
					a = "手紙など";
					break;
				}
				%>
				<td><%=a%></td>
				<%
				if (dto.getCategory() == 0) {
					a = "未選択";
				}
				if (dto.getCategory() == 1) {
					switch (dto.getItem()) {
					case 0:
						a = "未選択";
						break;
					case 1:
						a = "お中元";
						break;
					case 2:
						a = "お供え物";
						break;
					case 3:
						a = "お祝いもの";
						break;
					case 4:
						a = "お見舞い品";
						break;
					case 5:
						a = "贈答品";
						break;
					case 6:
						a = "お土産";
						break;
					case 7:
						a = "記念品";
						break;
					case 8:
						a = "誕生日プレゼント";
						break;
					case 9:
						a = "結婚祝い品";
						break;
					case 10:
						a = "出産祝い品";
						break;
					case 11:
						a = "引っ越し祝い品";
						break;
					case 12:
						a = "その他";
						break;
					}
				}
				if (dto.getCategory() == 2) {
					switch (dto.getItem()) {
					case 0:
						a = "未選択";
						break;
					case 1:
						a = "寄付金";
						break;
					case 2:
						a = "贈与金";
						break;
					case 3:
						a = "祝儀金";
						break;
					case 4:
						a = "贈答金";
						break;
					case 5:
						a = "報酬金";
						break;
					case 6:
						a = "賞金";
						break;
					case 7:
						a = "贈賄金";
						break;
					case 8:
						a = "支援金";
						break;
					case 9:
						a = "貢献金";
						break;
					case 10:
						a = "ギフト券";
						break;
					case 11:
						a = "仏教関連金";
						break;
					case 12:
						a = "お祝い金";
						break;
					case 13:
						a = "その他";
						break;
					}
				}
				if (dto.getCategory() == 3) {
					switch (dto.getItem()) {
					case 0:
						a = "未選択";
						break;
					case 1:
						a = "年賀状";
						break;
					case 2:
						a = "招待状";
						break;
					case 3:
						a = "お詫び状";
						break;
					case 4:
						a = "通知状";
						break;
					case 5:
						a = "その他";
						break;
					}
				}
				%>
				<td><%=a%></td>
				<td><%=dto.getShinamono_name()%></td>
				<td><%=dto.getShinamono_kingaku()%></td>
				<td><%=dto.getMemo()%></td>
				<td><a
					href="<%=request.getContextPath()%>/ExecuteEditShinamono?ID=<%=dto.getShinamono_id()%>">編集</a></td>

				<td><a
					href="#" onclick="deleteEvent(<%=dto.getShinamono_id()%>)">削除</a></td>
			</tr>
			<%
			}
			%>
			<%
			}
			%>
		</table>
		<h2>頂いたモノ</h2>

		<table border="1" class="my-table">
			<tr>
				<th>相手の名前</th>
				<th>品物送受日</th>
				<th>詳細項目1</th>
				<th>詳細項目2</th>
				<th>品目名</th>
				<th>金額(項目：お金のみ)</th>
				<th>備考</th>
				<th>編集</th>
				<th>削除</th>
			</tr>
			<%
			for (int i = 0; i < yearlist.size(); i++) {
				ShinamonoDTO dto = yearlist.get(i);
				if (dto.getBunrui() == 2) {
			%>

			<tr>
				<td><%=dto.getAite_name()%></td>
				<td><%=dto.getRe_time()%></td>

				<%
				String a = "0";
				switch (dto.getCategory()) {
				case 0:
					a = "未選択";
					break;
				case 1:
					a = "品物";
					break;
				case 2:
					a = "お金";
					break;
				case 3:
					a = "手紙など";
					break;
				}
				%>
				<td><%=a%></td>
				<%
				if (dto.getCategory() == 0) {
					a = "未選択";
				}
				if (dto.getCategory() == 1) {
					switch (dto.getItem()) {
					case 0:
						a = "未選択";
						break;
					case 1:
						a = "お中元";
						break;
					case 2:
						a = "お供え物";
						break;
					case 3:
						a = "お祝いもの";
						break;
					case 4:
						a = "お見舞い品";
						break;
					case 5:
						a = "贈答品";
						break;
					case 6:
						a = "お土産";
						break;
					case 7:
						a = "記念品";
						break;
					case 8:
						a = "誕生日プレゼント";
						break;
					case 9:
						a = "結婚祝い品";
						break;
					case 10:
						a = "出産祝い品";
						break;
					case 11:
						a = "引っ越し祝い品";
						break;
					case 12:
						a = "その他";
						break;
					}
				}
				if (dto.getCategory() == 2) {
					switch (dto.getItem()) {
					case 0:
						a = "未選択";
						break;
					case 1:
						a = "寄付金";
						break;
					case 2:
						a = "贈与金";
						break;
					case 3:
						a = "祝儀金";
						break;
					case 4:
						a = "贈答金";
						break;
					case 5:
						a = "報酬金";
						break;
					case 6:
						a = "賞金";
						break;
					case 7:
						a = "贈賄金";
						break;
					case 8:
						a = "支援金";
						break;
					case 9:
						a = "貢献金";
						break;
					case 10:
						a = "ギフト券";
						break;
					case 11:
						a = "仏教関連金";
						break;
					case 12:
						a = "お祝い金";
						break;
					case 13:
						a = "その他";
						break;
					}
				}
				if (dto.getCategory() == 3) {
					switch (dto.getItem()) {
					case 0:
						a = "未選択";
						break;
					case 1:
						a = "年賀状";
						break;
					case 2:
						a = "招待状";
						break;
					case 3:
						a = "お詫び状";
						break;
					case 4:
						a = "通知状";
						break;
					case 5:
						a = "その他";
						break;
					}
				}
				%>
				<td><%=a%></td>
				<td><%=dto.getShinamono_name()%></td>
				<td><%=dto.getShinamono_kingaku()%></td>
				<td><%=dto.getMemo()%></td>
				<td><a
					href="<%=request.getContextPath()%>/ExecuteEditShinamono?ID=<%=dto.getShinamono_id()%>">編集</a></td>

				<td><a
					href="#" onclick="deleteEvent(<%=dto.getShinamono_id()%>)">削除</a></td>
			</tr>
			<%
			}
			}
			%>
		</table>
	</main>


	<br>
		
	<script src="js/script.js"></script>
	<footer>
		<p>&copy; team フォレスト</p>
	</footer>

</body>

<script>
function deleteEvent(shinamonoId){
	var result = confirm('本当に削除してよろしいですか？');

	if( result ) {
        var url = '<%=request.getContextPath()%>/ExecuteDeleteShinamono?ID=' + shinamonoId + '&pageID=0&MEIBO_ID=0';
        window.location.href = url;
	}
	else {

	}
}

</script>
</html>