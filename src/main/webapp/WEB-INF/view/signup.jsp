<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>サインアップ</title>
  <link rel="stylesheet" href="css/signup.css">
</head>
<body style="margin-left: 20px;">
  <h2 class="kaza">サインアップ</h2>
  <div style="width:500px;">
    <form action="ExecuteSignup" method="post" onsubmit="return validatePassword()">
      <p>ID
        <input type="text" name="USER_ID" maxlength="20" placeholder="ID" required>
      </p>
      <p>USER_NAME
        <input type="text" name="USER_NAME" maxlength="20" placeholder="USER_NAME" required>
      </p>
      <p>PASSWORD
        <input type="password" name="PASSWORD" id="pass1" maxlength="20" placeholder="PASSWORD" required>
      </p>
      <p>確認のためにPASSWORDを入力
        <input type="password" name="PASSWORDcheck" id="pass2" maxlength="20" placeholder="確認用PASSWORDを入力" required>
        <span id="passwordError" style="color: red;"></span>
      </p>
      <input type="submit" value="登録する" class="button">
    </form>
  </div>
  <button onclick="history.back()">戻る</button>

  <script>
    function validatePassword() {
      var pass1 = document.getElementById("pass1").value;
      var pass2 = document.getElementById("pass2").value;
      var passwordError = document.getElementById("passwordError");

      if (pass1 !== pass2) {
        passwordError.innerHTML = "確認用パスワードが異なります";
        return false; // フォーム送信を中止
      } else {
        passwordError.innerHTML = ""; // エラーメッセージをクリア
        return true; // フォーム送信を許可
      }
    }
  </script>
</body>
</html>