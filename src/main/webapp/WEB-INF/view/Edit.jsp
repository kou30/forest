<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShinamonoDTO"%>
<%
ShinamonoDTO dto = (ShinamonoDTO) request.getAttribute("shinamono");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="styles.css">
<title>贈り物、貰い物編集</title>
</head>
<header>
	<h1>贈り物、貰い物編集</h1>
	<a href="Mainsub">TOP</a>
</header>
<main>
<h1 class="hero">贈り物・頂き物編集フォーム</h1>
<form action="ExecuteEdit" method="post" enctype="">
	<p>
		名前(仮):<input name="AITENAME" value="<%=dto.getAite_name()%>"
			maxlength="10">
	</p>
		<label for="DATE">贈った、または頂いた日時:</label>
		<input type="date"
			id="inputDate" name="DATE" value="<%=dto.getRe_time()%>">


	<!-- 第一分類 -->
	<p>
		分類 <select name="BUNRUI" id="bunruiSelect" >
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
		詳細項目選択 <select name="ITEM" id="thirdOptionSelect" disabled >
			<option value="0">選択してください</option>
		</select>
	</p>


	<!-- JavaScript部分 -->
	<script>
  const bunruiSelect = document.getElementById('bunruiSelect');
  const nextOptionSelect = document.getElementById('nextOptionSelect');
  const thirdOptionSelect = document.getElementById('thirdOptionSelect');
  
  bunruiSelect.addEventListener('change', function() {
    const selectedBunrui = bunruiSelect.value;
  
    // 第一分類が選択されたら第二分類を有効化
    nextOptionSelect.disabled = false;
    nextOptionSelect.value = '0';
    thirdOptionSelect.disabled = true;
    thirdOptionSelect.value = '0';
  
    // 第二分類が選択されたら第三分類を有効化
    nextOptionSelect.addEventListener('change', function() {
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
            amountField.style.display = 'block'; // 金額コメント欄を表示
          });
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
  });
});


</script>


	品目名：<input type="text" name="SHINAMONONAME" value="<%=dto.getShinamono_name()%>" maxlength="20" id="">
	</p>
	<div id="amountField" style="display: none;">
		金額：<input type="text" name="KINGAKU" value="<%=dto.getShinamono_kingaku()%>" maxlength="20" id="">
	</div>






	<p>
		備考:<br>
		<textarea name="MEMO" value="<%=dto.getMemo()%>"rows="4" cols="50" maxlength="250"></textarea>
	<input type="submit" value="贈り物・頂き物登録" onclick="return itAgg()">
</form>
<br>

<a href="">名簿一覧へ移動</a>
<br>
<a href="ShowAllShinamono">贈り物・頂き物全件一覧</a>
<br>
<a href="">ログアウト</a>
</form>
<script src="js/script.js"></script>

<p>&copy; team フォレスト</p>
</footer>
</body>
</html>