<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
		<link href="${ pageContext.request.contextPath }/css/cart.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<script type="text/javascript"
			src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			window.onload=function(){
				var items=document.getElementsByName("prodC");
				for(var i=0;i<items.length;i++){
					items[i].onclick=function(){
						var flag=true;
						for(var j=0;j<items.length;j++){
							if(!items[j].checked){
								flag=false;
								break;
							}
						}
						document.getElementsByName("allC").checked=flag;
					};
				}
			}
			function check(obj){
				var items=document.getElementsByName("prodC");
				var sum = 0;
				for(var i=0; i<items.length;i++){
					if(items[i].checked){
						sum += 	parseFloat(items[i].value);	
					}
				}
				document.getElementById("span_2").innerHTML = sum;
			}  
			function checkAll(obj){
				var items=document.getElementsByName("prodC");
				var sum=0;
				for(var i=0; i<items.length; i++){
				
					items[i].checked = obj.checked;
					sum += 	parseFloat(items[i].value);
					alert(sum);
				}
				document.getElementById("span_2").innerHTML = sum;
			}
		 	function changeNum(inp,flag,price){
				var num=parseInt($(inp).parent().children("input").val());
				var price=parseInt(price);
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
				var sumPrice=num*price;
				$(inp).parent().children("input").val(num);
				$(inp).parent().next().text("￥"+sumPrice);
			};
	</script>
	</head>
	<body>
		<%@ include file="/_head.jsp"%>
		<div class="warp">
			<div id="title">
				<input name="allC" type="checkbox" value="" onclick=""/>
				<span id="title_checkall_text">全选</span>
				<span id="title_name">商品</span>
				<span id="title_price">单价（元）</span>
				<span id="title_buynum">数量</span>
				<span id="title_money">小计（元）</span>
				<span id="title_del">操作</span>
			</div>
			<!-- 购物信息 -->
			<c:forEach items="${ requestScope.list }" var="prod">
			<div id="prods">
				<input name="prodC" type="checkbox" value="" onclick="check(this)"/>
				<img width="90" src="${pageContext.request.contextPath}/servlet/ProdImgServlet?imgurl=${ prod.imgurl }" height="90" />
				<span id="prods_name">${prod.name}</span>
				<span id="prods_price">${prod.price}</span>
				<span id="prods_buynum"> 
					<a href="javascript:void(0)" id="delNum" onclick="changeNum(this,'del','${prod.price}')">-</a>
					<input id="buyNumInp" type="text" value="${prod.buyNum}" onblur="changeNum(this,'mod','${prod.price}')"/>
					<a href="javascript:void(0)" id="addNum" onclick="changeNum(this,'add','${prod.price}')">+</a>
				</span>
				<span id="prods_money">${prod.price*(prod.buyNum)}</span>
				<span id="prods_del" ><a href="${pageContext.request.contextPath}/servlet/DelCartProdServlet?auto_id=${prod.auto_id}">删除</a></span>
			</div>
			</c:forEach>
			<!-- 总计条 -->
			<div id="total">
				<div id="total_1">
					<input name="allC" type="checkbox" value="" onclick="checkAll(this)"/> 
					<span>全选</span>
					<a id="del_a" href="#">删除选中的商品</a>
					<span id="span_1">总价：</span>
					<span id="span_2" >￥0</span>
				</div>
				<div id="total_2">	
					<a id="goto_order" href="#">去结算</a>
				</div>
			</div>
		</div>
		<%@ include file="/_foot.jsp"%>
	</body>
</html>
