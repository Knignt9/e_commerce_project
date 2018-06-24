package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String valistr = request.getParameter("valistr");
		String code = (String) request.getSession().getAttribute("code");
		if(!valistr.equalsIgnoreCase(code)){
			request.setAttribute("msg", "验证码不一致");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		try {
			User user=new User(username, password, password2, email, nickname, valistr);
			UserService service=BasicFactory.getFactory().getInstance(UserService.class);
			service.regist(user);
			response.getWriter().write("<h1 style='color:red;text-align:center'>恭喜您注册成功，3秒之后跳转回首页...</h1>");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
		} catch (MsgException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
