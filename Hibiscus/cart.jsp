<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="./images/favicon.ico">
<link rel="stylesheet" href="./css/hibiscus.css" />
<link rel="stylesheet" href="./css/common.css" />
<link rel="stylesheet" href="./css/cart.css" />
<title>カート画面</title>
<script>
	function checkValue(check) {
		var checkList = document.getElementsByClassName("checkList");
		var checkFlag = 0;
		for (var i = 0; i < checkList.length; i++) {
			if (checkFlag == 0) {
				if (checkList[i].checked) {
					checkFlag = 1;
					break;
				}
			}
		}
		if (checkFlag == 1) {
			document.getElementById('inactive_btn').disabled = "";
			document.getElementById('inactive_btn').id = "delete_btn";
		} else {
			document.getElementById('delete_btn').disabled = "true";
			document.getElementById('delete_btn').id = "inactive_btn";
		}
	}
</script>

</head>
<body>

	<jsp:include page="header.jsp" />

	<!-- メインここから -->

	<div id="main">
		<h1>カート画面</h1>

		<!-- 商品が入っていない -->
		<s:if test="#session.cartInfoDTOList.size() <= 0">
			<div class="complete">カート情報がありません。</div>
		</s:if>

		<!-- 商品が入っている -->
		<s:if test="#session.cartInfoDTOList.size() > 0">

			<s:form action="SettlementConfirmAction">

				<!-- カート情報テーブル -->
				<div>
					<table>
						<tr>
							<th>削除対象</th>
							<th>商品名</th>
							<th>ふりがな</th>
							<th>商品画像</th>
							<th>値段</th>
							<th>発売会社名</th>
							<th>発売年月日</th>
							<th>購入個数</th>
							<th>合計金額</th>
						</tr>
						<s:iterator value="#session.cartInfoDTOList">
							<tr>
								<td class="center"><s:checkbox class="checkList" name="checkList"
										value="checked" fieldValue="%{productId}"
										onchange="checkValue(this.value)" /></td>
								<td class="center"><s:property value="productName" /></td>
								<td class="center"><s:property value="productNameKana" /></td>
								<td class="center"><img
									src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
									width="50px" height="50px" class="photo" /></td>
								<td class="center"><s:property value="price" />円</td>
								<td class="center"><s:property value="releaseCompany" /></td>
								<td class="center"><s:property value="releaseDate" /></td>
								<td class="center"><s:property value="productCount" /></td>
								<td class="center"><s:property value="subtotal" />円</td>
							</tr>

							<s:hidden name="productId" value="%{productId}" />
							<s:hidden name="productCount" value="%{productCount}" />
						</s:iterator>
					</table>
				</div>

				<div class="sum">
					<s:label value="カート合計金額 :" />
					<s:property value="#session.totalPrice" />
					円
				</div>

				<div class="s_btn">
					<s:submit id="submit" value="決済" />
					<s:hidden name="notDestinationFlg" value="1" />
					<!-- 新規宛先からかカートからかの遷移判断Flg -->
					<div>
						<s:submit id="inactive_btn" value="削除"
							onclick="this.form.action='DeleteCartAction';" disabled="true" />
					</div>
				</div>
			</s:form>


		</s:if>

	</div>

	<!-- メインここまで -->

	<div class="margin"></div>
	<%@ include file="footer.jsp"%>

</body>
</html>