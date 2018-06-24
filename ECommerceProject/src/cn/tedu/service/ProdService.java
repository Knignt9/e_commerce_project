package cn.tedu.service;

import java.util.List;

import cn.tedu.bean.Prod;
import cn.tedu.exception.MsgException;

public interface ProdService {
	public void addProd(Prod prod) throws MsgException;

	public List<Prod> findProdList();

	public void delProd(int id);

	public void changePnum(int pnum, int id);

	public Prod findProdById(int id);
	/**
	 * 根据userid来存储商品
	 */
	public void addProdToCart(Prod prod);
	/**
	 * 获取user购买的所有商品
	 * @param i 
	 * @return 所有商品的list集合；
	 */
	public List<Prod> findCartProdList(int i);
	/**
	 * 删除购物车中商品
	 * @param auto_id
	 */
	public void delCartProd(int auto_id);
	/**
	 * 根据用户选择的查询信息查询对应的商品
	 * @param name 商品名
	 * @param cname 商品种类名
	 * @param minprice 最小价格
	 * @param maxprice 最大价格
	 * @return 符合的商品的集合
	 */
	public List<Prod> findProdListByParam(String name, String cname,
			int minprice, int maxprice);

	public List<Prod> findProdListByCname(String cname);
}
