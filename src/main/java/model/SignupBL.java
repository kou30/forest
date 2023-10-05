/** 
* Filename: SignupBL.java
* 
* Description: 
* このクラスは、ユーザーアカウントを新規登録するためのメソッドを定義しています。
* 引数ユーザDTOを取り、DAOクラスを利用してデータベースへ情報を登録し、
* DB操作フラグ（true/false)を返します。
* 
* Author: nagai kosuke 
* Creation Date: 2023-10-01 
* 
* Copyright (C) 2023 Forest All rights reserved. 
* 
* 
*/

package model;

import javax.naming.NamingException;

public class SignupBL {
	public boolean executeSignup(UserInfoDto dto) throws NamingException {

		boolean   succesInsert = false ; 

		UserInfoDao dao = new UserInfoDao();
		succesInsert = dao.doSignup(dto);

		return succesInsert;
	}
}



