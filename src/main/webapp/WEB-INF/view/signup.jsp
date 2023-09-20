<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>



<html>
<head>
  
  <title>サインアップ</title>
  <link rel="stylesheet" href="css/signup.css">
</head>
<body style="margin-left: 20px;">
  <h2 class="kaza">サインアップ</h2>
  <div style="width:500px;">
    <form action="ExecuteSignup" method="post" >
      <p>ID
        <input type="text" name="USER_ID"  maxlength="20"placeholder="ID" required>
     
      </p>
      <p>USER_NAME
        <input type="text" name="USER_NAME"  maxlength="20" placeholder="USER_NAME" required>
     
      </p>
      <p>PASSWORD
        <input type="text" name="PASSWORD" id="pass1" maxlength="20"placeholder="PASSWORD" required>
     
      </p>
      <p>確認のためにPASSWORDを入力
        <input type="text" name="PASSWORDcheck" id="pass2" maxlength="20"placeholder="確認用PASSWORDを入力" required>
     
      </p>
      
      
      <input type="submit" value="登録する" onclick="return inhibit();"class"=button">
    </form>
  </div>
</body>
</html>


