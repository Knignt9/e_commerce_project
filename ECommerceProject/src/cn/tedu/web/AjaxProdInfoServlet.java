package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.Prod;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class AjaxProdInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
		Prod prod=service.findProdById(id);
		request.setAttribute("prod", prod);
		request.getRequestDispatcher("/prodInfo.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
