/**
 * Filename: ExecuteEditshinamono.java
 *
 * Description:
 * このクラスは、dogetリクエストでeditshinamono.jspにフォワードと
 * IDを取得しExecuteSelectShinamonoBLに送りデータベースから返された情報を
 * JSPに渡すため変数shinamonoに入れリクエストスコープに格納する。
 * 
 * doPostリクエストでgetParameterで取得した情報をShinamonoDTOに格納して
 * UpdateShinamonoBLに送りデータベースに情報が更新された場合
 * finish.htmlにリダイレクト、できなかった場合error.htmlにリダイレクトする機能を提供するためのものです。
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

import model.ExecuteSelectShinamonoBL;
import model.ShinamonoDTO;
import model.UpdateShinamonoBL;
import model.UserInfoDto;


@WebServlet("/ExecuteEditShinamono")
public class ExecuteEditShinamono extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {

			int id = Integer.parseInt(request.getParameter("ID"));

			ExecuteSelectShinamonoBL logic = new ExecuteSelectShinamonoBL();
			ShinamonoDTO shinamono = logic.executeSelectShinamono(id);

			request.setAttribute("shinamono", shinamono);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/editshinamono.jsp");
			dispatch.forward(request, response);
		} else {
			response.sendRedirect("Login");
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		int shinamono_id = Integer.parseInt(request.getParameter("SHINAMONO_ID"));

	    String date = request.getParameter("DATE"); 
		if ("".equals(date)) {
			date = null;
		}
		String str=request.getParameter("BUNRUI");
		int bunrui=0;
		if (str != null) {
		    try {
		        bunrui = Integer.parseInt(str);
		    } catch (NumberFormatException e) {
		    }
		}
		str=request.getParameter("CATEGORY");
		int category=0;
		if (str != null) {
		    try {
		        category = Integer.parseInt(str);
		    } catch (NumberFormatException e) {
		    }
		}
		str=request.getParameter("ITEM");
		int item=0;
		if (str != null) {
		    try {
		        item = Integer.parseInt(str);
		    } catch (NumberFormatException e) {
		    }
		}
		String shinamono_name = request.getParameter("SHINAMONONAME"); //リクエストパラメータ（NAME）

		int kingaku = Integer.parseInt(request.getParameter("KINGAKU")); //リクエストパラメータ（AGE）
		String memo = request.getParameter("MEMO"); //リクエストパラメータ（NAME）
		
		ShinamonoDTO dto = new ShinamonoDTO();
		dto.setShinamono_id(shinamono_id);
		dto.setRe_time(date);
		dto.setBunrui(bunrui);
		dto.setCategory(category);
		dto.setItem(item);
		dto.setShinamono_name(shinamono_name);
		dto.setShinamono_kingaku(kingaku);
		dto.setMemo(memo);

		UpdateShinamonoBL logic = new UpdateShinamonoBL();
		boolean succesInsert = logic.executeUpdateShinamono(dto);

		if (succesInsert) {
			
			response.sendRedirect("html/finish.html");

		} else {

			
			response.sendRedirect("html/error.html");
		}
		
		
		
		
	}

}
