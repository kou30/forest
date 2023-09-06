package model;

public class ExecuteSelectShinamonoBL {
	public ShinamonoDTO executeSelectShinamono(int id) {
		ShinamonoDAO dao = new ShinamonoDAO();
		ShinamonoDTO dto = (ShinamonoDTO) dao.FindOne(id);

		return dto;
	}
}
