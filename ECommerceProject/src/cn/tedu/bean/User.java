package cn.tedu.bean;

import cn.tedu.exception.MsgException;
import cn.tedu.utils.WebUtils;

public class User {
	private int id;
	private String username;
	private String password;
	private String password2;
	private String email;
	private String nickname;
	private String valistr;
	public User() {
		super();
	}
	public User(String username, String password, String password2,
			String email, String nickname, String valistr) throws MsgException {
		super();
		this.username = username;
		this.password = password;
		this.password2 = password2;
		this.email = email;
		this.nickname = nickname;
		this.valistr = valistr;
		
		if(WebUtils.checkNull(username)){
			throw new MsgException("用户名不能为空");
		}
		if(WebUtils.checkNull(password)){
			throw new MsgException("密码不能为空");
		}
		if(WebUtils.checkNull(password2)){
			throw new MsgException("验证密码不能为空");
		}
		if(password!=password){
			throw new MsgException("两次密码不一致");
		}
		if(WebUtils.checkNull(email)){
			throw new MsgException("邮箱不能为空");
		}
		if(!email.matches("\\w+@\\w+(\\.\\w+)+")){
			throw new MsgException("邮箱格式不正确");
		}
		if(WebUtils.checkNull(nickname)){
			throw new MsgException("昵称不能为空");
		}
		if(WebUtils.checkNull(valistr)){
			throw new MsgException("验证码不能为空");
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getValistr() {
		return valistr;
	}
	public void setValistr(String valistr) {
		this.valistr = valistr;
	}
	
}
