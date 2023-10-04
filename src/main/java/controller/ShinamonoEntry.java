/**
 * Filename:ShinamonoEntry.java
 *
 * Description:
 * このクラスは、doGetリクエストでユーザ情報（userInfoOnSession）がある場合ExecuteSelectMeiboBL
 * にgetParameterで取得したMEIBO_IDを送りDBから返ってきた情報をリクエストスコープにセットして
 * shinamono_entry.jspにフォワードする。
 *情報がない場合はLogininfo.jspにフォワードする。
 *
 * doPostリクエストでgetParameterで取得した情報をShinamonoDTOにセットInsertShinamonoBLに送り
 * DB追加の可否で条件分岐がありできた場合finish.htmlにリダイレクト
 * できなかった場合ポップアップメッセージを表示して、編集画面にとどまる機能を提供するためのものです。
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

import model.ExecuteSelectMeiboBL;
import model.InsertShinamonoBL;
import model.MeiboDTO;
import model.ShinamonoDTO;
import model.UserInfoDto;

@WebServlet("/ShinamonoEntry")
public class ShinamonoEntry extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			int meibo_id = Integer.parseInt(request.getParameter("MEIBO_ID"));
			ExecuteSelectMeiboBL logic = new ExecuteSelectMeiboBL();
			MeiboDTO meibo = logic.executeSelectMeibo(meibo_id);

			request.setAttribute("meibo", meibo);

			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/shinamono_entry.jsp");
			dispatch.forward(request, response);

		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatch.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int meibo_id = Integer.parseInt(request.getParameter("MEIBO_ID"));
		String aite_name = request.getParameter("AITENAME"); //リクエストパラメータ（NAME）
		String date = request.getParameter("DATE"); // "date"はパラメータ名。適切に変更してください。
		if ("".equals(date)) {
			date = null;
		}
		String str = request.getParameter("BUNRUI");
		int bunrui = 0;
		if (str != null) {
			try {
				bunrui = Integer.parseInt(str);
			} catch (NumberFormatException e) {
			}
		}
		str = request.getParameter("CATEGORY");
		int category = 0;
		if (str != null) {
			try {
				category = Integer.parseInt(str);
			} catch (NumberFormatException e) {
			}
		}
		str = request.getParameter("ITEM");
		int item = 0;
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
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");
		int user_nr = userInfoOnSession.getUser_nr();
		dto.setUser_nr(user_nr);
		dto.setMeibo_id(meibo_id);
		dto.setAite_name(aite_name);
		dto.setRe_time(date);
		dto.setBunrui(bunrui);
		dto.setCategory(category);
		dto.setItem(item);
		dto.setShinamono_name(shinamono_name);
		dto.setShinamono_kingaku(kingaku);
		dto.setMemo(memo);

		InsertShinamonoBL logic = new InsertShinamonoBL();
		boolean succesInsert = logic.executeInsertShinamono(dto);

		if (succesInsert) {
		    // DB登録に成功した場合、回答完了画面（finish.html）にリダイレクト
		    response.sendRedirect("html/finish.html");
		} else {
		    // DB登録に失敗した場合、エラーメッセージを設定して、入力画面にフォワード
		    String errorMessage = "データベースへの登録に失敗しました。"; // エラーメッセージを設定

		    // エラーメッセージをリクエスト属性にセット
		    request.setAttribute("errorMessage", errorMessage);

		    // 入力画面にフォワード
		    RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/shinamono_entry.jsp");
		    dispatch.forward(request, response);
		
	}

	}
}