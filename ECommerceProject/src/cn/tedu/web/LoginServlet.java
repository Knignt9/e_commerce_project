package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rename = request.getParameter("remname");
		UserService service=BasicFactory.getFactory().getInstance(UserService.class);
		try {
			User user = service.login(username, password);
			request.getSession().setAttribute("user", user);
			if("true".equals(rename)){
				Cookie cookie=new Cookie("remname",URLEncoder.encode(username,"utf-8"));
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(60*60*24*30);
				response.addCookie(cookie);
			}else{
				Cookie cookie=new Cookie("remname","");
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			if("true".equals(request.getParameter("autologin"))){
				Cookie cookie=new Cookie("autologin",URLEncoder.encode(username, "utf-8")+"#"+password);
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(60*60*24*30);
				response.addCookie(cookie);
			}
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (MsgException e) {
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
