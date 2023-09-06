package model;

import java.util.List;

public class ShowAllMeiboBL {
	public List<MeiboDTO> executeSelectMeibo() {

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆対象の一覧取得するようタを登録するよう依頼
		MeiboDAO dao = new MeiboDAO();
		List<MeiboDTO> dtoList= dao.doSelect();
		
		return dtoList;
	}

}
