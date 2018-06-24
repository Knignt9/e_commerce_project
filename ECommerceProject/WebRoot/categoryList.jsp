<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/prodList.css"rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="/_head.jsp"%>
	<div id="content">
		<div id="prod_content">
			<c:forEach items="${ requestScope.list }" var="prod">
				<div id="prod_div">
					<img id="img_div" src="${pageContext.request.contextPath}/servlet/ProdImgServlet?imgurl=${ prod.imgurl }"  onclick="prodInfo('${prod.id}')"></img>
					<div id="prod_name_div">${ prod.name }</div>
					<div id="prod_price_div">${ prod.price }元</div>
					<div>
						<div id="gotocart_div">
							<a href="${pageContext.request.contextPath}/servlet/AddCartServlet?id=${prod.id}&&num=1">加入购物车</a>
						</div>
						<div id="say_div">133人评价</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="clear: both"></div>
	</div>
	<%@ include file="/_foot.jsp"%>
</body>
</html>

