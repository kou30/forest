/** 
* Filename: UpdateMeiboBL.java
* 
* Description: 
* このクラスは、品物情報を編集するためのメソッドを定義しています。
* 引数品物DTOを取り、DAOクラスを利用してデータベースに編集した品物データを登録し、
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

public class UpdateShinamonoBL {

	public boolean executeUpdateShinamono(ShinamonoDTO dto) {
		boolean succesUpdate = false; //DB操作成功フラグ（true:成功/false:失敗）

		ShinamonoDAO dao = new ShinamonoDAO();
		succesUpdate = dao.doUpdate(dto);

		return succesUpdate;
	}
}
