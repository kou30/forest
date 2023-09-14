package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ExecuteSelectMeiboBL;
import model.MeiboDTO;
import model.UserInfoDto;


@WebServlet("/ShinamonoEntry")
public class ShinamonoEntry extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			int meibo_id=Integer.parseInt(request.getParameter("MEIBO_ID"));
			ExecuteSelectMeiboBL logic = new ExecuteSelectMeiboBL();
			MeiboDTO meibo = logic.executeSelectMeibo(meibo_id);

			request.setAttribute("meibo", meibo);
			
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/shinamono_entry.jsp");
			dispatch.forward(request, response);

		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatch.forward(request, response);
		}
	}
}
