<%@ page language="java" import="java.util.*,cn.tedu.bean.User" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<div id="common_head">
	<div id="line1">
		<div id="content">
			<c:if test="${empty sessionScope.user}">
			<a href="${pageContext.request.contextPath}/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/regist.jsp">注册</a>
			</c:if>
			<c:if test="${!(empty sessionScope.user)}">
			欢迎 ${ user.username } 回来，
			<a href="${pageContext.request.contextPath}/servlet/LogoutServlet">退出</a>
			</c:if>
		</div>
	</div>
	<div id="line2">
		<img id="logo" src="${pageContext.request.contextPath}/img/head/logo.jpg"/>
		<input type="text" name=""/>
		<input type="button" value="搜 索"/>
		<span id="goto">
			<a id="goto_order" href="${pageContext.request.contextPath}/#">我的订单</a>
			<c:if test="${!(empty sessionScope.user)}">
			<a id="goto_cart" href="${pageContext.request.contextPath}/servlet/ManageCartProdServlet?user=${sessionScope.user}">我的购物车</a>
			</c:if>
			<c:if test="${empty sessionScope.user}">
			<a id="goto_cart" href="#">我的购物车</a>
			</c:if>
		</span>
		<img id="erwm" src="${pageContext.request.contextPath}/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="${pageContext.request.contextPath}/servlet/ProdListServlet">首页</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/ProdListServlet">全部商品</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/PhoneListServlet?cname=电子数码">手机数码</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/PhoneListServlet?cname=电子数码">电脑平板</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/PhoneListServlet?cname=家用电器">家用电器</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/PhoneListServlet?cname=家用电器">汽车用品</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/PhoneListServlet?cname=日用百货">食品饮料</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/PhoneListServlet?cname=图书杂志">图书杂志</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/PhoneListServlet?cname=服装服饰">服装服饰</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/PhoneListServlet?cname=日用百货">理财产品</a></li>
			</ul>
		</div>
	</div>
</div>
