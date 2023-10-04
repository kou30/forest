/**
 * Filename:MeiboEntry.java
 *
 * Description:
 * このクラスは、doGetリクエストでユーザ情報（userInfoOnSession）がある場合meiboentry.jspにフォワードする。
 *情報がない場合はLogininfo.jspにフォワードする。
 *
 * doPostリクエストでgetParameterで取得した情報をMeiboDTOにセットInsertMeiboBLに送り
 * DB追加の可否で条件分岐がありできた場合finish.htmlにリダイレクト
 * できなかった場合error.htmlにリダイレクトする機能を提供するためのものです。
 *
 * Author: morioka shougo 
 * Creation Date: 2023-10-4
 * 
 * Copyright (C) 2023 KEG forest All rights reserved.
 *
 *
 */
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

import model.InsertMeiboBL;
import model.MeiboDTO;
import model.UserInfoDto;

/**
 * Servlet implementation class MeiboEntry
 */
@WebServlet("/MeiboEntry")
@MultipartConfig
public class MeiboEntry extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/meiboentry.jsp");
			dispatch.forward(request, response);

		} else {
			response.sendRedirect("Logininfo");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
		int user_nr = userInfoOnSession.getUser_nr();

		String name = request.getParameter("NAME");
		String yomi = request.getParameter("YOMI");
		String birthday = request.getParameter("BIRTHDAY");
		if ("".equals(birthday)) {
			birthday = null;
		}
		
		int sex = Integer.parseInt(request.getParameter("SEX"));
		String bunrui = request.getParameter("BUNRUI");
		int relationship = Integer.parseInt(request.getParameter("RELATIONSHIP"));
		String memo = request.getParameter("MEMO");

		Part filePart = request.getPart("IMAGE");
		InputStream fileContent = filePart.getInputStream();
		byte[] image = null;

		if (fileContent.available() > 0) {
			image = fileContent.readAllBytes();
		}

		MeiboDTO dto = new MeiboDTO();
		dto.setUser_nr(user_nr);
		dto.setName(name);
		dto.setYomi(yomi);
		dto.setBirthday(birthday);
		dto.setSex(sex);
		dto.setBunrui(bunrui);
		dto.setRelationship(relationship);
		dto.setMemo(memo);
		dto.setImageData(image);
		InsertMeiboBL logic = new InsertMeiboBL();
		boolean successInsert = logic.executeInsertMeibo(dto);
		if (successInsert) {
			response.sendRedirect("html/finish.html");
		} else {

			response.sendRedirect("html/error.html");
		}

	}

}
