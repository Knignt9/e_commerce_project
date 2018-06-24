package cn.tedu.service;

import cn.tedu.bean.User;
import cn.tedu.dao.UserDao;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;

public class UserServiceImpl implements UserService {
	private UserDao dao=BasicFactory.getFactory().getInstance(UserDao.class);
	public void hasUser(String username) throws MsgException{
		boolean result=dao.checkUserByUsername(username);
		if(result){
			throw new MsgException("用户名已存在");
		}
	}
	public User login(String username,String password) throws MsgException{
		User user =dao.checkUserByUsernameAndPassword(username, password);
		if(user==null){
			throw new MsgException("用户名或密码错误");
		}
		return user;
	}
	public void regist(User user){
		dao.saveUser(user);
	}
}
