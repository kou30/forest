package model;

public class UpdateShinamonoBL {

	public boolean executeUpdateShinamono(ShinamonoDTO dto) {
		boolean succesUpdate = false; //DB操作成功フラグ（true:成功/false:失敗）

		ShinamonoDAO dao = new ShinamonoDAO();
		succesUpdate = dao.doUpdate(dto);

		return succesUpdate;
	}
}
