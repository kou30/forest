package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InsertShinamonoBL;
import model.ShinamonoDTO;
import model.UserInfoDto;


@WebServlet("/SaveShinamono")
public class SaveShinamono extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		int meibo_id=Integer.parseInt(request.getParameter("MEIBO_ID"));
		String aite_name = request.getParameter("AITENAME"); //リクエストパラメータ（NAME）
	    String date = request.getParameter("DATE"); // "date"はパラメータ名。適切に変更してください。
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
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");
		int user_nr =   userInfoOnSession.getUser_nr();	
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
			//DB登録に成功した場合、回答完了画面（finish.html）を表示する
			response.sendRedirect("html/finish.html");

		} else {

			//DB登録に失敗した場合、エラー画面（error.html）を表示する
			response.sendRedirect("html/error.html");
		}
		
		
		
		
	}

}
