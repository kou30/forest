/**
 * Filename: Detail.java
 *
 * Description:
 * このクラスは、detail.jspにフォワードとMEIBO_IDを使いデータベースから返された情報をJSPに渡すため
 * リクエストスコープで格納する機能を提供するためのものです。
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

import model.DetailBL;
import model.MeiboDTO;
import model.ShinamonoDTO;
import model.UserInfoDto;

@WebServlet("/Detail")
public class Detail extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			int id = Integer.parseInt(request.getParameter("MEIBO_ID"));
			
			//ビジネスロジックインスタンスを作成
			DetailBL logic = new DetailBL();
			
			//名簿IDを引数にとり品物の情報を取得、リクエストに設定
			List<ShinamonoDTO> Slist=new ArrayList<ShinamonoDTO>();
			Slist =  logic.DetailShinamono(id);
			request.setAttribute("Slist", Slist);
			
			//名簿IDを引数にとり名簿の情報を取得、リクエストに設定
			MeiboDTO Mlist = new MeiboDTO();
			Mlist = logic.DetailMeiboSelect(id);
			request.setAttribute("Mlist", Mlist);
			
			int Mnr=Mlist.getUser_nr();
			int Unr=userInfoOnSession.getUser_nr();
			if(Mnr!=Unr) {
				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/mainpage.jsp");
				dispatch.forward(request, response);
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/detail.jsp");
			dispatch.forward(request, response);

		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatch.forward(request, response);
		}
	}

}
