package model;

public class ExecuteSelectMeiboBL {
	public MeiboDTO executeSelectMeibo(int id) {
		MeiboDAO dao = new MeiboDAO();
		MeiboDTO dto = (MeiboDTO) dao.FindOne(id);

		return dto;
	}

}
