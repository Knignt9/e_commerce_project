<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/prodList.css"rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function prodInfo(msg){
	//$.post("${pageContext.request.contextPath}/servlet/AjaxProdInfoServlet",{"id":msg});
		window.location.href="${pageContext.request.contextPath}/servlet/AjaxProdInfoServlet?id="+msg;
// 		window.close();
	}
</script>
</head>
<body>
	<%@ include file="/_head.jsp"%>
	<div id="content">
		<div id="search_div">
			<form method="post" action="${pageContext.request.contextPath}/servlet/CheckProdServlet">
				<span class="input_span">商品名：
					<input type="text" name="name" />
				</span> 
				<span class="input_span">商品种类：
					<select name="cname">
						<option value="电子数码">电子数码</option>
						<option value="图书杂志">图书杂志</option>
						<option value="床上用品">床上用品</option>
						<option value="家用电器">家用电器</option>
						<option value="日用百货">日用百货</option>
						<option value="服装服饰">服装服饰</option>
					</select>
				</span> <span class="input_span">商品价格区间：
					<select name="minprice">
						<option value="0">0</option>
						<option value="100">100</option>
						<option value="200">200</option>
						<option value="500">500</option>
						<option value="800">800</option>
						<option value="1000">1000</option>
						<option value="2000">2000</option>
					</select>
					-
					<select name="maxprice">
						<option value="100">100</option>
						<option value="200">200</option>
						<option value="500">500</option>
						<option value="800">800</option>
						<option value="1000">1000</option>
						<option value="2000">2000</option>
						<option value="100000">10000</option>
					</select>
				</span> 
				<input type="submit" value="查询">
			</form>
		</div>
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
