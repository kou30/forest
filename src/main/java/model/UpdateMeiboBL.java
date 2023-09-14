package model;

public class UpdateMeiboBL {

	public boolean executeUpdateMeibo(MeiboDTO dto) {
		boolean succesUpdate = false; //DB操作成功フラグ（true:成功/false:失敗）

		MeiboDAO dao = new MeiboDAO();
		succesUpdate = dao.doUpdate(dto);

		return succesUpdate;
	}

}
