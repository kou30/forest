<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="model.UserInfoDto"      %>

<html>
<head>
  <script>
  if (document.getElementById('pass1').value != document.getElementById('pass2').value) {
   alert('パスワードを一致させてください');
   return false
  }</script>
  <title>サインアップ</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body style="margin-left: 20px;">
  <h2 class="kaza">サインアップ</h2>
  <div style="width:500px;">
    <form action="Signup" method="post" enctype="multipart/form-data">
      <p>ID<br>
        <input type="text" name="ID"  maxlength="20" >
     
      </p>
      <p>USER_NAME<br>
        <input type="text" name="USER_NAME"  maxlength="20" >
     
      </p>
      <p>PASSWORD<br>
        <input type="text" name="PASSWORD" id="pass1" maxlength="20" >
     
      </p>
      <p>確認のためにPASSWORDを入力<br>
        <input type="text" name="PASSWORDcheck" id="pass2" maxlength="20" >
     
      </p>
      
      
      <input type="submit" value="回答する(SaveSurveyを起動)" onclick="return inhibit();">
    </form>
  </div>
</body>
</html>