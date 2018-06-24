package cn.tedu.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
	private TransactionManager(){}
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>(){
		protected Connection initialValue(){
			try {
				return JDBCUtils.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		};
	};
	
	public static Connection getConnection(){
		return tl.get();
	}
	public static void startTran(){
		try {
			tl.get().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commitTran(){
		try {
			tl.get().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollbackTran(){
		try {
			tl.get().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void release(){
		JDBCUtils.close(tl.get(), null, null);
		tl.remove();
	}
	
}
