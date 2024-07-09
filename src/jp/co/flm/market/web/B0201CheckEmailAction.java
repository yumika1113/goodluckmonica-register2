package jp.co.flm.market.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Member;


public class B0201CheckEmailAction {

    //セッションを取得（ない場合新規作成する）
    public void checkSession(HttpServletRequest req) {
        req.getSession(true);
    }

    public String validate(HttpServletRequest req) {
        String page = null;

        // メッセージ格納リストを作成する。
        ArrayList<String> errorMessageList = new ArrayList<String>();

        //フォームで入力されたメールアドレスを取得する。
        String memberId = req.getParameter("memberId");

        //入力値を確認する（空チェック）
        if(memberId.length() == 0) {
            errorMessageList.add("メールアドレスは入力必須項目です。");
        }
        //入力値を確認する（不正な文字が含まれているかどうか）
        //入力値を確認する（＠マークが抜けていないかどうか）


        //入力エラーが発生していたかを確認する。
        if (errorMessageList.size() != 0) {
            req.setAttribute("errorMessageList", errorMessageList);
            page = "member-register-view.jsp";
        }

        return page;
    }

    //アクションを実行する
    public String execute(HttpServletRequest req) {

        String page = null;

        checkSession(req);

        page = validate(req);

        if(page == null) {
           try {
               //フォームで入力されたメールアドレスを取得する。
               String memberId = req.getParameter("memberId");

               //セッションを取得する。
               HttpSession session = req.getSession(false);

               //メールアドレスをセッションへ格納する。
               session.setAttribute("Member", memberId);

               page="member-register-view.jsp";
           }catch(MarketBusinessException e) {
               //エラーメッセージを取得する。
               String errorMessage = e.getMessage();

               //リクエストスコープへエラーメッセージを格納する。
               ArrayList<String> errorMessageList = new ArrayList<String>();
               errorMessageList.add(errorMessage);
               req.setAttribute("errorMessageList", errorMessageList);

               page = "error.jsp";
           }
        }

               return page;
    }


}
