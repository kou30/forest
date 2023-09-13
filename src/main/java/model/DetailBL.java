package model;

public class DetailBL {

	public ShinamonoDTO DetailShinamonoSelect(int id) {
		ShinamonoDAO dao = new ShinamonoDAO();
		ShinamonoDTO dto =dao.DetailFindOne(id);
		return dto;
	}

	public MeiboDTO DetailMeiboSelect(int id) {
		MeiboDAO dao = new MeiboDAO();
		MeiboDTO dto = dao.FindOne(id);
		return dto;
	}

}
