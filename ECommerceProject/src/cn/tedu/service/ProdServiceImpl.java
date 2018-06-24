package cn.tedu.service;


import java.util.List;

import cn.tedu.bean.Prod;
import cn.tedu.bean.ProdCategory;
import cn.tedu.dao.ProdDao;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.utils.TransactionManager;

public class ProdServiceImpl implements ProdService{
	private ProdDao dao=BasicFactory.getFactory().getInstance(ProdDao.class);

	public void addProd(Prod prod) throws MsgException {
		try{
			TransactionManager.startTran();
			String cname = prod.getCname();
			ProdCategory findpc=dao.findProdCategoryByCname(cname);
			int cid=0;
			if(findpc==null){
				ProdCategory pc=new ProdCategory();
				pc.setCname(cname);
				dao.addProdCategory(pc);
				ProdCategory findpc2=dao.findProdCategoryByCname(cname);
				cid=findpc2.getId();
			}else{
				cid=findpc.getId();
			}
			prod.setCid(cid);
			dao.addProd(prod);
			TransactionManager.commitTran();
		}catch(Exception e){
			TransactionManager.rollbackTran();
			e.printStackTrace();
		}finally{
			TransactionManager.release();
		}
	}

	public List<Prod> findProdList() {
		return dao.findProdList();
	}

	public void delProd(int id) {
		try{
		TransactionManager.startTran();
		Prod prod=dao.findProdById(id);
		int cid = prod.getCid();
		List<Prod> list=dao.finProdByCid(cid);
		if(list.size()>1){
			dao.delProdById(id);
		}else{
			dao.delProdById(id);
			dao.delProdCategoryById(cid);
		}
		TransactionManager.commitTran();
		}catch(Exception e){
			TransactionManager.rollbackTran();
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			TransactionManager.release();
		}
	}

	public void changePnum(int pnum, int id) {
		
		dao.changePnum(pnum,id);
	}

	public Prod findProdById(int id) {
		return dao.findProdById(id);
	}

	public void addProdToCart(Prod prod) {
		dao.adProdToCart(prod);
	}

	public List<Prod> findCartProdList(int id) {
		return dao.findCartProdList(id);
	}

	public void delCartProd(int auto_id) {
		dao.delCartProd(auto_id);
	}

	public List<Prod> findProdListByParam(String name, String cname,
			int minprice, int maxprice) {
		
		return dao.findProdListByParam(name, cname,minprice,maxprice);
	}

	public List<Prod> findProdListByCname(String cname) {
		ProdCategory pc = dao.findProdCategoryByCname(cname);
		List<Prod> list=dao.finProdByCid(pc.getId());
		return list;
	}
	
	
}
