/**
 * Filename: NarrowDownBL.java
 *
 * Description:
 * このクラスは、NarrowDown.javaに送られた情報をShinamonoDAOに送り
 * ShinamonoDAOで抽出した情報をNarrowDown.javaに返す機能を提供するためのものです。
 *
 * Author: morioka shougo 
 * Creation Date: 2023-10-4
 * 
 * Copyright (C) 2023 KEG forest All rights reserved.
 *
 *
 */
package model;

import java.util.List;

public class NarrowDownBL {
	public List<ShinamonoDTO> NarrowDownShinamono(String SelectedOption,String write,int id) {
			ShinamonoDAO dao = new ShinamonoDAO();
			List<ShinamonoDTO> dto= dao.NarrowDownSelect(SelectedOption,write,id);
			
			
			return dto;

	}
	
	
}
