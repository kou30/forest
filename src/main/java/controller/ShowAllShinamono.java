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

import model.ShinamonoDTO;
import model.ShowAllShinamonoBL;

/**
 * Servlet implementation class ShowAllSurvey
 */
@WebServlet("/ShowAllShinamono")
public class ShowAllShinamono extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		List<ShinamonoDTO> list  = new ArrayList<ShinamonoDTO>();
		ShowAllShinamonoBL logic = new ShowAllShinamonoBL();
		list = logic.executeSelectShinamono();
		
		request.setAttribute("list", list);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/showallshinamono.jsp");
		dispatch.forward(request, response);
	}
}
