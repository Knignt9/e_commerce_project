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

public class ManageCartProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list=service.findCartProdList(user.getId());
		request.setAttribute("list", list);
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
