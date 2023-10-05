
/** 
 * Filename: ScheduleDelete.java 
 * 
 * Description: 
 * このクラスは、「DeleteCheck」(削除確認ページ)から
 * カレンダーの1件分削除対象情報を受け取り、 
 * SQLの「shinamono」テーブルから対象の行を削除する
 * 
 * 
 * Author: kuroda yukie 
 * Creation Date: 2023-10-4
 * 
 * Modified By: 
 * Modification Date:  
 * Reason for Modification:  
 * 
 * Copyright (C) 2023 Forest All rights reserved. 
 * 
 * 
 */

package schedule;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


	@WebServlet("/ScheduleDelete")
	public class ScheduleDelete extends HttpServlet {
	    protected Connection conn = null;

	    public void init() throws ServletException {
	        String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	        String JDBC_URL = "jdbc:mysql://192.168.1.21/forest_db?characterEncoding=UTF-8&useSSL=false";
	        String USER_ID = "forest_user";
	        String USER_PASS = "forest_pass";

	        try {
	            Class.forName(DRIVER_NAME);
	            conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
	        } catch (ClassNotFoundException e) {
	            log("ClassNotFoundException: " + e.getMessage());
	        } catch (SQLException e) {
	            log("SQLException: " + e.getMessage());
	        } catch (Exception e) {
	            log("Exception: " + e.getMessage());
	            e.printStackTrace();
	            throw new ServletException("初期化エラー: " + e.getMessage(), e);
	        }
	    }

	    
	    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        res.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = res.getWriter();



        int id;
        int year;
        int month;

        String param = req.getParameter("ID");
        if (param == null || param.length() == 0){
            id = -999;
        }else{
            try{
                id = Integer.parseInt(param);
            }catch (NumberFormatException e){
                id = -999;
            }
        }

        param = req.getParameter("YEAR");
        if (param == null || param.length() == 0){
            year = -999;
        }else{
            try{
                year = Integer.parseInt(param);
            }catch (NumberFormatException e){
                year = -999;
            }
        }

        param = req.getParameter("MONTH");
        if (param == null || param.length() == 0){
            month = -999;
        }else{
            try{
                month = Integer.parseInt(param);
            }catch (NumberFormatException e){
                month = -999;
            }
        }

        /* IDが不正な値で来た場合はパラメータ無しで「MonthView」へリダイレクトする */
        if (id == -999){
            res.sendRedirect("MonthView");
        }

        try {
            String sql = "delete from schedule where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            pstmt.close();

        }catch (SQLException e){
            out.println("SQLException:" + e.getMessage());
        }

        StringBuffer sb = new StringBuffer();
        sb.append("MonthView");
        sb.append("?YEAR=");
        sb.append(year);
        sb.append("?MONTH=");
        sb.append(month);
        res.sendRedirect(new String(sb));

    }
}