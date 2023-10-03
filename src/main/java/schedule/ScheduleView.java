package schedule;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDto;


	@WebServlet("/ScheduleView2")
	public class ScheduleView extends HttpServlet {
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
	        req.setCharacterEncoding("UTF-8"); // リクエストの文字エンコーディングを設定
	        res.setCharacterEncoding("UTF-8"); // レスポンスの文字エンコーディングを設定
	        PrintWriter out = res.getWriter();
	        
	        
        int year = -1;
        int month = -1;
        int day = -1;
        int currentscheduleid;
        String currentStartTime = "";
        String currentEndTime = "";
        String currentSchedule = "";
        String currentMemo = "";

        String param = req.getParameter("ID");
        if (param == null || param.length() == 0){
            currentscheduleid = -1;
        }else{
            try{
                currentscheduleid = Integer.parseInt(param);
            }catch (NumberFormatException e){
                currentscheduleid = -1;
            }
        }

        /* パラメータが不正な場合はトップページへリダイレクト */
        if (currentscheduleid == -1){
            res.sendRedirect("top.html");
        }

        /* ユーザー情報を取り出す */
		HttpSession session = req.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
		int user_nr = userInfoOnSession.getUser_nr();
		String username = userInfoOnSession.getUserName();


        try {
            String sql = "SELECT * FROM schedule WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, currentscheduleid);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            String scheduledate = rs.getString("scheduledate");
            String yearStr = scheduledate.substring(0, 4);
            String monthStr = scheduledate.substring(5, 7);
            String dayStr = scheduledate.substring(8, 10);

            year = Integer.parseInt(yearStr);
            month = Integer.parseInt(monthStr) - 1;
            day = Integer.parseInt(dayStr);

            currentStartTime = rs.getString("starttime");
            currentEndTime = rs.getString("endtime");
            currentSchedule = rs.getString("schedule");
            currentMemo = rs.getString("schedulememo");

            rs.close();
            pstmt.close();

        }catch (SQLException e){
            log("SQLException:" + e.getMessage());
        }

        StringBuffer sb = new StringBuffer();

        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0.1//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");

        sb.append("<html lang=\"ja\">");
		sb.append("<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        sb.append("<meta http-equiv=\"Content-Type\" Content=\"text/html;charset=UTF-8\">");

        sb.append("<title>スケジュール登録</title>");

        sb.append("<style>");
        sb.append("table.sche{border:1px solid #a9a9a9;padding:0px;margin:0px;border-collapse:collapse;}");
        sb.append("td{vertical-align:top;margin:0px;padding:2px;font-size:0.75em;height:20px;}");
        sb.append("td.top{border-bottom:1px solid #a9a9a9;text-align:center;}");
        sb.append("td.time{background-color:#f0f8ff;text-align:right;border-right:1px double #a9a9a9;padding-right:5px;}");
        sb.append("td.timeb{background-color:#f0f8ff;border-bottom:1px solid #a9a9a9;border-right:1px double #a9a9a9;}");
        sb.append("td.contents{background-color:#ffffff;border-bottom:1px dotted #a9a9a9;}");
        sb.append("td.contentsb{background-color:#ffffff;border-bottom:1px solid #a9a9a9;}");
        sb.append("td.ex{background-color:#ffebcd;border:1px solid #8b0000;}");
        sb.append("table.view{border:1px solid #a9a9a9;padding:0px;margin:0px;border-collapse:collapse;width:250px}");
        sb.append("table.view td{border:1px solid #a9a9a9;}");
        sb.append("table.view td.left{width:70px;background-color:#f0f8ff;}");
        sb.append("img{border:0px;}");
        sb.append("p{font-size:0.75em;}");

        sb.append("#contents{margin:0;padding:0;width:710px;}");
        sb.append("#left{margin:0;padding:0;float:left;width:400px;}");
        sb.append("#right{margin:0;padding:0;float:right;width:300px;background-color:#ffffff;}");
        sb.append("#contents:after{content:\".\";display:block;height:0;clear:both;visibility:hidden;}");
        sb.append("</style>");
		sb.append("<link rel=\"stylesheet\" href=\"css/main.css\">");
		sb.append("<link rel=\"stylesheet\" href=\"css/schedule2.css\">");



        sb.append("</head>");
        sb.append("<body>");
		sb.append("		<header>\r\n"
				+ "		<p class=\"HeaderTagline\">贈り物・頂き物・記念日・年賀状送付管理・お年玉管理・弔慶事金額を一括管理</p>\r\n"
				+ "		<div class=\"container\">\r\n"
				+ "			<img src=\"./images/ENcounter.png\" alt=\"ENcounter\" class=\"img\">\r\n"
				+ "			<nav class=\"nav\">\r\n"
				+ "				<ul>\r\n"
				+ "					<li><a href=\"MainPage\">TOP</a></li>\r\n"
				+ "					<li><a href=\"MeiboEntry\">名簿登録</a></li>\r\n"
				+ "					<li><a href=\"ShowAllMeibo\">名簿一覧</a></li>\r\n"
				+ "					<li><a href=\"ShowAllShinamono\">贈り物・貰い物一覧</a></li>\r\n"
				+ "					<li><a href=\"MonthView7\">カレンダー</a></li>\r\n"
				+ "					<li><a href=\"Logoutinfo\" class=\"logout\">ログアウト</a></li>\r\n"
				+ "				</ul>\r\n"
				+ "			</nav>\r\n"
				+ "		</div>\r\n"
				+ "	</header>");

        sb.append("<div class=\"image\"><main><p>");
        sb.append(username);
        sb.append("さんのスケジュールです");
        sb.append("</p>");

        sb.append("<p>");
        sb.append("既存スケジュール確認  ");
        sb.append("[<a href=\"MonthView7");
        sb.append("?YEAR=");
        sb.append(year);
        sb.append("&MONTH=");
        sb.append(month);
        sb.append("\">カレンダーへ戻る</a>]");
        sb.append("</p>");

        String[] scheduleArray = new String[49];
        int[] widthArray = new int[49];

        for (int i = 0 ; i < 49 ; i++){
            scheduleArray[i] = "";
            widthArray[i] = 0;
        }

        try {
            String sql = "SELECT * FROM schedule WHERE user_nr = ? and scheduledate = ? ORDER BY starttime";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            String startDateStr = year + "-" + (month + 1) + "-" + day;
            pstmt.setInt(1, user_nr);
            pstmt.setString(2, startDateStr);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String starttime = rs.getString("starttime");
                String endtime = rs.getString("endtime");
                String schedule = rs.getString("schedule");

                if (starttime == null || endtime == null){
                    widthArray[0] = 1;

                    StringBuffer sbSchedule = new StringBuffer();
                    sbSchedule.append("<a href=\"ScheduleView2?ID=");
                    sbSchedule.append(id);
                    sbSchedule.append("\">");
                    sbSchedule.append(schedule);
                    sbSchedule.append("</a>");

                    scheduleArray[0] = scheduleArray[0] + (new String(sbSchedule)) + "<br>";

                }else{
                    String startTimeStr = starttime.substring(0, 2);
                    String startMinuteStr = starttime.substring(3, 5);

                    int startTimeNum = Integer.parseInt(startTimeStr);
                    int index = startTimeNum * 2 + 1;
                    if (startMinuteStr.equals("30")){
                        index++;
                    }

                    if (widthArray[index] == 0){
                    /* 既にデータが入っていた場合は異常な状態なので無視する */

                        String endTimeStr = endtime.substring(0, 2);
                        String endMinuteStr = endtime.substring(3, 5);

                        int endTimeNum = Integer.parseInt(endTimeStr);
                        int width = (endTimeNum - startTimeNum) * 2;

                        if (startMinuteStr.equals("30")){
                            width--;
                        }

                        if (endMinuteStr.equals("30")){
                            width++;
                        }

                        StringBuffer sbSchedule = new StringBuffer();
                        sbSchedule.append(startTimeStr);
                        sbSchedule.append(":");
                        sbSchedule.append(startMinuteStr);
                        sbSchedule.append("-");
                        sbSchedule.append(endTimeStr);
                        sbSchedule.append(":");
                        sbSchedule.append(endMinuteStr);
                        sbSchedule.append(" ");
                        sbSchedule.append("<a href=\"ScheduleView2?ID=");
                        sbSchedule.append(id);
                        sbSchedule.append("\">");
                        sbSchedule.append(schedule);
                        sbSchedule.append("</a>");

                        scheduleArray[index] = new String(sbSchedule);
                        widthArray[index] = width;

                        /* 同じスケジュールの先頭以外の箇所には「-1」を設定 */
                        for (int i = 1 ; i < width ; i++){
                            widthArray[index + i] = -1;
                        }
                    }
                }
            }

            rs.close();
            pstmt.close();

        }catch (SQLException e){
            log("SQLException:" + e.getMessage());
        }

        sb.append("<div id=\"contents\">");

        sb.append("<div id=\"left\">");

        sb.append("<table class=\"sche\">");
        sb.append("<tr><td class=\"top\" style=\"width:80px\">時刻</td><td class=\"top\" style=\"width:300px\">予定</td></tr>");

        sb.append("<tr><td class=\"timeb\">未定</td>");
        sb.append("<td class=\"contentsb\">");
        if (widthArray[0] == 1){
            sb.append(scheduleArray[0]);
        }
        sb.append("</td></tr>");

        for (int i = 1 ; i < 49 ; i++){
            if (i % 2 == 1){
                sb.append("<tr><td class=\"time\">");
                sb.append(i / 2);
                sb.append(":00</td>");
            }else{
                sb.append("<tr><td class=\"timeb\"></td>");
            }

            if (widthArray[i] != 0){
                if (widthArray[i] != -1){
                    sb.append("<td class=\"ex\" rowspan=\"");
                    sb.append(widthArray[i]);
                    sb.append("\">");

                    sb.append(scheduleArray[i]);
                }
            }else{
                if (i % 2 == 1){
                    sb.append("<td class=\"contents\">");
                }else{
                    sb.append("<td class=\"contentsb\">");
                }
            }

            sb.append("</td></tr>");
        }

        sb.append("</table>");

        sb.append("</div>");

        sb.append("<div id=\"right\">");

        sb.append("<table class=\"view\">");
        sb.append("<tr><td class=\"left\">日付</td><td>");
        sb.append(year);
        sb.append("年");
        sb.append(month + 1);
        sb.append("月");
        sb.append(day);
        sb.append("日");
        sb.append("</td></tr>");
        sb.append("<tr><td class=\"left\">時間</td><td>");
        if (currentStartTime == null){
            sb.append("未定");
        }else{
            sb.append(currentStartTime.substring(0, 5));
            sb.append(" - ");
            sb.append(currentEndTime.substring(0, 5));
        }
        sb.append("</td></tr>");
        sb.append("<tr><td class=\"left\">スケジュール</td><td>");
        sb.append(currentSchedule);
        sb.append("</td></tr>");
        sb.append("<tr><td class=\"left\" style=\"height:150px;\">メモ</td><td>");
        currentMemo = currentMemo.replaceAll("\r\n", "<br>");
        sb.append(currentMemo);
        sb.append("</td></tr>");
        sb.append("</table>");

        sb.append("<p>");
        sb.append("[<a href=\"EditSchedule2?ID=");
        sb.append(currentscheduleid);
        sb.append("\">スケジュールの変更</a>]");
        sb.append("  ");
        sb.append("[<a href=\"DeleteCheck2?ID=");
        sb.append(currentscheduleid);
        sb.append("\">スケジュールの削除</a>]");
        sb.append("</p>");

        sb.append("</div>");
        sb.append("</div>");
        sb.append("</main></div></body>");
        sb.append("</html>");

        out.println(new String(sb));
    }

    protected int getMonthLastDay(int year, int month, int day){

        Calendar calendar = Calendar.getInstance();

        /* 今月が何日までかを確認する */
        calendar.set(year, month + 1, 0);
        int thisMonthlastDay = calendar.get(Calendar.DATE);

        return thisMonthlastDay;
    }

}