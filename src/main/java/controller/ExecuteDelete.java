package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ShinamonoDAO;
import model.UserInfoDto;

@WebServlet("/ExecuteDelete")
public class ExecuteDelete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			int id = Integer.parseInt(request.getParameter("ID"));
			System.out.println(id);
			ShinamonoDAO dao = new ShinamonoDAO();
			dao.deleteOne(id);
			request.setAttribute("msg", "1件削除しました");

			RequestDispatcher rd = request.getRequestDispatcher("/ShowAllShinamono");
			rd.forward(request, response);
		} else {
			response.sendRedirect("Login");
		}
	}
}
