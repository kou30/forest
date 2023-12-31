/** 
 * Filename: MonthView.java 
 * 
 * Description: 
 * このクラスは、
 * ●カレンダー1カ月分の表フォーマットを表示し
 * 	ログイン日に合わせ今月がいつなのか判断し日付と曜日を表示する
 * 
 * ●「createMonthLink」メソッドで前月と翌月のリンクを押すと、
 * 	　対応する日付と曜日を表示
 * 
 * ●「isLeapYear」メソッドで閏年か判断し2月末日の日付を調整する
 * 
 * ●「createScheduleStr」メソッド
 * 	①新規スケジュール(「NewSchedule」)のタグを作成
 * 	　対象月の日付枠にしか編集リンクを表示させない
 * 
 * 	②SQLの「meibo」テーブルの誕生日を利用し、該当月日に
 * 	 「○○さんの誕生日」と該当月のカレンダーを開く毎に
 * 	  SQLから読み取りカレンダーに自動表示
 * 
 * 	③SQLから「schedule」テーブルのログイン者が登録したスケジュールの
 * 	　表示対象月分を読み取りカレンダーへ表示
 * 
 *  ④ログイン者が登録したスケジュールをクリックすると
 *   「ScheduleView」(スケジュール詳細確認、削除・編集確認画面)へ遷移
 *   
 *  ⑤「createMonthLink」メソッドで今月は何月か何日から何日まであるか確認
 *     前月・当月・翌月の情報を読み取っておく

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

@WebServlet("/MonthView")
public class MonthView extends HttpServlet {
	protected Connection conn = null;

	public void init() throws ServletException {
		String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
		String JDBC_URL = "jdbc:mysql://192.168.1.21/forest_db?characterEncoding=UTF-8&useSSL=false";
		String USER_ID = "forest_user";
		String USER_PASS = "forest_pass";

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
		} catch (ClassNotFoundException e) {	//JDBCドライバのクラスが見つからない場合
			log("ClassNotFoundException: " + e.getMessage());
		} catch (SQLException e) {	//SQL関連の例外が発生した場合
			log("SQLException: " + e.getMessage());
		} catch (Exception e) {	//それ以外の例外が発生した場合
			log("Exception: " + e.getMessage());
			e.printStackTrace();
			throw new ServletException("初期化エラー: " + e.getMessage(), e);
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // リクエストの文字エンコーディングを設定
		res.setCharacterEncoding("UTF-8"); // レスポンスの文字エンコーディングを設定
		PrintWriter out = res.getWriter();

		int[] calendarDay;
		int count;

		int year;
		int month;
		int day = 1;

		calendarDay = new int[42]; /* 最大で7日×6週 */
		count = 0;
		
		//HTTP リクエストから "YEAR" および "MONTH" パラメータを取得
		String param = req.getParameter("YEAR");
		if (param == null || param.length() == 0) {
			year = -999;
		} else {
			try {
				year = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				year = -999;
			}
		}

		param = req.getParameter("MONTH");
		if (param == null || param.length() == 0) {
			month = -999;
		} else {
			try {
				month = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				month = -999;
			}
		}

		/* パラメータが指定されていない場合は本日の日付を設定 */
		if (year == -999 || month == -999) {
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DATE);
		} else {
			if (month == 12) {
				month = 0;
				year++;
			}

			if (month == -1) {
				month = 11;
				year--;
			}
		}
		//ユーザ情報の取得
		HttpSession session = req.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
		int user_nr = userInfoOnSession.getUser_nr();
		String username = userInfoOnSession.getUserName();

		StringBuffer sb = new StringBuffer();

		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0.1//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");

		sb.append("<html lang=\"ja\">");
		sb.append("<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		sb.append("<meta http-equiv=\"Content-Type\" Content=\"text/html;charset=UTF-8\">");
		sb.append("<link rel=\"stylesheet\" href=\"css/Schedul.css\">");
		sb.append("<link rel=\"stylesheet\" href=\"css/main.css\">");

		
		sb.append("<title>スケジュール管理</title>");

		sb.append("<style>");
		sb.append("table{border:1px solid #a9a9a9;width:90%;padding:0px;margin:0px;border-collapse:collapse;}");
		sb.append(
				"td{width:12%;border-top:1px solid #a9a9a9;border-left:1px solid #a9a9a9;vertical-align:top;margin:0px;padding:2px;}");
		sb.append("td.week{text-align:center;}");
		sb.append("td.day{background-color:#f5f5f5;text-align:right;font-size:0.75em;}");
		sb.append("td.otherday{background-color:#f5f5f5;color:#d3d3d3;text-align:right;font-size:0.75em;}");
		sb.append("td.sche{background-color:#fffffff;text-align:left;height:80px;}");
		sb.append("img{border:0px;}");
		sb.append("span.small{font-size:0.75em;}");
		sb.append("</style>");
	

		sb.append("</head>");
		sb.append("<body>");
	
//		header
		sb.append("	<header>\r\n"
				+ "		<p class=\"HeaderTagline\">贈り物・頂き物・記念日・年賀状送付管理・お年玉管理・弔慶事金額を一括管理</p>\r\n"
				+ "		<div class=\"container\">\r\n"
				+ "			<img src=\"./images/ENcounter.png\" alt=\"ENcounter\" class=\"img\">\r\n"
				+ "			<nav class=\"nav\">\r\n"
				+ "				<ul>\r\n"
				+ "					<li><p class=\"name\">");
		sb.append(username);
		sb.append("さんのページ\r\n"
				+ "						</p></li>\r\n"
				+ "					<li><a href=\"MainPage\">TOP</a></li>\r\n"
				+ "					<li><a href=\"MeiboEntry\">名簿登録</a></li>\r\n"
				+ "					<li><a href=\"ShowAllMeibo\">名簿一覧</a></li>\r\n"
				+ "					<li><a href=\"ShowAllShinamono\">贈り物・頂き物一覧</a></li>\r\n"
				+ "					<li><a href=\"MonthView\">カレンダー</a></li>\r\n"
				+ "					<li><a href=\"Logoutinfo\" class=\"logout\">ログアウト</a></li>\r\n"
				+ "				</ul>\r\n"
				+ "			</nav>\r\n"
				+ "		</div>\r\n"
				+ "	</header>");	
		sb.append("<div class=\"image\"><main><p class=WelcomeMessage>");
		sb.append(username);
		sb.append("さんのスケジュール帳");
		sb.append("</p>");

		/* 日付データを配列に格納 */
		count = setDateArray(year, month, day, calendarDay, count);

		/* 年月のリンク作成 */
		sb.append(createMonthLink(year, month));

		sb.append("<table>");

		sb.append(
				"<tr><td class=\"week sun\">日</td><td class=\"week\">月</td><td class=\"week\">火</td><td class=\"week\">水</td><td class=\"week\">木</td><td class=\"week\">金</td><td class=\"week sut\">土</td></tr>");

		int weekCount = count / 7;

		for (int i = 0; i < weekCount; i++) {
			/* スケジュールの日付画面を作成する */
			sb.append("<tr>");

			for (int j = i * 7; j < i * 7 + 7; j++) {
				if (calendarDay[j] > 35) {
					sb.append("<td class=\"otherday\">");
					sb.append(calendarDay[j] - 35);
				} else {
					sb.append("<td class=\"day\">");
					sb.append(calendarDay[j]);
				}
				sb.append("</td>");
			}

			sb.append("</tr>");

			/* カレンダーのスケジュール登録画面を作成する */
			sb.append(createScheduleStr(year, month, i * 7, calendarDay, user_nr));
		}

		sb.append("</table>");

		sb.append("</div></body>");
		sb.append("</html>");

		out.println(new String(sb));
	}

	/* スケジュール登録へのリンクを設定する */
	/* スケジュール情報と名簿の誕生日を表示 */
	protected String createScheduleStr(int year, int month, int startDayNo, int[] calendarDay, int userid) {
		StringBuffer sb = new StringBuffer();

		sb.append("<tr>");

		for (int i = startDayNo; i < startDayNo + 7; i++) {
			if (calendarDay[i] > 35) {
				/* 前月及び翌月の箇所にはアイコンは表示しない */
				sb.append("<td class=\"sche\"></td>");
			} else {
				sb.append("<td class=\"sche\">");
				sb.append("<a href=\"NewSchedule");

				/* パラメータの作成 */
				sb.append("?YEAR=");
				sb.append(year);
				sb.append("&MONTH=");
				sb.append(month);
				sb.append("&DAY=");
				sb.append(calendarDay[i]);

				sb.append("\">");
				sb.append("<img src=\"./images/icon .png\" width=\"25\" height=\"25\">");
				sb.append("</a><br>");
				//名簿テーブルのその日誕生日のデータを取得し表示
				try {
					String sql1 = "SELECT * FROM meibo WHERE user_nr = ? and birthday like ?";
					PreparedStatement pstmt = conn.prepareStatement(sql1);

					String monthStr = String.format("%02d", month + 1);
					String dayStr = String.format("%02d", calendarDay[i]);
					String startDateStr = "%" + monthStr + "-" + dayStr;

					pstmt.setInt(1, userid);
					pstmt.setString(2, startDateStr);

					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						String name = rs.getString("name");
						String schedule = name + "さんの誕生日";
						sb.append("<p>");
						sb.append(schedule);
						sb.append("</p>");
					}

					rs.close();
					pstmt.close();
					if (isLeapYear(year)==false && (month + 1) == 2 && calendarDay[i] == 28) {
						try {
							String sql2 = "SELECT * FROM meibo WHERE user_nr = ? and birthday like ?";
							PreparedStatement pstmt1 = conn.prepareStatement(sql2);

							// For February 29th
							String startDateStr1 = "%02-29";
							pstmt1.setInt(1, userid);
							pstmt1.setString(2, startDateStr1);

							ResultSet rs1 = pstmt1.executeQuery();

							while (rs1.next()) {
								String name = rs1.getString("name");
								String schedule = name + "さんの誕生日(2/29生まれ)";
								sb.append("<p>");
								sb.append(schedule);
								sb.append("</p>");
							}

							rs1.close();
							pstmt1.close();
						} catch (SQLException e) {
							log("SQLException:" + e.getMessage());
						}
					}
				} catch (SQLException e) {
					log("SQLException:" + e.getMessage());
				}

				try {
					String sql = "SELECT * FROM schedule WHERE user_nr = ? and scheduledate = ? ORDER BY starttime";
					PreparedStatement pstmt = conn.prepareStatement(sql);

					String startDateStr = year + "-" + (month + 1) + "-" + calendarDay[i];
					pstmt.setInt(1, userid);
					pstmt.setString(2, startDateStr);

					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						int id = rs.getInt("id");
						String starttime = rs.getString("starttime");
						String endtime = rs.getString("endtime");
						String schedule = rs.getString("schedule");

						if (starttime == null || endtime == null) {
							sb.append("* ");
						} else {
							sb.append(starttime.substring(0, 5));
							sb.append("-");
							sb.append(endtime.substring(0, 5));
							sb.append(" ");
						}
						sb.append("<a href=\"ScheduleView?ID=");
						sb.append(id);
						sb.append("\">");
						sb.append(schedule);
						sb.append("</a><br>");
					}

					rs.close();
					pstmt.close();

				} catch (SQLException e) {
					log("SQLException:" + e.getMessage());
				}

				sb.append("</span>");

				sb.append("</td>");
			}
			sb.append("</td>");
		}

		sb.append("</tr>");
		sb.append("</main>");

		return (new String(sb));

	}

	protected int setDateArray(int year, int month, int day, int[] calendarDay, int count) {
		Calendar calendar = Calendar.getInstance();
		/* 今月が何曜日から開始されているか確認する */
		calendar.set(year, month, 1);
		int startWeek = calendar.get(Calendar.DAY_OF_WEEK);

		/* 先月が何日までだったかを確認する */
		calendar.set(year, month, 0);
		int beforeMonthlastDay = calendar.get(Calendar.DATE);

		/* 今月が何日までかを確認する */
		calendar.set(year, month + 1, 0);
		int thisMonthlastDay = calendar.get(Calendar.DATE);

		/* 先月分の日付を格納する */
		for (int i = startWeek - 2; i >= 0; i--) {
			calendarDay[count++] = beforeMonthlastDay - i + 35;
		}

		/* 今月分の日付を格納する */
		for (int i = 1; i <= thisMonthlastDay; i++) {
			calendarDay[count++] = i;
		}

		/* 翌月分の日付を格納する */
		int nextMonthDay = 1;
		while (count % 7 != 0) {
			calendarDay[count++] = 35 + nextMonthDay++;
		}

		return count;
	}
	//前月翌月リンクを作成
	protected String createMonthLink(int year, int month) {
		StringBuffer sb = new StringBuffer();

		sb.append("<p class=\"monthNext\">");

		sb.append("<a href=\"MonthView?YEAR=");
		sb.append(year);
		sb.append("&MONTH=");
		sb.append(month - 1);
		sb.append("\"><span class=\"small\">前月</span></a>  ");

		sb.append(year);
		sb.append("年");
		sb.append(month + 1);
		sb.append("月  ");

		sb.append("<a href=\"MonthView?YEAR=");
		sb.append(year);
		sb.append("&MONTH=");
		sb.append(month + 1);
		sb.append("\"><span class=\"small\">翌月</span></a>");

		sb.append("</p>");

		return (new String(sb));
	}

//	うるう年か判断↓
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

}