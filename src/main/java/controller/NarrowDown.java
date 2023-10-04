/**
 * Filename: Detail.java
 *
 * Description:
 * このクラスは、doPostでユーザ情報（userInfoOnSession）がある場合、
 * getParameterで取得した情報とuserInfoOnSessionの中にある
 * User_nrをgetして変数int型のidに格納。NarrowDownBL.javaに送る。
 * DBから抽出した情報が返ってきて変数yearlistに格納し、リクエストスコープにセットする。
 *情報がない場合はLogininfo.jspにフォワードする。機能を提供するためのものです。
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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.NarrowDownBL;
import model.ShinamonoDTO;
import model.UserInfoDto;

/**
 * Servlet implementation class NarrowDown
 */
@WebServlet("/NarrowDown")
public class NarrowDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NarrowDown() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
//			
			String  selectedOption= request.getParameter("selectedOption");
			String  write= request.getParameter("write");
			
			
			int id = userInfoOnSession.getUser_nr(); 
						NarrowDownBL logic = new NarrowDownBL();
			List<ShinamonoDTO> yearlist=new ArrayList<ShinamonoDTO>();
			yearlist =  logic. NarrowDownShinamono(selectedOption,write,id);
			request.setAttribute("yearlist", yearlist);
			System.out.println("-------------------------------------------------");
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/narrowdown.jsp");
			dispatch.forward(request, response);
		}
	}
}


