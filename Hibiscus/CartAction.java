package com.internousdev.hibiscus.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.hibiscus.dao.CartInfoDAO;
import com.internousdev.hibiscus.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Goカート
 *
 * @author Okabe
 *
 */

public class CartAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute() {

		if (!session.containsKey("mCategoryDtoList")) {
			return "session";
		}

		String result = ERROR;
		String loginId = null;
		CartInfoDAO cartInfoDAO = new CartInfoDAO();

		// ユーザーID取得
		if (session.containsKey("loginId")) {
			loginId = session.get("loginId").toString();
		} else if (session.containsKey("tempUserId")) {
			loginId = session.get("tempUserId").toString();
		}

		// cart情報を取得
		List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
		cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(loginId);
		session.put("cartInfoDTOList", cartInfoDTOList);

		int totalPrice = cartInfoDAO.getTotalPrice(loginId);
		session.put("totalPrice", totalPrice);

		result = SUCCESS;

		return result;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
