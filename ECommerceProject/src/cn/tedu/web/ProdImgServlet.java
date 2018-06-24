package cn.tedu.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProdImgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imgurl = request.getParameter("imgurl");
		OutputStream out=response.getOutputStream();
		InputStream in = new FileInputStream(imgurl);
		int i=-1;
		byte[] data=new byte[1024];
		while((i=in.read(data))!=-1){
			out.write(data,0,i);
		}
		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
