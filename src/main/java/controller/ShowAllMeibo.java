package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MeiboDTO;
import model.ShowAllMeiboBL;
import model.UserInfoDto;

/**
 * Servlet implementation class ShowallMeibo
 */
@WebServlet("/ShowAllMeibo")
public class ShowAllMeibo extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			int user_nr=   userInfoOnSession.getUser_nr();
			List<MeiboDTO> list = new ArrayList<MeiboDTO>();
			ShowAllMeiboBL logic = new ShowAllMeiboBL();
			list = logic.executeSelectMeibo(user_nr);
			request.setAttribute("MEIBO", list);

			request.getRequestDispatcher("/WEB-INF/view/showallmeibo.jsp").forward(request, response);
		} else {
			response.sendRedirect("Logininfo");
		}
	}
}
