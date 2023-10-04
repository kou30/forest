/** 
 * Filename: ScheduleUpdate.java 
 * 
 * Description: 
 * このクラスは、「EditSchedule」(再編集)から受け取ったパラメーターを
 * SQLの「shinamono」テーブルへ再挿入し直す
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


	@WebServlet("/ScheduleUpdate")
	public class ScheduleUpdate extends HttpServlet {
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

	    
	    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        res.setContentType("text/html;charset=UTF-8");
	        req.setCharacterEncoding("UTF-8"); // リクエストの文字エンコーディングを設定
	        PrintWriter out = res.getWriter();


	        int id;
	        int year;
	        int month;
	        int day;
	        int shour;
	        int sminute;
	        int ehour;
	        int eminute;
	        String plan;
	        String memo;

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

	        param = req.getParameter("DAY");
	        if (param == null || param.length() == 0){
	            day = -999;
	        }else{
	            try{
	                day = Integer.parseInt(param);
	            }catch (NumberFormatException e){
	                day = -999;
	            }
	        }

	        param = req.getParameter("SHOUR");
	        if (param == null || param.length() == 0){
	            shour = -999;
	        }else{
	            try{
	                shour = Integer.parseInt(param);
	            }catch (NumberFormatException e){
	                shour = -999;
	            }
	        }

	        param = req.getParameter("SMINUTE");
	        if (param == null || param.length() == 0){
	            sminute = -999;
	        }else{
	            try{
	                sminute = Integer.parseInt(param);
	            }catch (NumberFormatException e){
	                sminute = -999;
	            }
	        }

	        param = req.getParameter("EHOUR");
	        if (param == null || param.length() == 0){
	            ehour = -999;
	        }else{
	            try{
	                ehour = Integer.parseInt(param);
	            }catch (NumberFormatException e){
	                ehour = -999;
	            }
	        }

	        param = req.getParameter("EMINUTE");
	        if (param == null || param.length() == 0){
	            eminute = -999;
	        }else{
	            try{
	                eminute = Integer.parseInt(param);
	            }catch (NumberFormatException e){
	                eminute = -999;
	            }
	        }

	        param = req.getParameter("PLAN");
	        if (param == null || param.length() == 0){
	            plan = "";
	        }else{
	            try{
	                plan = param;
	            }catch (NumberFormatException e){
	                plan = "";
	            }
	        }

	        param = req.getParameter("MEMO");
	        if (param == null || param.length() == 0){
	            memo = "";
	        }else{
	            try{
	                memo = param;
	            }catch (NumberFormatException e){
	                memo = "";
	            }
	        }

	        /* IDや日付が不正な値で来た場合はパラメータ無しで「MonthView」へリダイレクトする */
	        if (id == -999 || year == -999 || month == -999 || day == -999){
	            res.sendRedirect("MonthView6");
	        }
	        String dateStr = year + "-" + month + "-" + day;

	        String startTimeStr = shour + ":" + sminute + ":00";
	        String endTimeStr = ehour + ":" + eminute + ":00";
	        /* 日付が指定されていない場合は、開始及び終了時刻をNULLとして登録する */
	        if (shour == -999 || sminute == -999 || ehour == -999 || eminute == -999){
	            startTimeStr = null;
	            endTimeStr = null;
	        }

	        try {
	            String sql = "update schedule set scheduledate=?, starttime=?, endtime=?, schedule=?, schedulememo=? where id = ?";
	            PreparedStatement pstmt = conn.prepareStatement(sql);

	            pstmt.setString(1, dateStr);
	            pstmt.setString(2, startTimeStr);
	            pstmt.setString(3, endTimeStr);
	            pstmt.setString(4, plan);
	            pstmt.setString(5, memo);
	            pstmt.setInt(6, id);

	            pstmt.executeUpdate();

	            pstmt.close();

	        }catch (SQLException e){
	            out.println("SQLException:" + e.getMessage());
	        }

	        StringBuffer sb = new StringBuffer();
	        sb.append("ScheduleView2");
	        sb.append("?ID=");
	        sb.append(id);
	        res.sendRedirect(new String(sb));

	    }
	}