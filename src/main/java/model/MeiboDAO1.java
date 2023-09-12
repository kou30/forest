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

public class MeiboDAO1 {
	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/family_db");
		this.db = ds.getConnection();
	}

	private void disconnect()  {
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
		}finally {
			this.disconnect();
		}
	}
	public void insertOne(NeiboDTO family ) {
		try {
			this.connect();
			ps = db.prepareStatement("INSERT INTO familys(name,yomi,relationship,address,sex,birthday,note,photo) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1, family.getName());
			ps.setString(2, family.getYomi());
			ps.setString(3, family.getRelationship());
			ps.setString(4, family.getAddress());
			ps.setString(5, family.getSex());
			ps.setDate(6, family.getBirthday());
			ps.setString(7, family.getNote());
			ps.setString(8, family.getPhoto());
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}

	public List<NeiboDTO> findAll() {
		List<NeiboDTO> list = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM familys");
			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String yomi = rs.getString("yomi");
				String relationship = rs.getString("relationship");
				String address = rs.getString("address");
				String sex = rs.getString("sex");
				Date birthday = rs.getDate("birthday");
				String note = rs.getString("note");
				String photo = rs.getString("photo");
				list.add(new NeiboDTO(name,yomi,relationship,address,sex,birthday,note,photo));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return list;
	}
}
