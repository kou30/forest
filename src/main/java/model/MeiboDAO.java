package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MeiboDAO {
	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//接続先のデータベース
	//※データベース名が「test_db」でない場合は該当の箇所を変更してください
	String JDBC_URL = "jdbc:mysql://localhost/family_db?characterEncoding=UTF-8&useSSL=false";

	//接続するユーザー名
	//※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
	String USER_ID = "forest_user";

	//接続するユーザーのパスワード
	//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
	String USER_PASS = "forest_pass";

	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/family_db");
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

	@SuppressWarnings("finally")
	public boolean doInsert(MeiboDTO dto) {
		boolean isSuccess = true;
		try {
			this.connect();
			StringBuffer buf = new StringBuffer();
			buf.append("INSERT INTO MEIBO (  ");
			buf.append("  USER_NR,               ");
			buf.append("  NAME,               ");
			buf.append("  YOMI,                ");
			buf.append("  SEX,                ");
			buf.append("  BUNRUI,                ");
			buf.append("  BIRTHDAY,                ");
			buf.append("  RELATIONSHIP, ");
			buf.append("  MEMO,            ");
			buf.append("  IMAGE                ");
			buf.append(") VALUES (            ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                   ");
			buf.append("  ?                   ");
			buf.append(")                     ");

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = db.prepareStatement(buf.toString()); //パラメータをセット
			ps.setInt(1, dto.getUser_nr()); //第1パラメータ：更新データ（名前）
			ps.setString(2, dto.getName()); //第1パラメータ：更新データ（名前）
			ps.setString(3, dto.getYomi()); //第2パラメータ：更新データ（年齢）
			ps.setInt(4, dto.getSex()); //第3パラメータ：更新データ（性別）
			ps.setString(5, dto.getBunrui()); //第1パラメータ：更新データ（食べ物）
			ps.setString(6, dto.getBirthday()); //第1パラメータ：更新データ（食べ物）
			ps.setInt(7, dto.getRelationship()); //第4パラメータ：更新データ（満足度）
			ps.setString(8, dto.getMemo()); //第5パラメータ：更新データ（メッセージ）
			ps.setBytes(9, dto.getImageData());
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			this.disconnect();
			return isSuccess;
		}
	}

	public List<MeiboDTO> doSelect(int user_nr) {
		List<MeiboDTO> dtoList = new ArrayList<MeiboDTO>();
		try {
			this.connect();
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT                     ");
			buf.append("  MEIBO_ID,               ");
			buf.append("  USER_NR,                ");
			buf.append("  NAME,               ");
			buf.append("  YOMI,                ");
			buf.append("  SEX,                ");
			buf.append("  BUNRUI,                ");
			buf.append("  BIRTHDAY,                ");
			buf.append("  RELATIONSHIP, ");
			buf.append("  MEMO,            ");
			buf.append("  IMAGE                ");
			buf.append("FROM                  ");
			buf.append("  MEIBO              ");
			//buf.append("  WHERE  DEL=0              ");
			buf.append("  WHERE  USER_NR=?              ");
			buf.append("  ORDER BY              ");
			buf.append("  MEIBO_ID;                ");

			ps = db.prepareStatement(buf.toString());
			ps.setInt(1, user_nr);
			rs = ps.executeQuery();

			//ResultSetオブジェクトからDTOリストに格納
			while (rs.next()) {
				MeiboDTO dto = new MeiboDTO();
				dto.setMeibo_id(rs.getInt("MEIBO_ID"));
				dto.setUser_nr(rs.getInt("USER_NR"));
				dto.setName(rs.getString("NAME"));
				dto.setYomi(rs.getString("YOMI"));
				dto.setSex(rs.getInt("SEX"));
				dto.setBunrui(rs.getString("BUNRUI"));
				dto.setBirthday(rs.getString("BIRTHDAY"));
				dto.setRelationship(rs.getInt("RELATIONSHIP"));
				dto.setMemo(rs.getString("MEMO"));
				dto.setImageData(rs.getBytes("IMAGE"));

				dtoList.add(dto);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return dtoList;
	}

	public MeiboDTO FindOne(int id) {
		MeiboDTO dto = new MeiboDTO();

		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM meibo WHERE meibo_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setMeibo_id(rs.getInt("MEIBO_ID"));
				dto.setUser_nr(rs.getInt("USER_NR"));
				dto.setName(rs.getString("NAME"));
				dto.setYomi(rs.getString("YOMI"));
				dto.setSex(rs.getInt("SEX"));
				dto.setBunrui(rs.getString("BUNRUI"));
				dto.setBirthday(rs.getString("BIRTHDAY"));
				dto.setRelationship(rs.getInt("RELATIONSHIP"));
				dto.setMemo(rs.getString("MEMO"));
				dto.setImageData(rs.getBytes("IMAGE"));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return dto;
	}

	public void deleteOne(int id) {
		try {
			this.connect();
			ps = db.prepareStatement("DELETE FROM meibo WHERE meibo_id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}

	@SuppressWarnings("finally")
	public boolean doUpdate(MeiboDTO dto) {
		boolean isSuccess = true;

		try {
			this.connect();
			StringBuffer buf = new StringBuffer();
			buf.append("UPDATE MEIBO set  ");
			buf.append("  NAME=?,               ");
			buf.append("  YOMI=?,                ");
			buf.append("  SEX=?,                ");
			buf.append("  BUNRUI=?,                ");
			buf.append("  BIRTHDAY=?,                ");
			buf.append("  RELATIONSHIP=?, ");
			buf.append("  MEMO=?,            ");
			buf.append("  IMAGE=?                ");
			buf.append("   WHERE MEIBO_ID=?)                     ");

			ps = db.prepareStatement(buf.toString());
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getYomi());
			ps.setInt(3, dto.getSex());
			ps.setString(4, dto.getBunrui());
			ps.setString(5, dto.getBirthday());
			ps.setInt(6, dto.getRelationship());
			ps.setString(7, dto.getMemo());
			ps.setBytes(8, dto.getImageData());
			ps.setInt(9, dto.getMeibo_id());
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			this.disconnect();
			return isSuccess;
		}
	}

}
