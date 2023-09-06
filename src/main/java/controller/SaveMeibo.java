package controller;



import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.InsertMeiboBL;
import model.MeiboDTO;
import model.UserInfoDto;

@WebServlet("/SaveMeibo")
@MultipartConfig
public class SaveMeibo extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	
		request.setCharacterEncoding("UTF-8");
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");
		System.out.println("セッション後");
		String userid =   userInfoOnSession.getUserId();
		
		String name = request.getParameter("NAME");
		String yomi = request.getParameter("YOMI");
		String birthday = request.getParameter("BIRTHDAY");
		int sex = Integer.parseInt(request.getParameter("SEX"));
		String bunrui = request.getParameter("BUNRUI");
		
		int relationship = Integer.parseInt(request.getParameter("RELATIONSHIP"));
		String memo = request.getParameter("MEMO");
		
		
		Part filePart= request.getPart("IMAGE");
		InputStream fileContext=filePart.getInputStream();
		byte[] image=fileContext.readAllBytes();
		
		
		MeiboDTO dto = new MeiboDTO();
		dto.setUserId( userid );
		dto.setName( name );
		dto.setYomi( yomi );
		dto.setBirthday( birthday );
		dto.setSex( sex );
		dto.setBunrui( bunrui );
		dto.setRelationship( relationship );
		dto.setMemo( memo ); 
		dto.setImageData(image);
		InsertMeiboBL logic = new InsertMeiboBL();
		boolean successInsert = logic.executeInsertMeibo(dto);
		System.out.println("最後");
		if(successInsert) {
			
			response.sendRedirect("html/finish.html");
		} else {
			
			response.sendRedirect("html/error.html");
		}
		
	}

}