package cn.tedu.service;

import cn.tedu.bean.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;

public interface UserService {
	public void hasUser(String username) throws MsgException;
	public User login(String username,String password) throws MsgException;
	public void regist(User user);
}
