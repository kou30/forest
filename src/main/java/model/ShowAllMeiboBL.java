/** 
* Filename: ShowAllMeiboBL.java
* 
* Description: 
* このクラスは、名簿情報を取得するためのメソッドを定義しています。
* 引数user_nrを取りDAOクラスを利用してデータベースから情報を取得し、
* DTOリストとして返します。
* 
* Author: nagai kosuke 
* Creation Date: 2023-10-01 
* 
* Copyright (C) 2023 Forest All rights reserved. 
* 
*/

package model;

import java.util.List;

public class ShowAllMeiboBL {
	public List<MeiboDTO> executeSelectMeibo(int user_nr) {

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆対象の一覧取得するようタを登録するよう依頼
		MeiboDAO dao = new MeiboDAO();
		List<MeiboDTO> dtoList= dao.doSelect(user_nr);
		
		return dtoList;
	}

}
