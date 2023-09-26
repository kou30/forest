package schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InsertMeiboBL;
import model.MeiboDTO;
import model.UserInfoDto;

public class BirthdayCalendarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

        if (userInfoOnSession != null) {
            // ログインユーザーの user_nr を取得
            int user_nr = userInfoOnSession.getUser_nr();

            // Meiboテーブルから誕生日が一致するデータを取得
            List<MeiboDTO> birthdays = InsertMeiboBL.getBirthdayList(user_nr);

            // カレンダーに誕生日イベントを表示
            out.println("<html><head><title>Birthday Calendar</title></head><body>");
            out.println("<h1>Birthday Calendar</h1>");

            for (MeiboDTO birthday : birthdays) {
                Date birthdayDate = birthday.getBirthday();
                Calendar birthdayCalendar = Calendar.getInstance();
                birthdayCalendar.setTime(birthdayDate);

                int birthMonth = birthdayCalendar.get(Calendar.MONTH) + 1; // 月は0から始まるため+1
                int birthDay = birthdayCalendar.get(Calendar.DAY_OF_MONTH);
                String name = birthday.getName();

                // 現在の年を取得
                Calendar today = Calendar.getInstance();
                int currentYear = today.get(Calendar.YEAR);

                // カレンダーに誕生日イベントを表示
                out.println("<p>" + currentYear + " 年 " + birthMonth + " 月 " + birthDay + " 日: " + name + " さんの誕生日</p>");
            }

            out.println("</body></html>");
        } else {
            response.sendRedirect("Logininfo");
        }
    }
}
