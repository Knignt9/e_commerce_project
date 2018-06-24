package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.bean.Prod;
import cn.tedu.bean.ProdCategory;
import cn.tedu.utils.JDBCUtils;
import cn.tedu.utils.TransactionManager;

public class ProdDaoImpl implements ProdDao{

	public ProdCategory findProdCategoryByCname(String cname) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ProdCategory pc=new ProdCategory();
		try{
			conn=TransactionManager.getConnection();
			String sql="select * from prod_category where cname=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, cname);
			rs=ps.executeQuery();
			if(rs.next()){
				pc.setCname(cname);
				pc.setId(rs.getInt("id"));
			}else{
				pc=null;
			}
			return pc;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public void addProdCategory(ProdCategory pc) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="insert into prod_category values(null,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, pc.getCname());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public void addProd(Prod prod) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="insert into prod values(null,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			System.out.println(prod.getName()+":"+prod.getPrice());
			ps.setString(1, prod.getName());
			ps.setDouble(2, prod.getPrice());
			ps.setInt(3, prod.getCid());
			ps.setInt(4, prod.getPnum());
			ps.setString(5, prod.getImgurl());
			ps.setString(6, prod.getDescription());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public List<Prod> findProdList() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="select * from prod,prod_category where prod.cid = prod_category.id";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			List<Prod> list=new ArrayList<Prod>();
			while(rs.next()){
				Prod prod=new Prod();
				prod.setId(rs.getInt("prod.id"));
				prod.setName(rs.getString("prod.name"));
				prod.setPrice(rs.getDouble("prod.price"));
				prod.setCid(rs.getInt("prod.cid"));
				prod.setCname(rs.getString("prod_category.cname"));
				prod.setPnum(rs.getInt("pnum"));
				prod.setImgurl(rs.getString("imgurl"));
				prod.setDescription(rs.getString("description"));
				list.add(prod);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public Prod findProdById(int id) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Prod prod=new Prod();
		try{
			conn=TransactionManager.getConnection();
			String sql="select * from prod where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("name"));
				prod.setPrice(rs.getDouble("price"));
				prod.setCid(rs.getInt("cid"));
				prod.setPnum(rs.getInt("pnum"));
				prod.setImgurl(rs.getString("imgurl"));
				prod.setDescription(rs.getString("description"));
			}
			return prod;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public List<Prod> finProdByCid(int cid) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="select * from prod where cid=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cid);
			rs=ps.executeQuery();
			List<Prod> list=new ArrayList<Prod>();
			while(rs.next()){
				Prod prod=new Prod();
				prod.setId(rs.getInt("prod.id"));
				prod.setName(rs.getString("prod.name"));
				prod.setPrice(rs.getDouble("prod.price"));
				prod.setCid(rs.getInt("prod.cid"));
				prod.setPnum(rs.getInt("pnum"));
				prod.setImgurl(rs.getString("imgurl"));
				prod.setDescription(rs.getString("description"));
				list.add(prod);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public void delProdById(int id) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="delete from prod where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public void delProdCategoryById(int cid) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="delete from prod_category where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
		
	}

	public void changePnum(int pnum, int id) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="update prod set pnum = ? where id = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pnum);
			ps.setInt(2,id);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public void adProdToCart(Prod prod) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=TransactionManager.getConnection();
			String sql="insert into cart_prod values(?,?,?,?,?,?,?,null)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,prod.getId());
			ps.setString(2, prod.getName());
			ps.setDouble(3, prod.getPrice());
			ps.setInt(4, prod.getCid());
			ps.setInt(5, prod.getBuyNum());
			ps.setString(6,prod.getImgurl());
			ps.setString(7, prod.getDescription());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public List<Prod> findCartProdList(int id) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="select * from cart_prod where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			List<Prod> list=new ArrayList<Prod>();
			while(rs.next()){
				Prod prod=new Prod();
				prod.setId(rs.getInt("id"));
				prod.setAuto_id(rs.getInt("auto_id"));
				prod.setName(rs.getString("name"));
				prod.setPrice(rs.getDouble("price"));
				prod.setCid(rs.getInt("cid"));
				prod.setBuyNum(rs.getInt("buyNum"));
				prod.setImgurl(rs.getString("imgurl"));
				prod.setDescription(rs.getString("description"));
				list.add(prod);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public void delCartProd(int auto_id) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="delete from cart_prod where auto_id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, auto_id);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	public List<Prod> findProdListByParam(String name, String cname,
			int minprice, int maxprice) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=TransactionManager.getConnection();
			String sql="select * from prod p,prod_category c where p.cid=c.id and  p.name like ?  and c.cname=? and p.price between ? and ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,"%"+name+"%");
			ps.setString(2,cname);
			ps.setInt(3, minprice);
			ps.setInt(4, maxprice);
			rs=ps.executeQuery();
			List<Prod> list=new ArrayList<Prod>();
			while(rs.next()){
				Prod prod=new Prod();
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("name"));
				prod.setPrice(rs.getDouble("price"));
				prod.setCid(rs.getInt("cid"));
				prod.setImgurl(rs.getString("imgurl"));
				prod.setDescription(rs.getString("description"));
				list.add(prod);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
	}

	


}
