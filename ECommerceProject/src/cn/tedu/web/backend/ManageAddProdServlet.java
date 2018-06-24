package cn.tedu.web.backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.tedu.bean.Prod;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class ManageAddProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, String> map=new HashMap<String, String>();
			ServletContext sc = this.getServletContext();
			String temp="WEB-INF/temp";
			String upload="WEB-INF/upload";
			DiskFileItemFactory factory = new DiskFileItemFactory(100,new File(sc.getRealPath(temp)));
			ServletFileUpload fileUpload=new ServletFileUpload(factory);
			if(!fileUpload.isMultipartContent(request)){
				throw new MsgException("表单格式不正确");
			}
			fileUpload.setFileSizeMax(1024*1024*10);
			fileUpload.setSizeMax(1024*1024*100);
			fileUpload.setHeaderEncoding(sc.getInitParameter("encode"));
			List<FileItem> items = fileUpload.parseRequest(request);
			for (FileItem item : items) {
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString(sc.getInitParameter("encode"));
					map.put(name, value);
				}else{
					String fname=item.getName();
					if(fname.contains("\\")){
						fname=fname.substring(fname.lastIndexOf("\\")+1);
					}
					fname=UUID.randomUUID().toString()+"_"+fname;
					String hash = Integer.toHexString(fname.hashCode());
					while(hash.length()<8){
						hash+="0";
					}
					for (int i = 0; i < hash.length(); i++) {
						upload+="/"+hash.charAt(i);
					}
					new File(sc.getRealPath(upload)).mkdirs();
					String savePath = sc.getRealPath(upload+"/"+fname);
					map.put("imgurl", savePath);
					InputStream in = item.getInputStream();
					OutputStream out=new FileOutputStream(savePath);
					byte[] b=new byte[1024];
					int i=-1;
					while((i=in.read(b))!=-1){
						out.write(b,0,i);
					}
					out.flush();
					out.close();
					in.close();
					item.delete();
				}
			}
			//2.将商品信息保存到数据库
			ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
			Prod prod=new Prod();
			prod.setName(map.get("name"));
			prod.setPrice(Double.parseDouble(map.get("price")));
			prod.setCname(map.get("cname"));
			prod.setPnum(Integer.parseInt(map.get("pnum")));
			prod.setImgurl(map.get("imgurl"));
			prod.setDescription(map.get("description"));
			
			service.addProd(prod);
			response.getWriter().write("恭喜你添加商品成功，3秒或跳转到后台");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/backend/_right.jsp");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (MsgException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
