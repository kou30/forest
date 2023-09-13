package model;

import javax.naming.NamingException;

public class SignupBL {
	public boolean executeSignup(UserInfoDto dto) throws NamingException {

		boolean   succesInsert = false ; 

		UserInfoDao dao = new UserInfoDao();
		succesInsert = dao.doSignup(dto);

		return succesInsert;
	}
}
