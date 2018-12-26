package com.internousdev.hibiscus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.hibiscus.dto.CartInfoDTO;
import com.internousdev.hibiscus.util.DBConnector;

/**
 * カートDAO
 *
 * @author Okabe
 *
 */

public class CartInfoDAO {

	// cart_infoテーブルとproduct_infoテーブルを結合してselect
	public ArrayList<CartInfoDTO> getCartInfoDTOList(String loginId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
		String sql = "SELECT ci.product_id, sum(ci.product_count) as product_count, pi.price, pi.product_name, "
				+ "pi.product_name_kana, pi.image_file_path, pi.image_file_name, (DATE_FORMAT(pi.release_date,'%Y/%m/%d')) as release_date, "
				+ "pi.release_company, (sum(ci.product_count) * ci.price) as subtotal FROM cart_info as ci "
				+ "LEFT JOIN product_info as pi ON ci.product_id = pi.product_id WHERE ci.user_id = ? "
				+ "GROUP BY product_id";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CartInfoDTO cartInfoDTO = new CartInfoDTO();
				cartInfoDTO.setProductId(rs.getInt("product_id"));
				cartInfoDTO.setProductCount(rs.getInt("product_count"));
				cartInfoDTO.setPrice(rs.getInt("price"));
				cartInfoDTO.setProductName(rs.getString("product_name"));
				cartInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				cartInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				cartInfoDTO.setImageFileName(rs.getString("image_file_name"));
				cartInfoDTO.setReleaseDate(rs.getString("release_date"));
				cartInfoDTO.setReleaseCompany(rs.getString("release_company"));
				cartInfoDTO.setSubtotal(rs.getInt("subtotal"));
				cartInfoDTOList.add(cartInfoDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cartInfoDTOList;
	}

	// cart_infoテーブルにデータinsert
	public int regist(String loginId, String tempUserId, int productId, int productCount, int price) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "INSERT INTO cart_info(user_id,temp_user_id,product_id,product_count,price,regist_date,update_date) "
				+ "values(?,?,?,?,?,now(),now())";
		int count = 0;
		// countはカート情報が挿入されたかの判定に使用

		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, tempUserId);
			preparedStatement.setInt(3, productId);
			preparedStatement.setInt(4, productCount);
			preparedStatement.setInt(5, price);

			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	// cart_infoテーブル内の総計金額select
	public int getTotalPrice(String loginId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "SELECT sum(product_count * price) as total_price FROM cart_info WHERE user_id = ?";
		int totalPrice = 0;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				totalPrice = rs.getInt("total_price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalPrice;
	}

	// cart_infoテーブルから選択delete
	public int delete(String productId, String loginId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "DELETE FROM cart_info WHERE product_id = ? and user_id = ?";
		int count = 0;
		// countは削除成否チェック用

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, productId);
			ps.setString(2, loginId);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	// cart_infoテーブルから全件delete
	public int deleteAll(String loginId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "DELETE FROM cart_info WHERE user_id = ?";
		int count = 0;
		// countは削除成否チェック用

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	// ログイン時にcart_infoテーブルのloginIdをupdate
	public int linkToLoginId(String tempUserId, String loginId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "UPDATE cart_info SET user_id = ?, temp_user_id = null WHERE temp_user_id = ?";
		int count = 0;
		// countは成否チェック用

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, tempUserId);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

}
