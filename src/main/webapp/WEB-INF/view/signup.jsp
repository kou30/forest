<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>



<html>
<head>
  
  <title>サインアップ</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body style="margin-left: 20px;">
  <h2 class="kaza">サインアップ</h2>
  <div style="width:500px;">
    <form action="ExecuteSignup" method="post" >
      <p>ID<br>
        <input type="text" name="USER_ID"  maxlength="20" required>
     
      </p>
      <p>USER_NAME<br>
        <input type="text" name="USER_NAME"  maxlength="20" required>
     
      </p>
      <p>PASSWORD<br>
        <input type="text" name="PASSWORD" id="pass1" maxlength="20" required>
     
      </p>
      <p>確認のためにPASSWORDを入力<br>
        <input type="text" name="PASSWORDcheck" id="pass2" maxlength="20" required>
     
      </p>
      
      
      <input type="submit" value="登録する" onclick="return inhibit();">
    </form>
  </div>
</body>
</html>