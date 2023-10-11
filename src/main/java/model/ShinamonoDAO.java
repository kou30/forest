/** 
* Filename: ShinamonoDAO.java
* 
* Description: 
* このクラスは、データベースとのやり取りを担当し、
* 品物情報の操作（挿入、選択、更新、削除）を行うためのメソッドを提供します。  
* 
* Author: nagai kosuke 
* Creation Date: 2023-10-04 
* 
* Copyright (C) 2023 Forest All rights reserved. 
* 
* 
*/

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

public class ShinamonoDAO {
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

	public void insertOne(ShinamonoDTO Shinamono) {
		try {
			this.connect();
			ps = db.prepareStatement(
					"INSERT INTO shinamono(shinamono_id,oneself_id,kojin_id,re_time,bunrui,category,item,shinamono_name,shinamono_kingaku,memo) VALUES(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, Shinamono.getShinamono_id());
			ps.setInt(2, Shinamono.getUser_nr());
			ps.setString(3, Shinamono.getAite_name());
			ps.setString(4, Shinamono.getRe_time());
			ps.setInt(5, Shinamono.getBunrui());
			ps.setInt(6, Shinamono.getCategory());
			ps.setInt(7, Shinamono.getItem());
			ps.setString(8, Shinamono.getShinamono_name());
			ps.setInt(9, Shinamono.getShinamono_kingaku());
			ps.setString(10, Shinamono.getMemo());
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}

	public List<ShinamonoDTO> findAll(int user_nr) {
		List<ShinamonoDTO> list = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM shinamono WHERE USER_NR=?");
			ps.setInt(1, user_nr);
			rs = ps.executeQuery();
			while (rs.next()) {
				int shinamono_id = rs.getInt("shinamono_id");
				user_nr = rs.getInt("user_nr");
				int meibo_id = rs.getInt("meibo_id");
				String aite_name = rs.getString("aite_name");
				String re_time = rs.getString("re_time");
				int bunrui = rs.getInt("bunrui");
				int category = rs.getInt("category");
				int item = rs.getInt("item");
				String shinamono_name = rs.getString("shinamono_name");
				int shinamono_kingaku = rs.getInt("shinamono_kingaku");
				String memo = rs.getString("memo");
				list.add(new ShinamonoDTO(shinamono_id, user_nr, meibo_id, aite_name, re_time, bunrui, category, item,
						shinamono_name, shinamono_kingaku, memo));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return list;
	}

	@SuppressWarnings("finally")
	public boolean doInsertShinamono(ShinamonoDTO Shinamono) {
		boolean isSuccess = true;
		try {
			this.connect();
			ps = db.prepareStatement(
					"INSERT INTO shinamono(user_nr,meibo_id,aite_name,re_time,bunrui,category,item,shinamono_name,shinamono_kingaku,memo) VALUES(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, Shinamono.getUser_nr());
			ps.setInt(2, Shinamono.getMeibo_id());
			ps.setString(3, Shinamono.getAite_name());
			ps.setString(4, Shinamono.getRe_time());
			ps.setInt(5, Shinamono.getBunrui());
			ps.setInt(6, Shinamono.getCategory());
			ps.setInt(7, Shinamono.getItem());
			ps.setString(8, Shinamono.getShinamono_name());
			ps.setInt(9, Shinamono.getShinamono_kingaku());
			ps.setString(10, Shinamono.getMemo());
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			this.disconnect();
			return isSuccess;
		}
	}

	public ShinamonoDTO FindOne(int id) {
		ShinamonoDTO shinamono = null;
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM shinamono WHERE shinamono_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				int shinamono_id = rs.getInt("shinamono_id");
				int user_nr = rs.getInt("user_nr");
				int meibo_id = rs.getInt("meibo_id");
				String aite_name = rs.getString("aite_name");
				String re_time = rs.getString("re_time");
				int bunrui = rs.getInt("bunrui");
				int category = rs.getInt("category");
				int item = rs.getInt("item");
				String shinamono_name = rs.getString("shinamono_name");
				int shinamono_kingaku = rs.getInt("shinamono_kingaku");
				String memo = rs.getString("memo");
				shinamono = new ShinamonoDTO(shinamono_id, user_nr, meibo_id, aite_name, re_time, bunrui, category,
						item,
						shinamono_name, shinamono_kingaku, memo);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return shinamono;
	}

	public boolean deleteOne(int id) {
		boolean result = false;
		try {
			this.connect();
			ps = db.prepareStatement("DELETE FROM shinamono WHERE shinamono_id=?");
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				result = true;
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return result;

	}

	@SuppressWarnings("finally")
	public boolean doUpdate(ShinamonoDTO Shinamono) {
		boolean isSuccess = true;
		try {
			this.connect();
			ps = db.prepareStatement(
					"UPDATE shinamono SET re_time=?,bunrui=?,category=?,item=?,shinamono_name=?,shinamono_kingaku=?,memo=? WHERE SHINAMONO_ID=?");
			ps.setString(1, Shinamono.getRe_time());
			ps.setInt(2, Shinamono.getBunrui());
			ps.setInt(3, Shinamono.getCategory());
			ps.setInt(4, Shinamono.getItem());
			ps.setString(5, Shinamono.getShinamono_name());
			ps.setInt(6, Shinamono.getShinamono_kingaku());
			ps.setString(7, Shinamono.getMemo());
			ps.setInt(8, Shinamono.getShinamono_id());
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			this.disconnect();
			return isSuccess;
		}
	}

	public List<ShinamonoDTO> DetailFindOne(int id) {
		List<ShinamonoDTO> shinamono = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM shinamono WHERE meibo_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int shinamono_id = rs.getInt("shinamono_id");
				int user_nr = rs.getInt("user_nr");
				int meibo_id = rs.getInt("meibo_id");
				String aite_name = rs.getString("aite_name");
				String re_time = rs.getString("re_time");
				int bunrui = rs.getInt("bunrui");
				int category = rs.getInt("category");
				int item = rs.getInt("item");
				String shinamono_name = rs.getString("shinamono_name");
				int shinamono_kingaku = rs.getInt("shinamono_kingaku");
				String memo = rs.getString("memo");
				shinamono.add(new ShinamonoDTO(shinamono_id, user_nr, meibo_id, aite_name, re_time, bunrui, category,
						item,
						shinamono_name, shinamono_kingaku, memo));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return shinamono;
	}

	public List<ShinamonoDTO> NarrowDownSelect(String selectedOption, String Write, int id) {
		List<ShinamonoDTO> Narrowdown = new ArrayList<>();
		try {
			this.connect();
			//		ps = db.prepareStatement("SELECT * FROM  forest_db.shinamono WHERE YEAR(re_time) = ? and user_nr=?");
			String query = "SELECT * FROM forest_db.shinamono WHERE user_nr = ?";

			if ("2".equals(selectedOption)) {
				query += " AND aite_name = ?";
			} else if ("3".equals(selectedOption)) {
				query += " AND re_time = ?";
			}
			ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, Write);
			rs = ps.executeQuery();
			while (rs.next()) {
				int shinamono_id = rs.getInt("shinamono_id");
				int user_nr = rs.getInt("user_nr");
				int meibo_id = rs.getInt("meibo_id");
				String aite_name = rs.getString("aite_name");
				String re_time = rs.getString("re_time");
				int bunrui = rs.getInt("bunrui");
				int category = rs.getInt("category");
				int item = rs.getInt("item");
				String shinamono_name = rs.getString("shinamono_name");
				int shinamono_kingaku = rs.getInt("shinamono_kingaku");
				String memo = rs.getString("memo");
				Narrowdown.add(new ShinamonoDTO(shinamono_id, user_nr, meibo_id, aite_name, re_time, bunrui, category,
						item,
						shinamono_name, shinamono_kingaku, memo));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return Narrowdown;
	}

}
