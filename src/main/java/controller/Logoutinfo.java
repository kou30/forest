/**
 * Filename: Logoutinfo.java
 *
 * Description:
 * このクラスは,doGetリクエストでセッションを削除し、logout.jspにフォワード機能を提供するためのものです。
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

@WebServlet("/Logoutinfo")
public class Logoutinfo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession();
		session.invalidate();	//invalidate メソッドを使用してセッションを無効にします。これによりユーザーのセッションが終了し、ログアウトが実行されます。
		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/logout.jsp");
		dispatch.forward(request, response);
	}

}
