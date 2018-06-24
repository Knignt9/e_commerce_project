<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<link href="${ pageContext.request.contextPath }/css/prodInfo.css" rel="stylesheet" type="text/css">
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<style type="text/css">
	#right #right_middle #buyNumInp{
		border: 1px solid #CCCCCC;
		width:40px;
		height:24px;
		line-height:20px;
		text-align: center;
		vertical-align: middle;
		}
	</style>
	<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
	<script type="text/javascript">
	function add_cart(id){
		 var num=$("#buyNumInp").val();
		window.location.href="${pageContext.request.contextPath}/servlet/AddCartServlet?id="+id+"&&num="+num;
		//$.post("${pageContext.request.contextPath}/servlet/AddCartServlet",{"id":id});
	}
	/* function add_cart_but(name,price){
		var num=parseInt($("#buyNumInp").val());
		var price=parseInt(price);
		var sumprice=num*price;
		window.location.href="${ pageContext.request.contextPath }/cart.jsp?name="+name+"&&num="+num+"&&price="+sumprice;
	}; */
	function changeNum(flag){
		var num=parseInt($("#buyNumInp").val());
			if(flag=="add"){
				num++;
			}else if(flag=="del"){
				num--;
				if(num<1){
					alert("商品数量不能小于1");
					return false;
				}
			}else if(flag=="mod"){
				if(isNaN(num)==true){
					alert("输入框必须为数字");
					window.location.href=window.location.href;
					return false;
				}else if(num<1){
					alert("商品数量不能小于1");
					window.location.href=window.location.href;
					return false;
				}
			}
			$("#buyNumInp").val(num);
		};
	</script>
</head>
<body>
	<%@ include file="/_head.jsp"%>
	<div id="warp">
		<div id="left">
			<div id="left_top">
				<img src="${pageContext.request.contextPath}/servlet/ProdImgServlet?imgurl=${ prod.imgurl }"/>
			</div>
			<div id="left_bottom">
				<img id="lf_img" src="${ pageContext.request.contextPath }/img/prodInfo/lf.jpg"/>
				<img id="mid_img" src="${pageContext.request.contextPath}/servlet/ProdImgServlet?imgurl=${ prod.imgurl }" width="60px" height="60px"/>
				<img id="rt_img" src="${ pageContext.request.contextPath }/img/prodInfo/rt.jpg"/>
			</div>
		</div>
		<div id="right">
			<div id="right_top">
				<span id="prod_name">${ prod.name } <br/></span>
				<br>
				<span id="prod_desc">${ prod.description }<br/></span>
			</div>
			<div id="right_middle">
				<span id="right_middle_span">
						EasyMall 价：<span class="price_red">￥${prod.price }<br/>
			            运     费：满 100 免运费<br />
			            服     务：由EasyMall负责发货，并提供售后服务<br />
			            购买数量：
	            <a href="#" id="delNum" onclick="changeNum('del')">-</a>
	            <input id="buyNumInp" name="buyNum" type="text" value="1" onblur="changeNum('mod')"/>
		        <a href="#" id="addNum" onclick="changeNum('add')">+</a>
			</div>
			<div id="right_bottom">
				<input class="add_cart_but" type="button" onclick="add_cart('${prod.id}')"/>	
			</div>
		</div>
	</div>
	<%@ include file="/_foot.jsp"%>
</body>
</html>