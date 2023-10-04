/** 
* Filename: ExecuteSelectMeiboBL.java
* 
* Description: 
* このクラスは、品物情報を取得するためのメソッドを定義しています。
* 引数品物IDを取り、DAOクラスを利用してデータベースから情報を取得し、
* DTOとして返します。
* 
* Author: nagai kosuke 
* Creation Date: 2023-10-01 
* 
* Copyright (C) 2023 Forest All rights reserved. 
* 
* 
*/

package model;

public class ExecuteSelectShinamonoBL {
	public ShinamonoDTO executeSelectShinamono(int id) {
		ShinamonoDAO dao = new ShinamonoDAO();
		ShinamonoDTO dto = (ShinamonoDTO) dao.FindOne(id);

		return dto;
	}
}
