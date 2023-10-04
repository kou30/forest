
/** 
 * Filename: ScheduleInsert.java 
 * 
 * Description: 
 * このクラスは、「NewSchedule」から受け取ったパラメーターを
 * SQLの「schedule」テーブルへ挿入する
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
import javax.servlet.http.HttpSession;

import model.UserInfoDto;


@WebServlet("/ScheduleInsert")
public class ScheduleInsert extends HttpServlet {
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

        req.setCharacterEncoding("UTF-8"); // リクエストの文字エンコーディングを設定
        res.setCharacterEncoding("UTF-8"); // レスポンスの文字エンコーディングを設定

        PrintWriter out = res.getWriter();

        int year;
        int month;
        int day;
        int shour;
        int sminute;
        int ehour;
        int eminute;
        String plan;
        String memo;

        String param = req.getParameter("YEAR");
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

        /* 日付が不正な値で来た場合はパラメータ無しで「MonthView」へリダイレクトする */
        if (year == -999 || month == -999 || day == -999){
            res.sendRedirect("MonthView");
        }
        String dateStr = year + "-" + month + "-" + day;

        String startTimeStr = shour + ":" + sminute + ":00";
        String endTimeStr = ehour + ":" + eminute + ":00";
        /* 日付が指定されていない場合は、開始及び終了時刻をNULLとして登録する */
        if (shour == -999 || sminute == -999 || ehour == -999 || eminute == -999){
            startTimeStr = null;
            endTimeStr = null;
        }

        /* ユーザー情報を取り出す */
        HttpSession session = req.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
		int user_nr = userInfoOnSession.getUser_nr();

        

        try {
            String sql = "insert into schedule (user_nr, scheduledate, starttime, endtime, schedule, schedulememo) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, user_nr);
            pstmt.setString(2, dateStr);
            pstmt.setString(3, startTimeStr);
            pstmt.setString(4, endTimeStr);
            pstmt.setString(5, plan);
            pstmt.setString(6, memo);
            pstmt.executeUpdate();

            pstmt.close();

        }catch (SQLException e){
            out.println("SQLException:" + e.getMessage());
        }

        StringBuffer sb = new StringBuffer();
        sb.append("MonthView");
        sb.append("?YEAR=");
        sb.append(year);
        sb.append("&MONTH=");
        sb.append(month - 1);
        res.sendRedirect(new String(sb));
    }
}