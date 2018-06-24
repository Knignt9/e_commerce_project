package cn.tedu.filter;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.tedu.bean.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class AutoLoginFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//ֻ��δ��¼���û��ſ������Զ���¼
		HttpServletRequest req=(HttpServletRequest) request;
		if(req.getSession(false)==null||req.getSession().getAttribute("user")==null){
			//ֻ��Я��cookie�Ĳſ������Զ���¼
			Cookie[] cookies=req.getCookies();
			Cookie autoCookie=null;
			if(cookies!=null){
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("autologin")){
						autoCookie=cookie;
					}
				}
			}
			//ֻ���Զ���¼cookie�б�����û������붼��ȷ�����Զ���¼
			if(autoCookie!=null){
				String username = URLEncoder.encode(autoCookie.getValue().split("#")[0], "utf-8");
				String password = autoCookie.getValue().split("#")[1];
				UserService service=BasicFactory.getFactory().getInstance(UserService.class);
				try {
					User user = service.login(username, password);
					req.getSession().setAttribute("user", user);
				} catch (MsgException e) {
					return;
				}
				
			}
		}
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
