package model;
import javax.naming.NamingException;
public class CheckIDBL {
	public boolean checkID(String id) throws NamingException {

		UserInfoDao dao = new UserInfoDao();
		boolean kuro= dao.doCheckID(id);
		
		
		return kuro;
	}
}
