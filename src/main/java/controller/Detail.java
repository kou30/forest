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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			int id = Integer.parseInt(request.getParameter("MEIBO_ID"));
			
			DetailBL logic = new DetailBL();
			
			List<ShinamonoDTO> Slist=new ArrayList<ShinamonoDTO>();
			Slist =  logic.DetailShinamono(id);
			request.setAttribute("Slist", Slist);
			
			MeiboDTO Mlist = new MeiboDTO();
			Mlist = logic.DetailMeiboSelect(id);
			request.setAttribute("Mlist", Mlist);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/detail.jsp");
			dispatch.forward(request, response);

		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatch.forward(request, response);
		}
	}

}
