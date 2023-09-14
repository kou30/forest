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

/**
 * Servlet implementation class ExecuteEdit
 */
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

	    String date = request.getParameter("DATE"); // "date"はパラメータ名。適切に変更してください。
		if ("".equals(date)) {
			date = null;
		}
		int bunrui = Integer.parseInt(request.getParameter("BUNRUI")); //リクエストパラメータ（NAME）
		int category = Integer.parseInt(request.getParameter("CATEGORY")); //リクエストパラメータ（NAME）
		int item = Integer.parseInt(request.getParameter("ITEM")); //リクエストパラメータ（NAME）
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
			//DB登録に成功した場合、回答完了画面（finish.html）を表示する
			response.sendRedirect("html/finish.html");

		} else {

			//DB登録に失敗した場合、エラー画面（error.html）を表示する
			response.sendRedirect("html/error.html");
		}
		
		
		
		
	}

}
