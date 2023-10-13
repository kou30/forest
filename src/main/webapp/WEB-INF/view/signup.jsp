<!--
/** 
 * Filename:signup.jsp
 * 
 * Description: 「login.jsp」の「サインアップ」リンクが押されたら、
 *「ExecuteSingnup.java」が実行され、このページ「signup.jsp」へ遷移する
 * 新規ログインユーザー情報を入力し、そのパラメーターを
 *「SignupBL.java」へ送りSQL「user_info」へ登録させる
 * 
 * Author:tanaka kotaro
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


<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<% if (request.getAttribute("isDuplicateID") != null) { %>
    <% boolean isDuplicateID = (boolean) request.getAttribute("isDuplicateID"); %>
    <!-- 以降のコードで isDuplicateID を使用 -->
<% } %>
<% boolean isDuplicateID = (boolean) request.getAttribute("isDuplicateID"); %>

<html>
<head>
  <title>新規登録</title>
  <link rel="stylesheet" href="css/signup.css">
</head>
<body style="margin-left: 20px;">
  <h2 class="kaza">サインアップ</h2>
  <div style="width:500px;">
    <form action="ExecuteSignup" method="post" onsubmit="return validatePassword()">
      <p>ID
        <input type="text" name="USER_ID" pattern="^[0-9a-zA-Z]+$" minlength="6" maxlength="20" placeholder="半角英数字のみ、6文字以上20文字以下" required>
        
      </p><span id="IDdup" style="color: red;"></span>
      <p>ユーザー名
        <input type="text" name="USER_NAME" minlength="6" maxlength="20" placeholder="6文字以上20文字以下" required>
      </p>
      <p>パスワード
        <input type="password" name="PASSWORD" id="pass1" minlength="6" maxlength="20" placeholder="6文字以上20文字以下" required>
      </p>
      <p>確認のためにパスワードを入力
        <input type="password" name="PASSWORDcheck" id="pass2" minlength="6" maxlength="20" placeholder="6文字以上20文字以下" required>
        
      </p>
      <span id="passwordError" style="color: red;"></span>
      <p></p>
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
   <% if (isDuplicateID) { %>
  <script>
  var IDdup = document.getElementById("IDdup");
  IDdup.innerHTML = "このIDは既に使用されています。別のIDを選択してください。"; 
  //alert("このIDは既に使用されています。別のIDを選択してください。");
  </script>
  <% } %>
</body>
</html>