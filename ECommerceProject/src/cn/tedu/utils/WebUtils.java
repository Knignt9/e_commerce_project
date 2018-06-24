package cn.tedu.utils;

public class WebUtils {
	private WebUtils(){}
	public static boolean checkNull(String str){
		return str==null||str.trim().equals("");
	}
}
