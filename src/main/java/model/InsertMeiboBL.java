/** 
* Filename: InsertMeiboBL.java
* 
* Description: 
* このクラスは、名簿情報を登録するためのメソッドを定義しています。
* 引数名簿DTOを取り、DAOクラスを利用してデータベースに名簿データを登録し、
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

public class InsertMeiboBL {

	public boolean executeInsertMeibo(MeiboDTO dto) {

		boolean   succesInsert = false ;  //DB操作成功フラグ（true:成功/false:失敗）

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		MeiboDAO dao = new MeiboDAO();
		succesInsert = dao.doInsert(dto);

		return succesInsert;
	}

}



//---------------------------------------------------------------------------------

