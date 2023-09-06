package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	    String dateString = request.getParameter("DATE"); // "date"はパラメータ名。適切に変更してください。
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // この形式は入力の形式に基づいています。必要に応じて変更してください。
        java.util.Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int bunrui = Integer.parseInt(request.getParameter("BUNRUI")); //リクエストパラメータ（NAME）
		int category = Integer.parseInt(request.getParameter("CATEGORY")); //リクエストパラメータ（NAME）
		int item = Integer.parseInt(request.getParameter("ITEM")); //リクエストパラメータ（NAME）
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
		dto.setRe_time((Date) date);
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
			response.sendRedirect("htmls/finish.html");

		} else {

			//DB登録に失敗した場合、エラー画面（error.html）を表示する
			response.sendRedirect("htmls/error.html");
		}
		
		
		
		
	}

}
