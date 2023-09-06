package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDto;

/**
 * Servlet implementation class MeiboEntry
 */
@WebServlet("/MeiboEntry")
public class MeiboEntry extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");
	 	
	 	if (userInfoOnSession != null) {
			request.getRequestDispatcher("/WEB-INF/view/MeiboEntry.jsp").forward(request, response);
	 	}else {
	 		response.sendRedirect("Logininfo");
	 	}
	}

}
