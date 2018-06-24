package cn.tedu.web.backend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class AjaxChangePnumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		int pnum =Integer.parseInt(request.getParameter("pnum")) ;
		int id =Integer.parseInt(request.getParameter("id"));
		ProdService service =BasicFactory.getFactory().getInstance(ProdService.class);
		service.changePnum(pnum,id);
		response.getWriter().write("��Ʒ�����޸ĳɹ�");
		}catch(Exception e){
			response.getWriter().write("��Ʒ�����޸�ʧ��");
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
