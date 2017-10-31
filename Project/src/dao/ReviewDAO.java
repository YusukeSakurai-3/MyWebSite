package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.DBManager;
import beans.ReviewDataBeans;

public class ReviewDAO {

	// インスタンスオブジェクトを返却させてコードの簡略化
	public static ReviewDAO getInstance() {
		return new ReviewDAO();
	}
	/**
	 * レビューリスト更新
	 * @param ReviewDataBeans
	 *
	 * @throws SQLException
	 */
	public void insertUserReview(ReviewDataBeans rdb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

				// ほしい物リスト更新
				st = con.prepareStatement("insert t_review (user_id, item_id,title,file_name,review_text,evaluation) VALUES(?,?,?,?,?,?)");
				st.setInt(1, rdb.getUserId());
				st.setInt(2, rdb.getItemId());
				st.setString(3, rdb.getTitle());
				st.setString(4, rdb.getFileName());
				st.setString(5,rdb.getReviewText());
				st.setInt(6,rdb.getEvaluation());


			int rs = st.executeUpdate();

			System.out.println("update Review has been completed");
			return ;
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
	 * ユーザーIDによるレビューリスト取得
	 * @param userId
	 * @return ItemDataBeans
	 * @throws SQLException
	 */
	public static ArrayList<ReviewDataBeans> getReviewListByUserId(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM t_review WHERE user_id = ?");
			st.setInt(1, userId);

			ResultSet rs = st.executeQuery();

			ArrayList<ReviewDataBeans> reviewList = new ArrayList<ReviewDataBeans>();
			while(rs.next()) {
				ReviewDataBeans review = new ReviewDataBeans();
				review.setId(rs.getInt("id"));
				review.setUserId(rs.getInt("user_id"));
				review.setItemId(rs.getInt("item_id"));
				review.setTitle(rs.getString("title"));
				review.setReviewText(rs.getString("review_text"));
				review.setEvaluation(rs.getInt("evaluation"));
				review.setFileName(rs.getString("file_name"));
				reviewList.add(review);
			}

			System.out.println("searching review by userID has been completed");

			return reviewList;
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
	 * レビューIDによる商品検索
	 * @param itemId
	 * @return ItemDataBeans
	 * @throws SQLException
	 */
	public static ReviewDataBeans getReviewByReviewId(int reviewId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM t_review WHERE id = ?");
			st.setInt(1, reviewId);

			ResultSet rs = st.executeQuery();

			ReviewDataBeans review = new ReviewDataBeans();
			if (rs.next()) {
				review.setId(rs.getInt("id"));
				review.setUserId(rs.getInt("user_id"));
				review.setItemId(rs.getInt("item_id"));
				review.setTitle(rs.getString("title"));
				review.setReviewText(rs.getString("review_text"));
				review.setFileName(rs.getString("file_name"));
			}

			System.out.println("searching item by itemID has been completed");

			return review;
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
	 * 商品IDによる商品検索
	 * @param itemId
	 * @return ItemDataBeans
	 * @throws SQLException
	 */
	public static ArrayList<ReviewDataBeans> getReviewListByItemId(int itemId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM t_review WHERE item_id = ?");
			st.setInt(1, itemId);

			ResultSet rs = st.executeQuery();


			ArrayList<ReviewDataBeans> reviewList = new ArrayList<ReviewDataBeans>();
			while (rs.next()) {
				ReviewDataBeans review = new ReviewDataBeans();
				review.setId(rs.getInt("id"));
				review.setUserId(rs.getInt("user_id"));
				review.setItemId(rs.getInt("item_id"));
				review.setTitle(rs.getString("title"));
				review.setReviewText(rs.getString("review_text"));
				review.setFileName(rs.getString("file_name"));
				reviewList.add(review);
			}

			System.out.println("searching item by itemID has been completed");

			return reviewList;
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
	 * レビュー削除
	 * @param userId
	 * @param item
	 * @throws SQLException
	 */
	public boolean deleteUserReview(int id) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			//レビュー削除
			st = con.prepareStatement("delete from t_review where id = ? ");
			st.setInt(1, id);

			int rs = st.executeUpdate();

			System.out.println("delete UserReview has been completed");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
			//throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
