/** 
* Filename: ShowAllShinamonoBL.java
* 
* Description: 
* このクラスは、品物情報を取得するためのメソッドを定義しています。
* 引数user_nrを取りDAOクラスを利用してデータベースから情報を取得し、
* DTOリストとして返します。
* 
* Author: nagai kosuke 
* Creation Date: 2023-10-01 
* 
* Copyright (C) 2023 Forest All rights reserved. 
* 
*/

package model;

import java.util.List;

public class ShowAllShinamonoBL {

	/**----------------------------------------------------------------------*
	 *■executeSelectShinamonoメソッド
	 *概要　：アンケートデータを全件抽出する
	 *引数　：なし
	 *戻り値：抽出結果（DTOリスト）
	 *----------------------------------------------------------------------**/
	public List<ShinamonoDTO> executeSelectShinamono(int user_nr) {

		ShinamonoDAO dao = new ShinamonoDAO();
		List<ShinamonoDTO> dtoList= dao.findAll(user_nr);
		
		return dtoList;
	}
	
}