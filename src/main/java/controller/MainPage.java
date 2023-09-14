package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDto;


	@WebServlet("/MainPage")
	public class MainPage extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

			if (userInfoOnSession != null) {
				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/mainpage.jsp");
				dispatch.forward(request, response);

			} else {
				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				dispatch.forward(request, response);
			}
		}
	}
