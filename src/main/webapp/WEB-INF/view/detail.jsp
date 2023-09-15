<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.io.FileOutputStream"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShinamonoDTO"%>
<%@ page import="model.MeiboDTO"%>
<%@ page import="java.io.FileOutputStream"%>

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
if (Mdto.getImageData() != null) {
	String webContentPath = getServletContext().getRealPath("/img");
	String imageFileName = webContentPath + "/image_" + Mdto.getMeibo_id() + ".jpg";
	FileOutputStream outputStream = new FileOutputStream(imageFileName);
	outputStream.write(Mdto.getImageData());
	outputStream.close();
}
;
%>
<!DOCTYPE html>
<html lang="ja">
<head class="header">
<meta charset="UTF-8">
<title>個人ページ</title>
<link rel="stylesheet" href="css/detail.css">
</head>
<body>
	<h1>個人ページ</h1>
	<h2>相手の詳細情報</h2>
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
			<td><%=Mdto.getBirthday()%></td>
			<td><%=Mdto.getSex() == 1 ? "男性" : "女性"%></td>
			<td><%=replaceEscapeChar(Mdto.getBunrui())%></td>
			<td><%=replaceEscapeChar(Integer.toString(Mdto.getRelationship()))%></td>
			<td><%=replaceEscapeChar(Mdto.getMemo())%></td>

		</tr>
	</table>
	<h2>相手の画像</h2>
	<div ID="file">
		<img class="image"
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
			if (SHdto.getBunrui() == 1) {
		%>

		<tr>
			<td><%=SHdto.getAite_name()%></td>
			<td><%=SHdto.getRe_time()%></td>
			<td><%=SHdto.getBunrui()%></td>
			<td><%=SHdto.getShinamono_name()%></td>
			<td><%=SHdto.getShinamono_kingaku()%></td>
			<td><%=replaceEscapeChar(SHdto.getMemo())%></td>
			<td><a
				href="<%=request.getContextPath()%>/ExecuteEditShinamono?ID=<%=SHdto.getShinamono_id()%>">編集</a></td>
			<td><a
				href="<%=request.getContextPath()%>/ExecuteDeleteShinamono?ID=<%=SHdto.getShinamono_id()%>">削除</a></td>
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
			<td><%=SHdto.getAite_name()%></td>
			<td><%=SHdto.getRe_time()%></td>
			<td><%=SHdto.getBunrui()%></td>
			<td><%=SHdto.getShinamono_name()%></td>
			<td><%=SHdto.getShinamono_kingaku()%></td>
			<td><%=replaceEscapeChar(SHdto.getMemo())%></td>
			<td><a
				href="<%=request.getContextPath()%>/ExecuteEditShinamono?ID=<%=SHdto.getShinamono_id()%>">編集</a></td>
			<td><a
				href="<%=request.getContextPath()%>/ExecuteDeleteShinamono?ID=<%=SHdto.getShinamono_id()%>">削除</a></td>
		</tr>
		<%
		}
		%>
		<%
		}
		%>
	</table>




</body>
</html>