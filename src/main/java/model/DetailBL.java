/** 
* Filename: DetailBL.java
* 
* Description: 
* このクラスは、品物の詳細情報と名簿情報を取得するためのメソッドを定義しています。
* 引数品物ID、名簿IDを取りDAOクラスを利用してデータベースから情報を取得し、
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

import java.util.List;

public class DetailBL {
	public List<ShinamonoDTO> DetailShinamono(int id) {

		ShinamonoDAO dao = new ShinamonoDAO();
		List<ShinamonoDTO> dto= dao.DetailFindOne(id);
		
		
		return dto;
	}

	public MeiboDTO DetailMeiboSelect(int id) {
		MeiboDAO dao = new MeiboDAO();
		MeiboDTO dto = dao.FindOne(id);
		return dto;
	}
 
}
