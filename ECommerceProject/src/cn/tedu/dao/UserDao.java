package cn.tedu.dao;

import cn.tedu.bean.User;

public interface UserDao {
	public boolean checkUserByUsername(String username);
	public User checkUserByUsernameAndPassword(String username,String password);
	public void saveUser(User user);
}
