package jp.co.flm.market.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Member;

public class B0201CheckMemberAction {
    //pageを初期化
    String page = null;

    public void checkSession(HttpServletRequest req) throws MarketSystemException  {
        HttpSession session = req.getSession(false);
      //セッションを取得（ない場合新規作成する）
        if(session==null) {
        req.getSession(true);
    }
        if(session==null) {
         // セッションの取得に失敗した場合、例外を投げる。
            throw new MarketSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
        }
    }
        public String validate(HttpServletRequest req) {

            // メッセージ格納リストを作成する。
            ArrayList<String> errorMessageList = new ArrayList<String>();

           //フォームで入力された「名前」 「性別」「住所」「電話番号」「パスワード」を取得する。
            String membername = req.getParameter("membername");
            String gender = req.getParameter("gender");
            String address = req.getParameter("address");
            String phone = req.getParameter("phone");
            String password = req.getParameter("password");

            //入力値を確認する（空チェック）
            if (membername.length() == 0) {
                errorMessageList.add("名前は入力必須項目です。");
            }
            if (gender.length() == 0) {
                errorMessageList.add("性別は入力必須項目です。");
            }
            if (address.length() == 0) {
                errorMessageList.add("住所は入力必須項目です。");
            }
            if (phone.length() == 0) {
                errorMessageList.add("電話番号は入力必須項目です。");
            }
            if (password.length() == 0) {
                errorMessageList.add("パスワードは入力必須項目です。");
            }

            //入力値を確認する（文字数が条件に当てはまっているかどうか）
            if(membername.length() >41) {
                errorMessageList.add("名前は40字以内で入力してください。");
            }
            if(address.length() >81) {
                errorMessageList.add("住所は80字以内で入力してください。");
            }
            if(phone.length() >14) {
                errorMessageList.add("電話番号は13字以内で入力してください。");
            }
            if(password.length() <3 && password.length()>9) {
                errorMessageList.add("パスワードは4文字以上8文字以内で入力してください。");
            }

            // 入力エラーが発生していたかを確認する。
            if (errorMessageList.size() != 0) {
                req.setAttribute("errorMessageList", errorMessageList);
                page = "member-register-view.jsp";
            }
        return page;
    }

    public String execute(HttpServletRequest req) {
        try {
        // セッションの確認
        checkSession(req);

        page = validate(req);

        if (page == null) {   //pageがnullの時はエラー発生していない状態
              //フォームで入力された「名前」 「性別」「住所」「電話番号」「パスワード」を取得する。
                String membername = req.getParameter("membername");
                String gender = req.getParameter("gender");
                String address = req.getParameter("address");
                String phone = req.getParameter("phone");
                String password = req.getParameter("password");

             // セッションを取得する。
                HttpSession session = req.getSession(false);

                // 会員情報をセッションへ格納する。
                session.setAttribute("memberId", membername);
                session.setAttribute("memberId", gender);
                session.setAttribute("memberId", address);
                session.setAttribute("memberId", phone);
                session.setAttribute("memberId", password);

                //確認画面に遷移する
                page = "member-register-confirm.jsp";

        }

            }catch (MarketSystemException e) {
                // エラーメッセージを取得する。
                String errorMessage = e.getMessage();

                // リクエストスコープへエラーメッセージを格納する。
                ArrayList<String> errorMessageList = new ArrayList<String>();
                errorMessageList.add(errorMessage);
                req.setAttribute("errorMessageList", errorMessageList);

                page = "error.jsp";
            }

        return page;
        }
   }
