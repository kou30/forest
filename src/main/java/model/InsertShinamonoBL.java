/** 
* Filename: InsertShinamonoBL.java
* 
* Description: 
* このクラスは、品物情報を登録するためのメソッドを定義しています。
* 引数品物DTOを取り、DAOクラスを利用してデータベースに品物データを登録し、
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

public class InsertShinamonoBL {
	public boolean executeInsertShinamono(ShinamonoDTO dto) {

		boolean succesInsert = false; //DB操作成功フラグ（true:成功/false:失敗）

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		ShinamonoDAO dao = new ShinamonoDAO();
		succesInsert = dao.doInsertShinamono(dto);

		return succesInsert;
	}
}
