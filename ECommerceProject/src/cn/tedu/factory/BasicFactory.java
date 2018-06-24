package cn.tedu.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BasicFactory {
	private static BasicFactory factory=new BasicFactory();
	private static Properties prop=new Properties();
	static{
		String path = BasicFactory.class.getClassLoader().getResource("config.properties").getPath();
		try {
			prop.load(new FileInputStream(path));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
	}
	private BasicFactory(){}
	public static BasicFactory getFactory(){
		return factory;
	}
	public <T> T getInstance(Class<T> clazz){
		try {
			String className = prop.getProperty(clazz.getSimpleName());
			Class<?> cls = Class.forName(className);
			return (T) cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
}
