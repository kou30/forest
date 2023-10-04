/**
 * Filename: ExcuteDeleteMeibo.java
 *
 * Description:
 * このクラスは、ShowAllMeibo.jspにフォワードと「1件削除しました」を変数msgに入れ
 * JSPに渡すためリクエストスコープで格納する機能を提供するためのものです。
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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MeiboDAO;
import model.UserInfoDto;

@WebServlet("/ExecuteDeleteMeibo")
public class ExecuteDeleteMeibo extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			int id = Integer.parseInt(request.getParameter("MEIBO_ID"));
			MeiboDAO dao = new MeiboDAO();
			dao.deleteOne(id);
			request.setAttribute("msg", "1件削除しました");

			RequestDispatcher rd = request.getRequestDispatcher("/ShowAllMeibo");
			rd.forward(request, response);
		} else {
			response.sendRedirect("Login");
		}

	}
}