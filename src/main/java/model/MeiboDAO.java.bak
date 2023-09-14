package model;

import java.sql.Connection;
import java.sql.DriverManager;
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

	public boolean doInsert(MeiboDTO dto) {

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME); //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection con = null; // Connection（DB接続情報）格納用変数
		PreparedStatement ps = null; // PreparedStatement（SQL発行用オブジェクト）格納用変数

		//実行結果（真：成功、偽：例外発生）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		boolean isSuccess = true;

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------
			System.out.println("SQL文生成前");
			//発行するSQL文の生成（INSERT）
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
			ps = con.prepareStatement(buf.toString());			//パラメータをセット
			ps.setInt(1, dto.getUser_nr()); //第1パラメータ：更新データ（名前）
			ps.setString(2, dto.getName()); //第1パラメータ：更新データ（名前）
			ps.setString(3, dto.getYomi()); //第2パラメータ：更新データ（年齢）
			ps.setInt(4, dto.getSex()); //第3パラメータ：更新データ（性別）
			ps.setString(5, dto.getBunrui()); //第1パラメータ：更新データ（食べ物）
			ps.setString(6, dto.getBirthday()); //第1パラメータ：更新データ（食べ物）
			ps.setInt(7, dto.getRelationship()); //第4パラメータ：更新データ（満足度）
			ps.setString(8, dto.getMemo()); //第5パラメータ：更新データ（メッセージ）
			ps.setBytes(9, dto.getImageData());
			System.out.println(ps);
			System.out.println("SQL文実行前");
			ps.execute();
			System.out.println("SQL文実行後");
		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false;

		} finally {
			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if (isSuccess) {
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) { //接続が確認できている場合のみ実施
				try {
					ps.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) { //接続が確認できている場合のみ実施
				try {
					con.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		//実行結果を返す
		return isSuccess;
	}

	public List<MeiboDTO> doSelect(int user_nr) {

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME); //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		Connection con = null; // Connection（DB接続情報）格納用変数
		PreparedStatement ps = null; // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet rs = null; // ResultSet（SQL抽出結果）格納用変数

		//抽出結果格納用DTOリスト
		List<MeiboDTO> dtoList = new ArrayList<MeiboDTO>();

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

			ps = con.prepareStatement(buf.toString());
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//ResultSetオブジェクトの接続解除
			if (rs != null) { //接続が確認できている場合のみ実施
				try {
					rs.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) { //接続が確認できている場合のみ実施
				try {
					ps.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) { //接続が確認できている場合のみ実施
				try {
					con.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		//抽出結果を返す
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
}
