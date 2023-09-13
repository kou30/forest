<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.io.FileOutputStream"%>

<%@ page import="model.ShinamonoDTO"%>
<%@ page import="model.MeiboDTO"%>
<%@ page import="java.io.FileOutputStream"%>

<%
ShinamonoDTO Sdto = (ShinamonoDTO) request.getAttribute("Slist");
MeiboDTO Mdto = (MeiboDTO) request.getAttribute("Mlist");
%>
<%
String webContentPath = getServletContext().getRealPath("/img");
String imageFileName = webContentPath + "/image_" + Mdto.getMeibo_id() + ".jpg";
FileOutputStream outputStream = new FileOutputStream(imageFileName);
outputStream.write(Mdto.getImageData());
outputStream.close();
%>
<%!String replaceEscapeChar(String inputText) {
		inputText = inputText.replace("&", "&amp;");
		inputText = inputText.replace("<", "&lt;");
		inputText = inputText.replace(">", "&gt;");
		inputText = inputText.replace("\"", "&quot;");
		inputText = inputText.replace("'", "&#039;");

		return inputText;
	}%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>詳細ページ</title>
</head>
<body>
	<p><div ID="file">
		<img class="image"
			src="<%=request.getContextPath()%>/img/image_<%=Mdto.getMeibo_id()%>.jpg"
			alt="Image" />
			</div></p>
	
			<p>
		<%=Sdto.getItem() %>
	</p>

</body>
</html>