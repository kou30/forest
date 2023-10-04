/** 
* Filename: ExecuteSelectMeiboBL.java
* 
* Description: 
* このクラスは、名簿情報を取得するためのメソッドを定義しています。
* 引数名簿IDを取りDAOクラスを利用してデータベースから情報を取得し、
* DTOとして返します。
* 
* Author: nagai kosuke 
* Creation Date: 2023-10-01 
* 
* Copyright (C) 2023 Forest All rights reserved. 
* 
* 
*/

package model;

public class ExecuteSelectMeiboBL {
	public MeiboDTO executeSelectMeibo(int id) {
		MeiboDAO dao = new MeiboDAO();
		MeiboDTO dto = (MeiboDTO) dao.FindOne(id);

		return dto;
	}

}
