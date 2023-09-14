package model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;

/**----------------------------------------------------------------------*
 *■■■UserInfoDaoクラス■■■
 *概要：DAO（「user_info」テーブル）
 *----------------------------------------------------------------------**/
public class UserInfoDao {
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//接続先のデータベース
	//※データベース名が「test_db」でない場合は該当の箇所を変更してください
	String JDBC_URL    = "jdbc:mysql://localhost/family_db?characterEncoding=UTF-8&useSSL=false";

	//接続するユーザー名
	//※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
	String USER_ID     = "forest_user";

	//接続するユーザーのパスワード
	//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
	String USER_PASS   = "forest_pass";

	public UserInfoDto doSelect(String inputUserId, String inputPassWord) {

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数

		//抽出データ（UserInfoDto型）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		UserInfoDto dto = new UserInfoDto();

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append(" USER_NR,           ");
			buf.append("   USER_ID  ,       ");
			buf.append("   USER_NAME,       ");
			buf.append("   PASSWORD         ");
			buf.append(" FROM               ");
			buf.append("   USER_INFO        ");
			buf.append(" WHERE              ");
			buf.append("   USER_ID  = ? AND ");  //第1パラメータ
			buf.append("   PASSWORD = ?     ");  //第2パラメータ

			//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString( 1, inputUserId   );  //第1パラメータ：ユーザーID（ユーザー入力）
			ps.setString( 2, inputPassWord );  //第2パラメータ：ユーザーパスワード（ユーザー入力）

			//SQL文の送信＆戻り値としてResultSet（SQL抽出結果）を取得
			rs = ps.executeQuery();

			//--------------------------------------------------------------------------------
			//ResultSetオブジェクトからユーザーデータを抽出
			//--------------------------------------------------------------------------------
			if (rs.next()) {
				//ResultSetから1行分のレコード情報をDTOへ登録
				dto.setUser_nr(rs.getInt("USER_NR"));
				dto.setUserId(   rs.getString("USER_ID")   );    //ユーザーID
				dto.setUserName( rs.getString("USER_NAME") );    //ユーザー名
				dto.setPassWord( rs.getString("PASSWORD")  );    //ユーザーパスワード
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//ResultSetオブジェクトの接続解除
			if (rs != null) {    //接続が確認できている場合のみ実施
				try {
					rs.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		//抽出したユーザーデータを戻す
		return dto;
	}
	public UserInfoDto executeSelectUserInfo(String inputUserId, String inputPassWord) {

		UserInfoDto dto = new UserInfoDto(); //ユーザーデータ（UserInfoDto型）

		//DAOクラスをインスタンス化＆「user_info」テーブルからユーザーデータを抽出するよう依頼
		UserInfoDao dao = new UserInfoDao();
		dto = dao.doSelect(inputUserId, inputPassWord);

		//抽出したユーザーデータを戻す
		return dto;
	}
}
