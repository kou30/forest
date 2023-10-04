/** 
* Filename: UserInfoDto.java
* 
* Description: 
* このクラスは、ユーザー情報の各フィールドを保持し、
* セッターゲッターを提供します。
* ユーザー情報には、ユーザー番号、ユーザーID、
* ユーザー名、ユーザーパスワードが含まれています。
* 
* Author: nagai kosuke 
* Creation Date: 2023-10-04 
* 
* Copyright (C) 2023 Forest All rights reserved. 
* 
* 
*/

package model;


public class UserInfoDto {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private int user_nr;		//ユーザー番号
	private String userId;         //ユーザーID
	private String userName;       //ユーザー名
	private String passWord;       //ユーザーパスワード


	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：userId）
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	//getter/setter（対象フィールド：userName）
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }

	//getter/setter（対象フィールド：passWord）
	public String getPassWord() { return passWord; }
	public void setPassWord(String passWord) { this.passWord = passWord; }
	public int getUser_nr() {
		return user_nr;
	}
	public void setUser_nr(int user_nr) {
		this.user_nr = user_nr;
	}

}
