package cn.tedu.web.backend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class ManageDelProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
		service.delProd(id);
		response.getWriter().write("ɾ���ɹ���3�����ת����̨");
		response.setHeader("refresh", "3;url="+request.getContextPath()+"/servlet/ManageProdListServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
