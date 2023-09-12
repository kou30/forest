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

import model.ShinamonoDTO;
import model.ShowAllShinamonoBL;
import model.UserInfoDto;

/**
 * Servlet implementation class ShowAllSurvey
 */
@WebServlet("/ShowAllShinamono")
public class ShowAllShinamono extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		
		

		if (userInfoOnSession != null) {
			int user_nr=   userInfoOnSession.getUser_nr();
			List<ShinamonoDTO> list  = new ArrayList<ShinamonoDTO>();
			ShowAllShinamonoBL logic = new ShowAllShinamonoBL();
			list = logic.executeSelectShinamono(user_nr);
			request.setAttribute("list", list);

			request.getRequestDispatcher("/WEB-INF/view/showallshinamono.jsp").forward(request, response);
		} else {
			response.sendRedirect("Logininfo");
		}
	}
}
