package cn.tedu.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.omg.CORBA.Request;

public class EncodingFilter implements Filter{
	private String encode=null;
	public void destroy() {
		
	} 

	public void doFilter(final ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html;charset="+encode);
		ServletRequest proxyRequest=(ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader()
				, request.getClass().getInterfaces(),new InvocationHandler() {
					
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						if("getParameter".equals(method.getName())){
							String value = (String) method.invoke(request, args);
							if(value!=null){
							return new String(value.getBytes("iso8859-1"),encode);
							}else{
								return null;
							}
						}else if("getParameterValues".equals(method.getName())){
							String[] values=(String[]) method.invoke(request, args);
							for (String value : values) {
								value=new String(value.getBytes("iso8859-1"),encode);
							}
							return values;
						}else if("getParameterMap()".equals(method.getName())){
							Map<String,String[]> map=(Map<String, String[]>) method.invoke(request, args);
							for (Map.Entry<String, String[]> entry:map.entrySet()) {
								String[] values = entry.getValue();
								for (String value : values) {
									value=new String(value.getBytes("iso8859-1"),encode);
								}
								map.put(entry.getKey(), values);
							}
							return map;
						}else{
							return method.invoke(request, args);
						}
					}
				});
		chain.doFilter(proxyRequest, response);
	}

	public void init(FilterConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		this.encode=sc.getInitParameter("encode");
	}

}
