package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); //文字コードをUTF-8で設定
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/signup.jsp");
		dispatch.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("USER_ID");
		String userName = request.getParameter("USER_NAME");
		String password = request.getParameter("PASSWORD");

		String hashedPassword = null;
		try {
			hashedPassword = hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		
		UserInfoDto dto = new UserInfoDto();
		dto.setUserId(id);
		dto.setUserName(userName);
		dto.setPassWord(hashedPassword);

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
	private String hashPassword(String password) throws NoSuchAlgorithmException {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

	    StringBuilder hexString = new StringBuilder();
	    for (byte hashByte : hashBytes) {
	        String hex = Integer.toHexString(0xff & hashByte);
	        if (hex.length() == 1)
	            hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
