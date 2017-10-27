package ec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

public class EcHelper {
	// 検索結果
		static final String SEARCH_RESULT_PAGE = "WEB-INF/jsp/itemsearchresult.jsp";
		// 商品ページ
		static final String ITEM_PAGE = "WEB-INF/jsp/item.jsp";
		// TOPページ
		static final String TOP_PAGE = "/WEB-INF/jsp/index.jsp";
		// エラーページ
		static final String ERROR_PAGE = "/error.jsp";
		// 買い物かごページ
		static final String CART_PAGE = "/WEB-INF/jsp/cart.jsp";
		// 購入
		static final String BUY_PAGE = "/buy.jsp";
		// 購入確認
		static final String BUY_CONFIRM_PAGE = "/buyconfirm.jsp";
		// 購入完了
		static final String BUY_RESULT_PAGE = "/buyresult.jsp";
		// ユーザー情報
		static final String USER_DETAIL_PAGE = "WEB-INF/jsp/userdetail.jsp";
		// ユーザー情報更新確認
		static final String USER_DATA_UPDATE_CONFIRM_PAGE = "/userdataupdateconfirm.jsp";
		// ユーザー情報更新完了
		static final String USER_UPDATE_PAGE = "WEB-INF/jsp/userupdate.jsp";
		// ユーザー購入履歴
		static final String USER_BUY_HISTORY_DETAIL_PAGE = "/userbuyhistorydetail.jsp";
		// ログイン
		static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
		// ログアウト
		static final String LOGOUT_PAGE = "/logout.jsp";
		// 新規登録
		static final String USER_CREATE_PAGE = "WEB-INF/jsp/usercreate.jsp";
		// 新規登録入力内容確認
		static final String REGIST_CONFIRM_PAGE = "/registconfirm.jsp";
		// 新規登録完了
		static final String REGIST_RESULT_PAGE = "/registresult.jsp";


		/**暗号メソッド
		 * @param  source ;
		 * @return 暗号化された文字列
		 */
		public static String toCode(String source) {


			//ハッシュ生成前にバイト配列に置き換える際のCharset
			Charset charset = StandardCharsets.UTF_8;
			//ハッシュアルゴリズム
			String algorithm = "MD5";

			//ハッシュ生成処理
			byte[] bytes;
			try {
				bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
				String result = DatatypeConverter.printHexBinary(bytes);
				return result;

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}
		}


	/**
	 * セッションから指定データを取得（削除も一緒に行う）
	 *
	 * @param session
	 * @param str
	 * @return
	 */
	public static Object cutSessionAttribute(HttpSession session, String str) {
		Object test = session.getAttribute(str);
		session.removeAttribute(str);

		return test;
	}

}
