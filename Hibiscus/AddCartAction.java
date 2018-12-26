package com.internousdev.hibiscus.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.hibiscus.dao.CartInfoDAO;
import com.internousdev.hibiscus.dao.ProductInfoDAO;
import com.internousdev.hibiscus.dto.CartInfoDTO;
//個数等書き換えられた際に商品リストに飛ばす用
import com.internousdev.hibiscus.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * カート追加
 *
 * @author Okabe
 *
 */

public class AddCartAction extends ActionSupport implements SessionAware {

	private int productCount;
	private Map<String, Object> session;
//	個数書き換え対策に商品リストを取得するよう
	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();

	public String execute() {

		if (!session.containsKey("mCategoryDtoList")) {
			return "session";
		}
		// 商品個数が自然数以外に書き換えられた時商品リストへ=>エラー画面は表示させない
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		if (productCount <= 0 || productCount >= 6) {
			productInfoDtoList = productInfoDAO.getProductInfoList();
			session.put("productInfoDtoList", productInfoDtoList);
			return ERROR;
		}

		String result = ERROR;
		String loginId = null;
		String tempUserId = null;

		// ログインID取得
		if (session.containsKey("loginId")) {
			loginId = session.get("loginId").toString();
		} else if (session.containsKey("tempUserId")) {
			loginId = session.get("tempUserId").toString();
			tempUserId = session.get("tempUserId").toString();
		}

		// cartに商品を追加
		int productId = Integer.parseInt(session.get("productId").toString());
		int price = Integer.parseInt(session.get("price").toString());
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		int count = cartInfoDAO.regist(loginId, tempUserId, productId, productCount, price);
		if (count > 0) {
			result = SUCCESS;
		}

		// cart情報を取得
		List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
		cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(loginId);
		session.put("cartInfoDTOList", cartInfoDTOList);

		int totalPrice = cartInfoDAO.getTotalPrice(loginId);
		session.put("totalPrice", totalPrice);

		return result;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
