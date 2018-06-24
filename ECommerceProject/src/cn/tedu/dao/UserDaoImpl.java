package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.bean.User;
import cn.tedu.utils.JDBCUtils;

public class UserDaoImpl implements UserDao{
	public boolean checkUserByUsername(String username){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtils.getConnection();
			String sql="select * from user where username=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			return rs.next();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
	}
	public User checkUserByUsernameAndPassword(String username,String password){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtils.getConnection();
			String sql="select * from user where username=? and password=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setNickname(rs.getString("nickname"));
				return user;
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
	}
	public void saveUser(User user){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtils.getConnection();
			String sql="insert into user value(null,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getEmail());
			ps.setString(4,user.getNickname());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
	}
}
