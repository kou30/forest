<!--
/** 
 * Filename: meiboentry.jsp
 * 
 * Description: 「MeiboEntry.java」からこのページへ遷移し、
 * 名簿登録フォームに入力された情報を「InsertMeiboBL.java」へ
 * パラメーターを送る
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
<%@ page import="model.UserInfoDto"%>
<%
UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
%>
<%@ page import="model.MeiboDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<%-- 今日の日付を取得 --%>
<%@ page import="java.time.LocalDate"%>
<%
LocalDate now = LocalDate.now();
%>

<%
MeiboDTO dto = (MeiboDTO) request.getAttribute("meibo");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/meiboentry.css">
<link rel="stylesheet" href="css/main.css">
<script>
        function displayAlert() {
            var errorMessage = '<%=request.getAttribute("errorMessage")%>';
		if (errorMessage && errorMessage !== 'null') {
			alert(errorMessage);
		}
	}
</script>
<title>名簿登録</title>
</head>
<body onload="displayAlert()">
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
			<h2 class="hero">名簿登録フォーム</h2>
			<form action="MeiboEntry" ID="MeiboEntry" method="post"
				enctype="multipart/form-data">
				<p>
					<input type="text" name="YOMI" maxlength="30" id="yomiInput"
						placeholder="よみがな" pattern="^[ぁ-ん]+$" required>
				</p>

				<p>
					<input type="text" name="NAME" maxlength="20" id="nameInput"
						placeholder="氏名" pattern="^[ぁ-ん]+$" required>
				</p>

				<p>
					生年月日: <input type="date" id=""
						name="BIRTHDAY" value="<%=now%>" min="1950-01-01" max="<%=now%>" />
					<label for="">生年月日:</label> <input type="date" id=""
						name="BIRTHDAY" value="1980-01-01" min="1950-01-01" max="<%=now%>" />
				</p>

				<p>
					性別:<input type="radio" name="SEX" value="1" checked>男性<input
						type="radio" name="SEX" value="2">女性
				</p>
				<p>
					分類: <input type="radio" name="BUNRUI" value="親族"  checked>親族
					<input type="radio" name="BUNRUI" value="知人">知人 
				</p>
				
				<p>
					続柄: <select name="RELATIONSHIP">
						<option value="1">選択なし</option>
						<option value="2">父</option>
						<option value="3">母</option>
						<option value="4">兄</option>
						<option value="5">姉</option>
						<option value="6">弟</option>
						<option value="7">妹</option>
						<option value="8">義父</option>
						<option value="9">義母</option>
						<option value="10">義兄</option>
						<option value="11">義姉</option>
						<option value="12">義弟</option>
						<option value="13">義妹</option>
						<option value="14">義祖父</option>
						<option value="15">義祖母</option>
						<option value="16">義曽祖父</option>
						<option value="17">義曾祖母</option>
						<option value="18">義おじ</option>
						<option value="19">義おば</option>
						<option value="20">義いとこ</option>
						<option value="21">義甥</option>
						<option value="22">義姪</option>
						<option value="23">夫</option>
						<option value="24">妻</option>
						<option value="25">息子</option>
						<option value="26">娘</option>
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
                    { value: "0", text: "選択なし" },
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
					<textarea name="MEMO" rows="4" cols="50" maxlength="100"></textarea>
				</p>

				<p>
					画像を選択：<input id="file-sample" type="file" name="IMAGE"
						accept="image/png,image/jpeg" required>
				</p>
				<p id="file-validation-message" style="color: red;"></p>
				<br> <img id="file-preview" src="" style="display: none;"
					alt="画像プレビュー"> <br> <input type="submit" value="名簿登録">
				<input type="reset" value="入力し直す" id="reset-button">
			</form>
		</main>
		<footer>
			<script src="js/script.js"></script>
			<p>&copy; team フォレスト</p>
		</footer>
	</div>
</body>
<script src="js/script.js"></script>
<script>
	document
			.addEventListener(
					'DOMContentLoaded',
					function() {
						const fileInput = document
								.getElementById('file-sample');
						const imgPreview = document
								.getElementById('file-preview');
						const resetButton = document
								.getElementById('reset-button');
						const meiboEntryForm = document.querySelector('form');
						const fileValidationMessage = document
								.getElementById('file-validation-message');

						if (fileInput && imgPreview && resetButton
								&& meiboEntryForm) {
							fileInput
									.addEventListener(
											'change',
											function() {
												if (fileInput.files
														&& fileInput.files[0]) {
													const reader = new FileReader();

													reader.onload = function(e) {
														imgPreview.src = e.target.result;
														imgPreview.style.display = 'block'; // 画像を表示
													};

													reader
															.readAsDataURL(fileInput.files[0]);

													// 画像サイズのバリデーション
													const fileSize = fileInput.files[0].size; // バイト単位でファイルサイズを取得
													const maxSize = 5 * 1024 * 1024; // 5MBまで許容
													if (fileSize > maxSize) {
														fileValidationMessage.textContent = '画像のサイズは5MB以下にしてください。';
														fileInput.value = ''; // ファイル選択をクリア
														imgPreview.src = ''; // 画像プレビューの初期化
														imgPreview.style.display = 'none'; // 画像を非表示
													} else {
														fileValidationMessage.textContent = '';
													}

													// 画像拡張子のバリデーション
													const allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i; // jpg, jpeg, png形式のファイルを許容
													const fileName = fileInput.files[0].name;
													if (!allowedExtensions
															.exec(fileName)) {
														fileValidationMessage.textContent = '画像はjpg, jpeg, png形式のファイルを選択してください。';
														fileInput.value = ''; // ファイル選択をクリア
														imgPreview.src = ''; // 画像プレビューの初期化
														imgPreview.style.display = 'none'; // 画像を非表示
													} else {
														fileValidationMessage.textContent = '';
													}
												} else {
													imgPreview.src = ''; // 画像プレビューの初期化
													imgPreview.style.display = 'none'; // 画像を非表示
													fileValidationMessage.textContent = '';
												}
											});

							resetButton.addEventListener('click', function(
									event) {
								// リセットボタンがクリックされたときの処理を追加
								meiboEntryForm.reset();
							});

							meiboEntryForm
									.addEventListener(
											'submit',
											function(event) {
												event.preventDefault();

												// 名前のバリデーション
												var name = document
														.getElementsByName('NAME')[0].value;
												if (name.trim() === '') {
													alert('氏名を入力してください');
													return;
												}

												// 成年月日のバリデーション
												var birthday = document
														.getElementsByName('BIRTHDAY')[0].value;
												if (birthday.trim() === '') {
													alert('生年月日を入力してください');
													return;
												}

												// 性別のバリデーション
												var sex = document
														.querySelectorAll('input[name="SEX"]:checked');
												if (sex.length === 0) {
													alert('性別を選択してください');
													return;
												}

												// 分類のバリデーション
												var bunrui = document
														.querySelectorAll('input[name="BUNRUI"]:checked');
												if (bunrui.length === 0) {
													alert('分類を選択してください');
													return;
												}

												// 続柄のバリデーション
												var relationship = document
														.getElementsByName('RELATIONSHIP')[0].value;
												if (relationship === '1') {
													alert('続柄を選択してください');
													return;
												}

												// 備考のバリデーション
												var memo = document
														.getElementsByName('MEMO')[0].value;
												if (memo.length > 100) {
													alert('備考は100文字以内で入力してください');
													return;
												}

												// 絵文字のバリデーション
												var emojiRegex = /[\uD800-\uDBFF][\uDC00-\uDFFF]/g;
												if (emojiRegex.test(memo)) {
													alert('備考に絵文字を使用しないでください');
													return;
												}

												meiboEntryForm.submit();
											});
						}
					});
</script>

</html>
