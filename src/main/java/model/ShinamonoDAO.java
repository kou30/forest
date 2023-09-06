package model;

import java.sql.Connection;
import java.sql.Date;
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

	public void ConnectCheck() {
		try {
			this.connect();
			System.out.println("OK");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
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
			ps.setDate(4, Shinamono.getRe_time());
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

	public List<ShinamonoDTO> findAll() {
		List<ShinamonoDTO> list = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM shinamono");
			rs = ps.executeQuery();
			while (rs.next()) {
				int shinamono_id = rs.getInt("shinamono_id");
				int user_id = rs.getInt("user_id");
				int meibo_id=rs.getInt("meibo_id");
				String aite_name = rs.getString("aite_name");
				Date re_time = rs.getDate("re_time");
				int bunrui = rs.getInt("bunrui");
				int category = rs.getInt("category");
				int item = rs.getInt("item");
				String shinamono_name = rs.getString("shinamono_name");
				int shinamono_kingaku = rs.getInt("shinamono_kingaku");
				String memo = rs.getString("memo");
				list.add(new ShinamonoDTO(shinamono_id, user_id, aite_name, re_time, bunrui, category, item,
						shinamono_name, shinamono_kingaku, memo,meibo_id));
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
					"INSERT INTO shinamono(user_id,aite_name,re_time,bunrui,category,item,shinamono_name,shinamono_kingaku,memo) VALUES(?,?,?,?,?,?,?,?,?)");
//			ps.setInt(1, Shinamono.getShinamono_id()); 自動割り当て
			ps.setInt(1, Shinamono.getUser_nr());
			ps.setString(2, Shinamono.getAite_name());
			ps.setDate(3, Shinamono.getRe_time());
			ps.setInt(4, Shinamono.getBunrui());
			ps.setInt(5, Shinamono.getCategory());
			ps.setInt(6, Shinamono.getItem());
			ps.setString(7, Shinamono.getShinamono_name());
			ps.setInt(8, Shinamono.getShinamono_kingaku());
			ps.setString(9, Shinamono.getMemo());
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
			ps=db.prepareStatement("SELECT * FROM shinamono WHERE shinamono_id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				int shinamono_id = rs.getInt("shinamono_id");
				int user_id = rs.getInt("user_id");
				int meibo_id=rs.getInt("meibo_id");
				String aite_name = rs.getString("aite_name");
				Date re_time = rs.getDate("re_time");
				int bunrui = rs.getInt("bunrui");
				int category = rs.getInt("category");
				int item = rs.getInt("item");
				String shinamono_name = rs.getString("shinamono_name");
				int shinamono_kingaku = rs.getInt("shinamono_id");
				String memo = rs.getString("memo");
				shinamono=new ShinamonoDTO(shinamono_id, user_id, aite_name, re_time, bunrui, category, item,
						shinamono_name, shinamono_kingaku, memo,meibo_id);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return shinamono;
	}
	public void deleteOne(int id) {
		try {
			this.connect();
			ps=db.prepareStatement("DELETE FROM shinamono WHERE shinamono_id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
}
