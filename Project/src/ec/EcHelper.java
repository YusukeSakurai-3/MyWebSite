package ec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import beans.ItemDataBeans;

public class EcHelper {
	    // ファイルをアップロードする場所
	    static final String UPLOAD_PAGE = "/Users/sakurai/git/MyWebSite/Project/WebContent/img";
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
		static final String BUY_PAGE = "/WEB-INF/jsp/buy.jsp";
		// 購入確認
		static final String BUY_CONFIRM_PAGE = "WEB-INF/jsp/buyconfirm.jsp";
		// 購入完了
		static final String BUY_RESULT_PAGE = "WEB-INF/jsp/buyresult.jsp";
		//ほしい物リストページ
		static final String  ITEM_GET_LIST_PAGE= "WEB-INF/jsp/itemgetlist.jsp";
		// ユーザー情報
		static final String USER_DETAIL_PAGE = "WEB-INF/jsp/userdetail.jsp";
		// ユーザー情報更新確認
		static final String USER_DATA_UPDATE_CONFIRM_PAGE = "/userdataupdateconfirm.jsp";
		// ユーザー情報更新完了
		static final String USER_UPDATE_PAGE = "WEB-INF/jsp/userupdate.jsp";
		// ユーザー購入履歴
		static final String USER_BUY_HISTORY_DETAIL_PAGE = "WEB-INF/jsp/userbuyhistorydetail.jsp";
		// ログイン
		static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
		//商品レビュー詳細ページ
		static final String USER_LIST_PAGE ="WEB-INF/jsp/userlist.jsp";
		//商品マスタ一覧ページ
		static final String ITEM_MASTER_PAGE ="WEB-INF/jsp/itemmaster.jsp";
		//商品マスタ詳細ページ
		static final String ITEM_MASTER_DETAIL_PAGE ="WEB-INF/jsp/itemmasterdetail.jsp";
		//商品マスタ新規登録ページ
		static final String ITEM_MASTER_CREATE_PAGE ="WEB-INF/jsp/itemmastercreate.jsp";
		//商品マスタ更新ページ
		static final String ITEM_MASTER_UPDATE_PAGE ="WEB-INF/jsp/itemmasterupdate.jsp";
		//商品マスタ削除ページ
		static final String ITEM_MASTER_DELETE_PAGE ="WEB-INF/jsp/itemmasterdelete.jsp";
		// ユーザー新規登録
		static final String USER_CREATE_PAGE = "WEB-INF/jsp/usercreate.jsp";
		// 新規登録入力内容確認
		static final String REGIST_CONFIRM_PAGE = "/registconfirm.jsp";
		// 新規登録完了
		static final String REGIST_RESULT_PAGE = "/registresult.jsp";
		//ランキングページ
		static final String RANKING_PAGE ="WEB-INF/jsp/ranking.jsp";
		//商品レビューページ
		static final String ITEM_REVIEW_PAGE ="WEB-INF/jsp/itemreview.jsp";
		//商品レビュー更新ページ
	    static final String ITEM_REVIEW_UPDATE_PAGE ="WEB-INF/jsp/itemreviewupdate.jsp";
		//商品レビュー一覧ページ
		static final String ITEM_REVIEW_LIST_PAGE ="WEB-INF/jsp/itemreviewlist.jsp";
		//商品レビュー詳細ページ
		static final String ITEM_REVIEW_DETAIL_PAGE ="WEB-INF/jsp/itemreviewdetail.jsp";


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
		 * 文字列の引数を符号付き 10 進数の整数型として構文解析します。
		 * 文字列が構文解析可能な int 値を含まない場合は、指定されたデフォルトの int 値を返します。
		 * @param value 構文解析対象の int 表現を含む String
		 * @param defaultValue デフォルト値
		 * @return 10 進数の引数で表される整数値
		 */
		public static int parseInt(String value, int defaultValue) {

		    try {
		        // intに変換して返す
		        return Integer.parseInt(value);
		    } catch ( NumberFormatException e ) {
		        // デフォルト値を返す
		        return defaultValue;
		  }
		}

	/**
    * 商品の合計金額を算出する
	*
    * @param items
    * @return total
     */
	public static int getTotalItemPrice(ArrayList<ItemDataBeans> items) {
		int total = 0;
		for (ItemDataBeans item : items) {
			total += item.getPrice();
		}
		return total;
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

	public static String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }


	/**
	 * 文字列がnullかどうかチェックする
	 *
	 * @param string
	 * @return str
	 */

	public static String nullCheck(String str) {
			if(str==null) {
				return "";
			}else {
				return str;
			}
		}

}
