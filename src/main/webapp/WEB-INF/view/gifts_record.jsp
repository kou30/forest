<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="styles.css">
  <title>サンプル</title>
 
</head>
  <header>
    <h1>サンプルHP</h1>
    <nav>
      <ul>
        <ol><a href="#">TOP</a></ol>
        <ol><a href="#">名簿登録</a></ol>
        <ol><a href="#">名簿一覧</a></ol>  <!--8/30追加-->
        <ol><a href="#">贈り物一覧</a></ol>
        <ol><a href="#">頂き物一覧</a></ol>
      </ul>
    </nav>
  </header>
</main>
<h1 class="hero">贈り物・頂き物登録フォーム</h1>
<form action="" method="" enctype="">
  <p>氏名:<input type="text" name="NAME" maxlength="10" id=""></p>
  <p>生年月日(西暦):<input type="text" name="BIRTH" maxlength="3" id=""></p>
  <p>性別:<input type="radio" name="SEX" value="1" checked>男性<input type="radio" name="SEX" value="2">女性</p>
  <p>分類:
    <input type="radio" name="CATEGORY" value="親族" checked>親族
    <input type="radio" name="CATEGORY" value="知人">知人
    <input type="radio" name="CATEGORY" value="なし">なし
  </p>
  <p>続柄:
    <select name="ZOKUGARA">
      <option value="1">父</option>
      <option value="2">母</option>
      <option value="3">兄</option>
      <option value="4">姉</option>
      <option value="5">弟</option>
      <option value="6">妹</option>
      <option value="7">義父</option>
      <option value="8">義母</option>
      <option value="9">義兄</option>
      <option value="10">義姉</option>
      <option value="11">義弟</option>
      <option value="12">義妹</option>
      <option value="13">義祖父</option>
      <option value="14">義祖母</option>
      <option value="15">義曽祖父</option>
      <option value="16">義曾祖母</option>
      <option value="17">義おじ</option>
      <option value="18">義おば</option>
      <option value="19">義いとこ</option>
      <option value="20">義甥</option>
      <option value="21">義姪</option>
      <option value="22">夫</option>
      <option value="23">妻</option>
      <option value="24">息子</option>
      <option value="25">娘</option>
    </select>

  <p>続柄備考:<input type="text" name="ZOKUGARABIKOU" maxlength="50" id=""></p>
 
  <p><input type="radio" name="SHOHIN" value="1" checked>送りモノ<input type="radio" name="SYOHIN" value="2">頂きモノ</p>
  <p>********************品物関連*********************</p>
  
  <p>贈り物：<select name="OKURIMONOBUNRUI">
    <option value="1">選択なし</option>
    <option value="2">お中元</option>
    <option value="3">お歳暮</option>
    <option value="4">お供え</option>
    <option value="5">お祝い品</option>
    <option value="6">お見舞い品</option>
    <option value="7">贈答品</option>
    <option value="8">お土産</option>
    <option value="9">記念品</option>
    <option value="10">誕生日プレゼント</option>
    <option value="11">結婚祝い品</option>
    <option value="12">出産祝い品</option>
    <option value="13">引っ越し祝い品</option>
    <option value="14">その他</option>
  </select>
   贈り物名:<input type="text" name="ZOKUGARABIKOU" maxlength="50" id=""></p>



    <p>********************手紙関連*********************</p>
   
  <p>送り状:<select name="TEGAMI">
    <option value="1">選択なし</option>
    <option value="2">年賀状</option>
    <option value="3">招待状</option>
    <option value="4">お詫び状</option>
    <option value="5">通知状</option>
    <option value="6">その他</option>
  </select>
    送り状名:<input type="text" name="ZOKUGARABIKOU" maxlength="50" id=""></p>

    <p>******************その他事項***********************</p>
  <p>備考:<br>
    <textarea name="BIKOU" rows="4" cols="50" maxlength="250"></textarea>
  </p>

  <input type="submit" value="贈り物・頂き物登録" onclick="return itAgg()">       <!--itAgg()は仮-->
  </form>
  <br>
 
  <a href="Logoutinfo">ログアウト</a>
  <a href="">名簿一覧へ移動</a>
</form>
<script src="js/script.js"></script>

    <p>&copy; team フォレスト</p>
  </footer>

</body>
</html>
