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
			<form action="ExecuteEditShinamono" method="post" id="ExecuteEditShinamono">
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
						>
			</form>
		
		</main>
		<footer>
			<p>&copy; team フォレスト</p>
		</footer>
	</div>
</body>
<script src="js/script.js"></script> 
<script>
document.getElementById('ExecuteEditShinamono').addEventListener('submit', function(event) {
	console.log("あいうえお");
	sleep(5000);
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
    if (memo.trim() === '') {
        alert('備考を入力してください');
        event.preventDefault();
        return;
    }

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
<script>
var kingakuValue = <%=dto.getShinamono_kingaku()%>;

//amountField 要素を取得
var amountField = document.getElementById('amountField');

//条件に応じてスタイルを変更
if (kingakuValue !== 0) {
amountField.style.display = 'block';
} else {
amountField.style.display = 'none';
}




const categorySelect = document.getElementById('nextOptionSelect');
const itemSelect = document.getElementById('thirdOptionSelect');

// CATEGORYの値が変更されたときのイベントリスナーを追加
categorySelect.addEventListener('change', function() {
    // 選択されたCATEGORYの値を取得
    const selectedCategory = categorySelect.value;

    // ITEMの選択肢をクリア
    itemSelect.innerHTML = '';

    // 選択されたCATEGORYに応じてITEMの選択肢を追加
    
    
    	if (selectedCategory === '1') { // 品物
        const itemOptions = [
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
        itemOptions.forEach(option => {
            const optionElement = document.createElement('option');
            optionElement.value = option.value;
            optionElement.textContent = option.label;
            itemSelect.appendChild(optionElement);
        });
        amountField.style.display = 'none';
    } else if (selectedCategory === '2') { // お金
        const itemOptions = [
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
        itemOptions.forEach(option => {
            const optionElement = document.createElement('option');
            optionElement.value = option.value;
            optionElement.textContent = option.label;
            itemSelect.appendChild(optionElement);
        });
        amountField.style.display = 'block';
    } else if (selectedCategory === '3') { // 手紙など
        const itemOptions = [
            { value: '0', label: '選択してください' },
            { value: '1', label: '年賀状' },
            { value: '2', label: '招待状' },
            { value: '3', label: 'お詫び状' },
            { value: '4', label: '通知状' },
            { value: '5', label: 'その他' }
        ];
        itemOptions.forEach(option => {
            const optionElement = document.createElement('option');
            optionElement.value = option.value;
            optionElement.textContent = option.label;
            itemSelect.appendChild(optionElement);
        });
        amountField.style.display = 'none';
    }
    	
    
});
</script>
// 初期状態でプルダウンを更新
updateDropdownOptions(<%=dto.getCategory()%>);
<script>
// 条件が変更されたときにプルダウンを更新
const conditionSelect = document.getElementById('nextOptionSelect'); // 条件を選択する要素
conditionSelect.addEventListener('change', function () {
    const selectedCondition = conditionSelect.value;
    updateDropdownOptions(selectedCondition);
});

</script>

</html>