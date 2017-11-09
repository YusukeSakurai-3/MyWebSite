package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import base.DBManager;
import beans.SearchDataBeans;
import beans.UserDataBeans;
import ec.EcHelper;

public class UserDAO {
	// インスタンスオブジェクトを返却させてコードの簡略化
	public static UserDAO getInstance() {
		return new UserDAO();
	}

	/**
	 * データの挿入処理を行う。現在時刻は挿入直前に生成
	 *
	 * @param user
	 *            対応したデータを保持しているJavaBeans
	 * @throws SQLException
	 *             呼び出し元にcatchさせるためにスロー
	 */
	public boolean insertUser(UserDataBeans udb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("INSERT INTO t_user(name,birth_date,address,login_id,is_open,login_password,create_date,update_date) VALUES(?,?,?,?,?,?,?,?)");
			st.setString(1, udb.getName());
			st.setString(4, udb.getLoginId());
			st.setString(3, udb.getAddress());
			st.setBoolean(5,false);
			String strdate = new SimpleDateFormat("yyyy-MM-dd").format(udb.getBirthDate());
			st.setString(2,strdate);
			System.out.println(udb.getBirthDate());
			st.setString(6, EcHelper.toCode(udb.getPassword()));
			st.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			st.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();
			System.out.println("inserting user has been completed");
			return true;
		} catch (SQLException e) {
			return false;
			//System.out.println(e.getMessage());
			//throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}



	/**
	 * ユーザーIDを取得
	 *
	 * @param loginId
	 *            ログインID
	 * @param password
	 *            パスワード
	 * @return int ログインIDとパスワードが正しい場合対象のユーザーID 正しくない||登録されていない場合0
	 * @throws SQLException
	 *             呼び出し元にスロー
	 */
	public static int getUserId(String loginId, String password) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM t_user WHERE login_id = ?");
			st.setString(1, loginId);

			ResultSet rs = st.executeQuery();

			int userId = 0;
			while (rs.next()) {
				if (EcHelper.toCode(password).equals(rs.getString("login_password"))) {
					userId = rs.getInt("id");
					System.out.println("login succeeded");
					break;
				}
			}

			System.out.println("searching userId by loginId has been completed");
			return userId;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * ユーザーIDからユーザー情報を取得する
	 *
	 * @param useId
	 *            ユーザーID
	 * @return udbList 引数から受け取った値に対応するデータを格納する
	 * @throws SQLException
	 *             呼び出し元にcatchさせるためスロー
	 */
	public static UserDataBeans getUserDataBeansByUserId(int userId) throws SQLException {
		UserDataBeans udb = new UserDataBeans();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT id,name,birth_date,is_open,login_id, address FROM t_user WHERE id =" + userId);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				udb.setId(rs.getInt("id"));
				udb.setName(rs.getString("name"));
				udb.setLoginId(rs.getString("login_id"));
				udb.setAddress(rs.getString("address"));
				udb.setBirthDate(rs.getDate("birth_date"));
				udb.setIs_open(rs.getBoolean("is_open"));

			}

			st.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		System.out.println("searching UserDataBeans by userId has been completed");
		return udb;
	}


	/**
	 * ユーザーIDから名前を取得する
	 *
	 * @param useId
	 *            ユーザーID
	 * @return userName
	 *            ユーザーIDに対する名前
	 *
	 * @throws SQLException
	 *             呼び出し元にcatchさせるためスロー
	 */
	public static String getUserName(int userId) throws SQLException {

		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT name FROM t_user WHERE id =" + userId);
			ResultSet rs = st.executeQuery();

			String userName = "";

			while (rs.next()) {
				userName = rs.getString("name");
				break;

			}

			st.close();
			System.out.println("searching UserDataBeans by userId has been completed");
			return userName;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * loginIdの重複チェック
	 *
	 * @param loginId
	 *            check対象のログインID
	 * @param userId
	 *            check対象から除外するuserID
	 * @return bool 重複している
	 * @throws SQLException
	 */
	public static boolean isOverlapLoginId(String loginId, int userId) throws SQLException {
		// 重複しているかどうか表す変数
		boolean isOverlap = false;
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBManager.getConnection();
			// 入力されたlogin_idが存在するか調べる
			st = con.prepareStatement("SELECT login_id FROM t_user WHERE login_id = ? AND id != ?");
			st.setString(1, loginId);
			st.setInt(2, userId);
			ResultSet rs = st.executeQuery();

			System.out.println("searching loginId by inputLoginId has been completed");

			if (rs.next()) {
				isOverlap = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		System.out.println("overlap check has been completed");
		return isOverlap;
	}

	/**
	 * ほしい物リストを公開しているユーザーを取得
	 * @return UserDataBeans
	 * @throws SQLException
	 */
	public static ArrayList<UserDataBeans> getUsersByisOpen(int pageNum,int pageMaxUserCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		String searchWord = "";
		try {
			int startUserNum = (pageNum - 1) * pageMaxUserCount;
			con = DBManager.getConnection();

			if (searchWord.length() == 0) {
				// 全検索
				st = con.prepareStatement("SELECT * FROM t_user WHERE is_open = 1 ORDER BY id ASC LIMIT ?,? ");
				st.setInt(1, startUserNum);
				st.setInt(2, pageMaxUserCount);
			} else {
				// ユーザー名検索
				searchWord="%"+searchWord+"%";
				st = con.prepareStatement("SELECT * FROM t_user WHERE name like   ?  ORDER BY id ASC LIMIT ?,? ");
				st.setString(1,searchWord);
				st.setInt(2, startUserNum);
				st.setInt(3, pageMaxUserCount);
			}

			ResultSet rs = st.executeQuery();
			ArrayList<UserDataBeans> userList = new ArrayList<UserDataBeans>();

			while (rs.next()) {
				UserDataBeans user = new UserDataBeans();
				/*if(rs.getInt("id")==1) {
					continue;
				}*/
				user.setId(rs.getInt("id"));
				user.setLoginId(rs.getString("login_id"));
				user.setName(rs.getString("name"));
				user.setBirthDate(rs.getDate("birth_date"));
				user.setAddress(rs.getString("address"));
				user.setIs_open(rs.getBoolean("is_open"));
				user.setCreateDate(rs.getDate("create_date"));
				user.setUpdateDate(rs.getDate("update_date"));
				userList.add(user);
			}
			System.out.println("get User by userName has been completed");
			return userList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * ユーザー詳細検索
	 * @param SerchDataBeans
	 * @param pageNum
	 * @param pageMaxUserCount
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<UserDataBeans> getUsersDetail(SearchDataBeans sdb, int pageNum, int pageMaxUserCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startUserNum = (pageNum - 1) * pageMaxUserCount;
			con = DBManager.getConnection();
			ResultSet rs;
			String searchWord = sdb.getName();
			String loginId = sdb.getLoginId();
			String startDate = sdb.getStartDate();
			String endDate = sdb.getEndDate();
			String or = " or ";
			String and = " and ";

			if (searchWord.length() == 0&&!loginId.equals("__noId")&&!startDate.equals("nodate")&&!endDate.equals("nodate")) {
				// 全検索
				st = con.prepareStatement("SELECT * FROM t_user ORDER BY id ASC LIMIT ?,? ");
				st.setInt(1, startUserNum);
				st.setInt(2, pageMaxUserCount);
				rs = st.executeQuery();
			} else {
				boolean flag = true;
				// 商品名検索
				String sql = "SELECT * FROM t_user WHERE name like ";

				String[] searchWords = searchWord.split("　", 0);
				for(String word:searchWords) {
				  word=" '%"+word+"%' ";
				  if(flag) {
					  sql += word;
					  flag = false;
				  }else {
					  sql = sql +" "+or+" name like " + word;
					  }
				}

				if(!loginId.equals("__noId")) {
					loginId = " '"+loginId+"' ";
					sql = sql + and + "login_id = "+loginId+" ";
				}

				if(!startDate.equals("nodate")) {
					startDate = "' "+startDate+" '";
				    sql = sql +and+" birth_date >= "+startDate+" ";
				    	}

				if(!endDate.equals("nodate")) {
					endDate = "' "+endDate+" '";
				    sql = sql+and+"birth_date <= "+endDate+" ";
				}


			sql += "LIMIT "+startUserNum+" , "+pageMaxUserCount;
			System.out.println(sql);

			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			}

			ArrayList<UserDataBeans> userList = new ArrayList<UserDataBeans>();

			while (rs.next()) {
				UserDataBeans user = new UserDataBeans();
				/*if(rs.getInt("id")==1) {
					continue;
				}*/
				user.setId(rs.getInt("id"));
				user.setLoginId(rs.getString("login_id"));
				user.setName(rs.getString("name"));
				user.setBirthDate(rs.getDate("birth_date"));
				user.setAddress(rs.getString("address"));
				user.setIs_open(rs.getBoolean("is_open"));
				user.setCreateDate(rs.getDate("create_date"));
				user.setUpdateDate(rs.getDate("update_date"));
				userList.add(user);
			}
			System.out.println("get User by userName has been completed");
			return userList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	/**
	 * ユーザー総数を取得
	 *
	 * @param SearchDataBeans
	 * @return
	 * @throws SQLException
	 */
	public static double getUserCount(SearchDataBeans sdb) throws SQLException {
		Connection con = null;

		String searchUserName = sdb.getName();
		String loginId = sdb.getLoginId();
		String startDate = sdb.getStartDate();
		String endDate = sdb.getEndDate();

		boolean flag = true;
		String or = " or ";
		String and = " and ";
		try {
			con = DBManager.getConnection();

			String sql ="select count(*) as cnt from t_user where name like ";
			String[] searchWords = searchUserName.split("　", 0);
			for(String word:searchWords) {
			  word=" '%"+word+"%' ";
			  if(flag) {
				  sql += word;
				  flag = false;
			  }else {
				  sql = sql +or+" name like " + word;
				  }
			}

			if(!loginId.equals("__noId")) {
				loginId = " '"+loginId+"' ";
				sql = sql + and + "login_id = "+loginId+" ";
			}

			if(!startDate.equals("nodate")) {
				startDate = "' "+startDate+" '";
			    sql = sql +and+" birth_date >= "+startDate+" ";
			    		}
			if(!endDate.equals("nodate")) {
				endDate = "' "+endDate+" '";
			    sql = sql+and+"birth_date <= "+endDate+" ";
			}
			//System.out.println(sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//test
			//System.out.println(rs);
			double count = 0.0;
			while (rs.next()) {
				count = Double.parseDouble(rs.getString("cnt"));
			}
			return count;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * ほしい物リストを公開しているユーザー総数を取得
	 *
	 * @param SearchDataBeans
	 * @return
	 * @throws SQLException
	 */
	public static double getOpenUserCount(String searchUserName) throws SQLException {
		Connection con = null;
		boolean flag = true;
		String or = " or ";
		String and = " and ";
		try {
			con = DBManager.getConnection();

			String sql ="select count(*) as cnt from t_user where is_open = 1 and name like ";
			String[] searchWords = searchUserName.split("　", 0);
			for(String word:searchWords) {
			  word=" '%"+word+"%' ";
			  if(flag) {
				  sql += word;
				  flag = false;
			  }else {
				  sql = sql +or+" name like " + word;
				  }
			}

			//test
			String loginId = "__noId";

			if(!loginId.equals("__noId")) {
				loginId = " '"+loginId+"' ";
				sql = sql + and + "login_id = "+loginId+" ";
			}

			System.out.println(sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//test
			//System.out.println(rs);
			double count = 0.0;
			while (rs.next()) {
				count = Double.parseDouble(rs.getString("cnt"));
			}
			return count;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	/**
	 * ユーザー情報更新
	 * @param UserDataBeans
	 * @throws SQLException
	 */
	public String updateUser(UserDataBeans udb,String birthDate,String confirmPassword) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		String error = "入力内容が正しくありません";
		try {

			if(!udb.getPassword().equals(confirmPassword)) {
				return "パスワードとパスワード(確認)か一致していません";
			}
			if(udb.getName().length()==0) {
				return "名前が入力されていません";
			}

			if(udb.getAddress().length()== 0) {
				return "住所が入力されていません";
			}

			con = DBManager.getConnection();

			// ユーザー情報更新
			st = con.prepareStatement("UPDATE t_user  SET name = ?, address = ?, login_password = ?, birth_date = ?, is_open = ? , update_date = ? WHERE id = ? ");
			st.setString(1, udb.getName());
			st.setString(2, udb.getAddress());
			st.setString(3,udb.getPassword());
			st.setString(4,birthDate);
			st.setBoolean(5,udb.getIs_open());
			st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			st.setInt(7, udb.getId());

			System.out.println(st);


			int rs = st.executeUpdate();

			System.out.println("update User has been completed");
			return "success";
		} catch (SQLException e) {
			return error;

		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


}
