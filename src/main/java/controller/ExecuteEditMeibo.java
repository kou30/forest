package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.ExecuteSelectMeiboBL;
import model.MeiboDTO;
import model.UpdateMeiboBL;
import model.UserInfoDto;

/**
 * Servlet implementation class ExecuteEditMeibo
 */
@MultipartConfig
@WebServlet("/ExecuteEditMeibo")
public class ExecuteEditMeibo extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {

			int id = Integer.parseInt(request.getParameter("MEIBO_ID"));

			ExecuteSelectMeiboBL logic = new ExecuteSelectMeiboBL();
			MeiboDTO meibo = logic.executeSelectMeibo(id);

			request.setAttribute("meibo", meibo);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/editmeibo.jsp");
			dispatch.forward(request, response);
		} else {
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
		int user_nr = userInfoOnSession.getUser_nr();
		int meibo_id = Integer.parseInt(request.getParameter("MEIBO_ID"));

		String name = request.getParameter("NAME");
		String yomi = request.getParameter("YOMI");
		String birthday = request.getParameter("BIRTHDAY");
		int sex = Integer.parseInt(request.getParameter("SEX"));
		String bunrui = request.getParameter("BUNRUI");

		int relationship = Integer.parseInt(request.getParameter("RELATIONSHIP"));
		String memo = request.getParameter("MEMO");

		Part filePart = request.getPart("IMAGE");
		InputStream fileContext = filePart.getInputStream();
		byte[] image = fileContext.readAllBytes();

		MeiboDTO dto = new MeiboDTO();
		dto.setMeibo_id(meibo_id);
		dto.setUser_nr(user_nr);
		dto.setName(name);
		dto.setYomi(yomi);
		dto.setBirthday(birthday);
		dto.setSex(sex);
		dto.setBunrui(bunrui);
		dto.setRelationship(relationship);
		dto.setMemo(memo);
		dto.setImageData(image);
		UpdateMeiboBL logic = new UpdateMeiboBL();
		boolean successInsert = logic.executeUpdateMeibo(dto);
		if (successInsert) {

			response.sendRedirect("html/finish.html");
		} else {

			response.sendRedirect("html/error.html");
		}

	}

}
