package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.Prod;
import cn.tedu.bean.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int num = Integer.parseInt(request.getParameter("num"));
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			response.getWriter().write("<h1 style='color:red;text-align:center'>你还没有登陆，3秒后跳转到登陆页面</h1>");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/login.jsp");
		}else{
			ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
			Prod prod = service.findProdById(id);
			prod.setId(user.getId());
			//设置购买数量
			prod.setBuyNum(num);
			//将商品信息存储到数据库
			service.addProdToCart(prod);
			response.sendRedirect(request.getContextPath()+"/servlet/ManageCartProdServlet");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
