<!--
/** 
 * Filename: editshinamono.jsp
 * 
 * Description: 
 * このJSPは、「ExecuteEditShinamono.java」からこのJSPへ遷移し、既にSQL「shinamono」テーブルに
 *登録されている情報を呼び出し、再編集後「UpdateShinamonoBL」へ処理が移り、
 *SQL「shinamono」テーブルへ上書き登録をする
 * 
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
<%@ page import="model.UserInfoDto"%>
<%
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
%>

<%-- 今日の日付を取得 --%>
<%@ page import="java.time.LocalDate"%>
<%
LocalDate now = LocalDate.now();
%>

<%
ShinamonoDTO dto = (ShinamonoDTO) request.getAttribute("shinamono");
%>
<!DOCTYPE html>



<script>
function thirdItem() {
    const selectedBunrui = bunruiSelect.value;


    // 第二分類が選択されたら第三分類を有効化
   
        const selectedNextOption = nextOptionSelect.value;
        if (selectedNextOption !== '0') {
            thirdOptionSelect.disabled = false;

            if (selectedNextOption === '1') { // 第二分類が「品物」の場合、第三分類を更新
                const thirdOptions = [
                    { value: '0', label: '選択してください' },
                    { value: '1', label: 'お中元' },
                    { value: '2', label: 'お供え物' },
                    { value: '3', label: 'お祝い品' },
                    { value: '4', label: 'お見舞い品' },
                    { value: '5', label: '贈答品' },
                    { value: '6', label: 'お土産' },
                    { value: '7', label: '記念品' },
                    { value: '8', label: '誕生日プレゼント' },
                    { value: '9', label: '結婚祝い品' },
                    { value: '10', label: '出産祝い品' },
                    { value: '11', label: '引っ越し祝い品' },
                    { value: '12', label: 'その他' }
                ];
				
                thirdOptionSelect.innerHTML = '';
                thirdOptions.forEach(option => {
                    const optionElement = document.createElement('option');
                    optionElement.value = option.value;
                    optionElement.textContent = option.label;
                    thirdOptionSelect.appendChild(optionElement);
                });
                 
                amountField.style.display = 'none'; 
            } else if (selectedNextOption === '2') { // 第二分類が「お金」の場合、第三分類を更新
                const thirdOptions = [
                    { value: '0', label: '選択してください' },
                    { value: '1', label: '寄付金' },
                    { value: '2', label: '贈与金' },
                    { value: '3', label: '祝儀金' },
                    { value: '4', label: '贈答金' },
                    { value: '5', label: '報酬金' },
                    { value: '6', label: '賞金' },
                    { value: '7', label: '贈賄金' },
                    { value: '8', label: '支援金' },
                    { value: '9', label: '貢献金' },
                    { value: '10', label: 'ギフト券' },
                    { value: '11', label: '仏教関連金' },
                    { value: '12', label: 'お祝い金' },
                    { value: '13', label: 'その他' }
                ];
				
                thirdOptionSelect.innerHTML = '';
                thirdOptions.forEach(option => {
                    const optionElement = document.createElement('option');
                    optionElement.value = option.value;
                    optionElement.textContent = option.label;
                    thirdOptionSelect.appendChild(optionElement);
                });
                 
                amountField.style.display = 'block'; // 金額コメント欄を表示
            } else if (selectedNextOption === '3') { // 第二分類が「手紙など」の場合、第三分類を更新
                const thirdOptions = [
                    { value: '0', label: '選択してください' },
                    { value: '1', label: '年賀状' },
                    { value: '2', label: '招待状' },
                    { value: '3', label: 'お詫び状' },
                    { value: '4', label: '通知状' },
                    { value: '5', label: 'その他' }
                ];
				
                thirdOptionSelect.innerHTML = '';
                thirdOptions.forEach(option => {
                    const optionElement = document.createElement('option');
                    optionElement.value = option.value;
                    optionElement.textContent = option.label;
                    thirdOptionSelect.appendChild(optionElement);
                });
                
                amountField.style.display = 'none'; // 第二セレクト変更時に金額コメント欄を非表示に
            } else { // その他の場合はデフォルトの選択肢を表示
                thirdOptionSelect.innerHTML = '<option value="0">選択してください</option>';
                amountField.style.display = 'none'; // 第二セレクト変更時に金額コメント欄を非表示に
            }
        } else {
            thirdOptionSelect.disabled = true;
            thirdOptionSelect.value = '0';
            amountField.style.display = 'none'; // 第二セレクト変更時に金額コメント欄を非表示に
        }
    
};


</script>


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
			<h2 class="hero">贈り物・頂き物編集フォーム</h2>
			<form action="ExecuteEditShinamono" method="post" enctype="">
				<input type="hidden" name="SHINAMONO_ID"
					value="<%=dto.getShinamono_id()%>" maxlength="10" readonly>
				<input type="hidden" name="AITE_NAME" value=<%=dto.getAite_name()%>>

				<p>
					名前:<%=dto.getAite_name()%>
				</p>

				<p>
					<label for="start">贈った、または頂いた日時:</label> <input type="date"
						id="inputDate" name="DATE" value="<%=dto.getRe_time()%>"
						min="1950-01-01" max="<%=now%>" />
				</p>


				<!-- 第一分類 -->
				<p>
					分類 <select name="BUNRUI" id="bunruiSelect">
						<option value="0" <%=dto.getBunrui() == 0 ? "selected" : ""%>>選択してください</option>
						<option value="1" <%=dto.getBunrui() == 1 ? "selected" : ""%>>贈り物</option>
						<option value="2" <%=dto.getBunrui() == 2 ? "selected" : ""%>>頂き物</option>
					</select>
				</p>


				<!-- 第二分類 -->
				<p>
					項目選択 <select name="CATEGORY" id="nextOptionSelect" >
						<option value="0" <%=dto.getCategory() == 0 ? "selected" : ""%>>選択してください</option>
						<option value="1" <%=dto.getCategory() == 1 ? "selected" : ""%>>品物</option>
						<option value="2" <%=dto.getCategory() == 2 ? "selected" : ""%>>お金</option>
						<option value="3" <%=dto.getCategory() == 3 ? "selected" : ""%>>手紙など</option>
					</select>
				</p>


				<!-- 第三分類 -->
				<p>
					詳細項目選択 <select name="ITEM" id="thirdOptionSelect">
						<option value="0">選択してください</option>
					</select>
				</p>


				<script >thirdItem()</script>

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
			<script src="js/editthing.js"></script>
		</main>
		<footer>
			<p>&copy; team フォレスト</p>
		</footer>
	</div>
</body>
</html>