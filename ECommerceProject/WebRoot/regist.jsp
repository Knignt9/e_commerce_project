<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>欢迎注册EasyMall</title>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/regist.css" />
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
$(function(){
		$("#img").click(function(){
			$(this).attr("src","${ pageContext.request.contextPath }/servlet/ValiImageServlet?time="+new Date().getTime());
		});
		$("input[name='username']").blur(function(){
			if(!formObj.checkNull("username","用户名不能为空")){
				return;
			}
				var username=$(this).val();
				$("#username_msg").load("${ pageContext.request.contextPath }/servlet/AjaxCheckUsernameServlet", {"username":username});

		});
		$("input[name='password']").blur(function(){

			formObj.checkNull("password","密码不能为空");
		});
		$("input[name='password2']").blur(function(){
			formObj.checkNull("password2","密码不能为空");
			formObj.checkPassword("password","输入密码不一致");
		});
		$("input[name='email']").blur(function(){
			formObj.checkNull("email","邮箱不能为空");
			formObj.checkEmail("email","邮箱格式不正确");
		});
		$("input[name='nickname']"),blur(function(){
			formObj.checkNull("nickname","昵称不能为空");
		});
		$("input[name='valistr']"),blur(function(){
			formObj.checkNull("valistr","验证码不能为空");
		});
})
	var formObj={
		"checkForm":function(){
			var flag1=checkNull("username","用户名不能为空");
			var flag2=checkNull("password","密码不能为空");
			var flag3=checkNull("password2","验证密码不能为空");
			var flag4=checkNull("email","邮箱不能为空");
			var flag5=checkNull("nickname","昵称不能为空");
			var flag6=checkNull("valistr","验证码不能为空");
			var flag7=checkEmail("email","邮箱格式不正确");
			var flag8=checkPassword("password","输入密码不一致");
			return flag1&&flag2&&flag3&&flag4&&flag5&&flag6&&flag7&&flag8;
		},
		"checkNull":function(name,msg){
			var value = $("input[name='"+name+"']").val();
			if(value.trim() == ""){
				this.setMsg(name,msg);
				return false;
			}
			this.setMsg(name,"");
			return true;
		},
		"setMsg" : function(name,msg){
		 	$("input[name='"+name+"']").nextAll("span").html(msg).css("color","red");
		},
		
		"checkEmail":function(name,msg){
			var value=$("input[name='"+name+"']").val();
			this.setMsg(name,"");
			if(!value.test(/^\w+@\w+(.\w+)+$/)){
				setMsg(name,msg);
				return false;
			}
			return true;
		},
		"checkPassword":function(name,msg){
			var pwd1=$("input[name='"+name+"']").val();
			var pwd2=$("input[name='"+name+"']").val();
			this.setMsg(name,"");
			if(pwd1!=""&&pwd2!=""&&pwd1!=pwd2){
				this.setMsg(name+"2",msg);
				return false;
			}
			return true;
		}
	}
</script>


</head>
<body>
	<form onsubmit="return formObj.checkForm()"
		action="${ pageContext.request.contextPath }/servlet/RegistServlet"
		method="POST">
		<h1>欢迎注册EasyMall</h1>
		<table>
			<tr>
				<td colspan="2" style="color:red;text-align: center">${msg}</td>
			</tr>
			<tr>
				<td class="tds">用户名：</td>
				<td><input type="text" name="username"/>
					
					<span id="username_msg"></span></td>
			</tr>
			<tr>
				<td class="tds">密码：</td>
				<td><input type="password" name="password" />
				</td>
			</tr>
			<tr>
				<td class="tds">确认密码：</td>
				<td><input type="password" name="password2"/>
					<span></span></td>
			</tr>
			<tr>
				<td class="tds">昵称：</td>
				<td><input type="text" name="nickname"
					/>
					<span></span></td>
			</tr>
			<tr>
				<td class="tds">邮箱：</td>
				<td><input type="text" name="email"
					/>
					<span></span></td>
			</tr>
			<tr>
				<td class="tds">验证码：</td>
				<td><input type="text" name="valistr"
					/>
					<img id="img" src="${ pageContext.request.contextPath }/servlet/ValiImageServlet"
					alt="" /> <span></span></td>
			</tr>
			<tr>
				<td class="sub_td" colspan="2" class="tds">
				<input type="submit" value="注册用户 "/> <span></span></td>
			</tr>
		</table>
	</form>
</body>
</html>

