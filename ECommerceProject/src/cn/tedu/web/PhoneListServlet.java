package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.bean.Prod;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class PhoneListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cname = request.getParameter("cname");
		ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list=service.findProdListByCname(cname);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/categoryList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
