package com.internousdev.hibiscus.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.hibiscus.dao.CartInfoDAO;
import com.internousdev.hibiscus.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * カート削除
 *
 * @author Okabe
 *
 */

public class DeleteCartAction extends ActionSupport implements SessionAware {

	private Collection<String> checkList;
	private Map<String, Object> session;

	public String execute() {

		if (!session.containsKey("mCategoryDtoList")) {
			return "session";
		}

		String result = ERROR;
		String loginId = null;
		int count = 0;
		CartInfoDAO cartInfoDAO = new CartInfoDAO();

		// ログインIDを取得
		if (session.containsKey("loginId")) {
			loginId = session.get("loginId").toString();
		} else if (session.containsKey("tempUserId")) {
			loginId = session.get("tempUserId").toString();
		}

		// カート情報が複数件あり、削除チェックボックスにひとつもチェックをつけない場合、
		// nullになるため空のリストを生成する。
		// （カート一件の場合はfalseが返りOK）
		if (checkList == null) {
			checkList = new ArrayList<String>();
		}

		// checkListで選択した商品をカートから削除
		for (String productId : checkList) {
			count += cartInfoDAO.delete(productId, loginId);
		}

		if (count > 0) {
			// 削除成功
			List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
			cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(loginId);
			session.put("cartInfoDTOList", cartInfoDTOList);
			result = SUCCESS;
		} else {
			// 削除失敗
			return ERROR;
		}

		int totalPrice = cartInfoDAO.getTotalPrice(loginId);
		session.put("totalPrice", totalPrice);

		return result;
	}

	public Collection<String> getCheckList() {
		return checkList;
	}

	public void setCheckList(Collection<String> checkList) {
		this.checkList = checkList;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
