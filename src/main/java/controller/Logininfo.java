package controller;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDao;
import model.UserInfoDto;


@WebServlet("/Logininfo")
public class Logininfo extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		dispatch.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態によって表示画面を振り分ける
		// ※ログイン状態はセッション上からユーザーデータを取得できたか否かで判断
		//    ユーザーデータを取得できた　　　→既にログインされている
		//    ユーザーデータを取得できなかった→まだログインされていない
		if (userInfoOnSession != null) {
			//ログイン済：ホーム画面に転送
			response.sendRedirect("MainPage");
		} else {
			//未ログイン：ログイン処理を実施

			//リクエストパラメータからユーザー入力値を取得
			String userId   = request.getParameter("USER_ID");      //リクエストパラメータ（USER_ID）
			String passWord = request.getParameter("PASSWORD");     //リクエストパラメータ（PASSWORD）
			String hashedPassword = null;
			try {
				hashedPassword = hashPassword(passWord);
				System.out.println(hashedPassword);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			//「user_info」テーブルからユーザー入力値と合致するユーザーデータ（UserInfoDto型）を抽出
			// ※合致するデータがなかった場合、各フィールドがnullのDTOを得る
			UserInfoDao logic = new UserInfoDao();
			UserInfoDto   dto   = logic.executeSelectUserInfo(userId, hashedPassword);

			//ユーザーデータの抽出成功/失敗に応じて表示させる画面を振り分ける
			if (dto.getUserId() != null) {
				//ユーザーデータの抽出に成功：ログインOKとしてセッションにユーザーデータをセット＆ホーム画面へ

				//DBから抽出したユーザデータをセッションにセット
				session.setAttribute("LOGIN_INFO", dto);

				//ホーム画面へ転送
				response.sendRedirect("MainPage");

			} else {
				//ユーザーデータの抽出に失敗：ログインNGとしてログイン画面へ転送
				request.setAttribute("error_msg", "IDまたはpasswordが違います。");
				response.sendRedirect("Logininfo");
				

			}
		}
	}
	private String hashPassword(String password) throws NoSuchAlgorithmException {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

	    StringBuilder hexString = new StringBuilder();
	    for (byte hashByte : hashBytes) {
	        String hex = Integer.toHexString(0xff & hashByte);
	        if (hex.length() == 1)
	            hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
