package controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SignupBL;
import model.UserInfoDto;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/ExecuteSignup")
public class ExecuteSignup extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("USER_ID");
		String userName = request.getParameter("USER_NAME");
		String password = request.getParameter("PASSWORD");

		UserInfoDto dto = new UserInfoDto();
		dto.setUserId(id);
		dto.setUserName(userName);
		dto.setPassWord(password);

		SignupBL logic = new SignupBL();
		boolean successInsert = false;
		try {
			successInsert = logic.executeSignup(dto);
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if (successInsert) {

			response.sendRedirect("html/finish.html");
		} else {

			response.sendRedirect("html/error.html");
		}

	}
}
