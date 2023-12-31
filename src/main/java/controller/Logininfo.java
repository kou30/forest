/**
 * Filename: Logininfo.java
 *
 * Description:
 * このクラスは、dopostリクエストでgetParameterで取得した情報をUserInfoDtoに格納して
 * UserInfoDaoに送りデータベースからデータ抽出の可否で条件分岐し
 * できた場合ユーザデータをセッションスコープにセットしてMain.jspにフォワード、
 * できなかった場合Logininfo.jspにリダイレクトする。
 *
 *パスワードをハッシュ化してセキュアに保存できます。
 *ハッシュ化されたパスワードは元のパスワードから逆引き不可能で、
 *セキュリティを向上させる機能を提供するためのものです。

 * Author: morioka shougo 
 * Creation Date: 2023-10-4
 * 
 * Copyright (C) 2023 KEG forest All rights reserved.
 *
 *
 */
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

		response.setContentType("text/html;charset=UTF-8");
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
				hashedPassword = hashPassword(passWord);	//SHA256を使いハッシュ化
															//パスワードのセキュリティを強化するため
			} catch (NoSuchAlgorithmException e) {		//アルゴリズムが利用不可の場合にスローされる例外
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
				//エラーメッセージを送る
				request.setAttribute("error_msg", "IDまたはpasswordが違います。");
				RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/view/login.jsp");
	            rd.forward(request, response);
			}
		}
	}
	private String hashPassword(String password) throws NoSuchAlgorithmException {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");		//MessageDigest クラスを使用して、SHA-256アルゴリズムのインスタンスを取得
	    byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));	//UTF-8エンコーディングでバイト配列としてパスワードをハッシュ化

	    StringBuilder hexString = new StringBuilder();
	    for (byte hashByte : hashBytes) {
	        String hex = Integer.toHexString(0xff & hashByte);
	        if (hex.length() == 1)
	            hexString.append('0');
	        hexString.append(hex);
	    }//ハッシュ化されたバイト配列を16進数の文字列に変換。このループは、各バイトを16進数の文字列に変換し、必要に応じて先頭に0を付け加えて、hexString に追加
	    return hexString.toString();
	}
}
