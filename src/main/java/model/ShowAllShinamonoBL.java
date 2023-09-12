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