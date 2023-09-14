package model;

import java.util.List;

public class DetailBL {

//	public ShinamonoDTO DetailShinamonoSelect(int id) {
//		ShinamonoDAO dao = new ShinamonoDAO();
//		ShinamonoDTO dto =dao.DetailFindOne(id);
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
