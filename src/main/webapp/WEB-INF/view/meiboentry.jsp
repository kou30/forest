<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head class=header>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/meiboentry.css">
<title>名簿登録</title>
</head>
<body>
	<header>
		<p class="HeaderTagline">贈り物・頂き物・記念日・年賀状送付管理・お年玉管理・弔慶事金額を一括管理</p>
		<div class="nav">
			<img src="./images/ENcounter.png" alt="ENcounter" class="img">
			<div class="menu">
				<a href="MainPage">TOP</a> <a href="MeiboEntry">名簿登録</a> <a
					href="ShowAllMeibo">名簿一覧</a> <a href="ShowAllShinamono">贈り物・貰い物一覧</a>
				<a href="MonthView7">カレンダー</a>
			</div>
			<a href="Logoutinfo" class="logout">ログアウト</a>
		</div>
	</header>
	<main>
		<h2 class="hero">名簿登録フォーム</h2>
		<form action="MeiboEntry" method="post" enctype="multipart/form-data">
			<p>
				<input type="text" name="YOMI" maxlength="10" id="yomiInput"
					placeholder="よみがな" required>
			</p>

			<p>
				<input type="text" name="NAME" maxlength="10" id="nameInput"
					placeholder="氏名" required>
			</p>

			<p>
				生年月日(西暦):<input type="date" name="BIRTHDAY" maxlength="10" id="">
			</p>

			<p>
				性別:<input type="radio" name="SEX" value="1" checked>男性<input
					type="radio" name="SEX" value="2">女性
			</p>
			<p>
				分類: <input type="radio" name="BUNRUI" value="親族" checked>親族
				<input type="radio" name="BUNRUI" value="知人">知人 <input
					type="radio" name="BUNRUI" value="なし">なし
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
			<p>
				備考:<br>
				<textarea name="MEMO" rows="4" cols="50" maxlength="250"></textarea>
			</p>
			<script>
        document.addEventListener('DOMContentLoaded', function() {
            // 画像を選択した<input>要素
            const fileInput = document.getElementById('file-sample');
            
            // 画像を表示する<img>要素
            const imgPreview = document.getElementById('file-preview');
            
            if (fileInput && imgPreview) {
                fileInput.addEventListener('change', function() {
                    if (fileInput.files && fileInput.files[0]) {
                        const reader = new FileReader();
                        
                        reader.onload = function(e) {
                            imgPreview.src = e.target.result;
                        };
                        
                        reader.readAsDataURL(fileInput.files[0]);
                    }
                });
            }
        });
    </script>
			 <p>
        画像を選択：<input id="file-sample" type="file" name="IMAGE" accept="image/png,image/jpeg">
    </p>
    <br>
    <img id="file-preview" src="" alt="画像プレビュー">
    <style>
    #file-preview {
		max-width: 100%;
		max-height: 300px;
}
	</style>
			<br><input type="submit" value="名簿登録" onclick="return itAgg()">
			<!--itAgg()は仮-->
		</form>
	</main>
	<footer>
		<script src="js/script.js"></script>

		<p>&copy; team フォレスト</p>
	</footer>
</body>
</html>