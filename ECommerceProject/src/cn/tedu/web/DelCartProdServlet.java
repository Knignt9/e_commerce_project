package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class DelCartProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int auto_id = Integer.parseInt(request.getParameter("auto_id"));
		ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
		service.delCartProd(auto_id);
		response.sendRedirect(request.getContextPath()+"/servlet/ManageCartProdServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
