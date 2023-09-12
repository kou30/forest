package model;

public class InsertShinamonoBL {
	public boolean executeInsertShinamono(ShinamonoDTO dto) {

		boolean succesInsert = false; //DB操作成功フラグ（true:成功/false:失敗）

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆対象のユーザーデータを登録するよう依頼
		ShinamonoDAO dao = new ShinamonoDAO();
		succesInsert = dao.doInsertShinamono(dto);

		return succesInsert;
	}
}
