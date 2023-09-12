package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ExecuteSelectShinamonoBL;
import model.ShinamonoDTO;
import model.UserInfoDto;

/**
 * Servlet implementation class ExecuteEdit
 */
@WebServlet("/ExecuteEdit")
public class ExecuteEdit extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {

			int id = Integer.parseInt(request.getParameter("ID"));

			ExecuteSelectShinamonoBL logic = new ExecuteSelectShinamonoBL();
			ShinamonoDTO shinamono = logic.executeSelectShinamono(id);

			System.out.println(shinamono.getAite_name());
			request.setAttribute("shinamono", shinamono);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/Edit.jsp");
			dispatch.forward(request, response);
		} else {
			response.sendRedirect("Login");
		}

	}

}
