package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserInfoDao {
	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/forest_db");
		this.db = ds.getConnection();
	}

	private void disconnect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (db != null) {
				db.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public UserInfoDto doSelect(String inputUserId, String inputPassWord) {
		UserInfoDto dto = new UserInfoDto();
		try {
			this.connect();
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append(" USER_NR,           ");
			buf.append("   USER_ID  ,       ");
			buf.append("   USER_NAME,       ");
			buf.append("   PASSWORD         ");
			buf.append(" FROM               ");
			buf.append("   USER_INFO        ");
			buf.append(" WHERE              ");
			buf.append("   USER_ID  = ? AND "); //第1パラメータ
			buf.append("   PASSWORD = ?     "); //第2パラメータ

			//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
			ps = db.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString(1, inputUserId); //第1パラメータ：ユーザーID（ユーザー入力）
			ps.setString(2, inputPassWord); //第2パラメータ：ユーザーパスワード（ユーザー入力）

			//SQL文の送信＆戻り値としてResultSet（SQL抽出結果）を取得
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setUser_nr(rs.getInt("USER_NR"));
				dto.setUserId(rs.getString("USER_ID")); //ユーザーID
				dto.setUserName(rs.getString("USER_NAME")); //ユーザー名
				dto.setPassWord(rs.getString("PASSWORD")); //ユーザーパスワード
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
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

	public boolean doSignup(UserInfoDto dto) throws NamingException {
		boolean isSuccess = true;
		try {
			this.connect();
			StringBuffer buf = new StringBuffer();
			buf.append("INSERT INTO USER_INFO (  ");
			buf.append("   USER_ID,       ");
			buf.append("   USER_NAME,       ");
			buf.append("   PASSWORD         ");
			buf.append(") VALUES (            ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?                   ");
			buf.append(")                     ");

			//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
			ps = db.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString(1, dto.getUserId()); //第1パラメータ：ユーザーID（ユーザー入力）
			ps.setString(2, dto.getUserName()); //第2パラメータ：ユーザーパスワード（ユーザー入力）
			ps.setString(3, dto.getPassWord());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			this.disconnect();
		}

		//実行結果を返す
		return isSuccess;

	}
}
