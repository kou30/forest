<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="styles.css">
  <title>サンプル</title>
</head>
<body>
  <header>
    <h1>サンプルHP</h1>
    <nav>
      <ul>
        <ol><a href="Mainsub">TOP</a></ol>
        <ol><a href="ShowAllMeibo">名簿一覧</a></ol>
        <ol><a href="ShowAllShinamono">贈り物・貰い物一覧</a></ol>
      </ul>
    </nav>
  </header>
  <main>
    <h1 class="hero">名簿登録フォーム</h1>
    <form action="SaveMeibo" method="post" enctype="multipart/form-data">
      <p>よみがな:<input type="text" name="YOMI" maxlength="10" id=""></p>
      
      <p>氏名:<input type="text" name="NAME" maxlength="10" id=""></p>
      
      <p>生年月日(西暦):<input type="date" name="BIRTHDAY" maxlength="10" id=""></p>
      
      <p>性別:<input type="radio" name="SEX" value="1" checked>男性<input type="radio" name="SEX" value="2">女性</p>
      <p>分類:
    <input type="radio" name="BUNRUI" value="親族" checked>親族
    <input type="radio" name="BUNRUI" value="知人">知人
    <input type="radio" name="BUNRUI" value="なし">なし
  </p>
  <p>続柄:
    <select name="RELATIONSHIP">
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
</p></p>
    

  
 
  <p>備考:<br>
    <textarea name="MEMO" rows="4" cols="50" maxlength="250"></textarea>
  </p>
  <input type="file" name="IMAGE" accept="image/png,image/jpeg"></p>
  <input type="submit" value="名簿登録" onclick="return itAgg()">       <!--itAgg()は仮-->
    </form>
    <br>
    <a href="Logoutinfo">ログアウト</a>
    <a href="ShowAllMeibo">名簿一覧へ移動</a>
  </main>
  <script src="js/script.js"></script>
  <footer>
    <p>&copy; team フォレスト</p>
  </footer>
</body>
</html>