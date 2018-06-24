package cn.tedu.dao;

import java.util.List;

import cn.tedu.bean.Prod;
import cn.tedu.bean.ProdCategory;

public interface ProdDao {

	ProdCategory findProdCategoryByCname(String cname);

	void addProdCategory(ProdCategory pc);

	void addProd(Prod prod);

	List<Prod> findProdList();

	Prod findProdById(int id);

	List<Prod> finProdByCid(int cid);

	void delProdById(int id);

	void delProdCategoryById(int cid);

	void changePnum(int pnum, int id);
	/**
	 * 将商品添加到购物车
	 * @param prod 加入和购物车的商品信息
	 */
	void adProdToCart(Prod prod);
	/**
	 * 
	 * @param id 
	 * @return 买家购买的所有商品信息
	 */
	List<Prod> findCartProdList(int id);

	void delCartProd(int auto_id);

	List<Prod> findProdListByParam(String name, String cname, int minprice,
			int maxprice);
	




}
