package jp.co.flm.market.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Member;

public class B0201CheckMemberAction {

    // //セッションを取得（ない場合新規作成する）
    public void checkSession(HttpServletRequest req) {
        req.getSession(true);
    }

    public String validate(HttpServletRequest req) {
        String page = null;

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



        // 入力エラーが発生していたかを確認する。
        if (errorMessageList.size() != 0) {
            req.setAttribute("errorMessageList", errorMessageList);
            page = "member-register-confirm-view.jsp";
        }

        return page;
    }

    public String execute(HttpServletRequest req) {

        String page = null;

        checkSession(req);

        page = validate(req);

        if (page == null) {
            try {
              //フォームで入力された「名前」 「性別」「住所」「電話番号」「パスワード」を取得する。
                String membername = req.getParameter("membername");
                String gender = req.getParameter("gender");
                String address = req.getParameter("address");
                String phone = req.getParameter("phone");
                String password = req.getParameter("password");
            }

    }
        }
    }
