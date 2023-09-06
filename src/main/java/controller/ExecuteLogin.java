package controller;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDao;
import model.UserInfoDto;


/**----------------------------------------------------------------------*
 *■■■ExecuteLoginクラス■■■
 *概要：サーブレット
 *詳細：「user_info」テーブルからユーザー入力値と合致するユーザーデータを抽出し、リダイレクトする。
 *　　　＜リダイレクト先＞合致データあり（ログインOK）：Homeサーブレット
 *　　　　　　　　　　　　合致データなし（ログインNG）：Loginサーブレット（前画面に戻る）
 *----------------------------------------------------------------------**/
@WebServlet("/ExecuteLogin")
public class ExecuteLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteLogin() {
		super();
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//	}

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
			response.sendRedirect("Mainsub");
		} else {
			//未ログイン：ログイン処理を実施

			//リクエストパラメータからユーザー入力値を取得
			String userId   = request.getParameter("USER_ID");      //リクエストパラメータ（USER_ID）
			String passWord = request.getParameter("PASSWORD");     //リクエストパラメータ（PASSWORD）

			//「user_info」テーブルからユーザー入力値と合致するユーザーデータ（UserInfoDto型）を抽出
			// ※合致するデータがなかった場合、各フィールドがnullのDTOを得る
			UserInfoDao logic = new UserInfoDao();
			UserInfoDto   dto   = logic.executeSelectUserInfo(userId, passWord);

			//ユーザーデータの抽出成功/失敗に応じて表示させる画面を振り分ける
			if (dto.getUserId() != null) {
				//ユーザーデータの抽出に成功：ログインOKとしてセッションにユーザーデータをセット＆ホーム画面へ

				//DBから抽出したユーザデータをセッションにセット
				session.setAttribute("LOGIN_INFO", dto);

				//ホーム画面へ転送
				response.sendRedirect("Mainsub");

			} else {
				//ユーザーデータの抽出に失敗：ログインNGとしてログイン画面へ転送
				request.setAttribute("error_msg", "IDまたはpasswordが違います。");
				response.sendRedirect("Logininfo");
				

			}
		}
	}
}
