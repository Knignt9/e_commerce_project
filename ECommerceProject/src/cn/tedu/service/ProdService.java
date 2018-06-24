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
	 * ����userid���洢��Ʒ
	 */
	public void addProdToCart(Prod prod);
	/**
	 * ��ȡuser�����������Ʒ
	 * @param i 
	 * @return ������Ʒ��list���ϣ�
	 */
	public List<Prod> findCartProdList(int i);
	/**
	 * ɾ�����ﳵ����Ʒ
	 * @param auto_id
	 */
	public void delCartProd(int auto_id);
	/**
	 * �����û�ѡ��Ĳ�ѯ��Ϣ��ѯ��Ӧ����Ʒ
	 * @param name ��Ʒ��
	 * @param cname ��Ʒ������
	 * @param minprice ��С�۸�
	 * @param maxprice ���۸�
	 * @return ���ϵ���Ʒ�ļ���
	 */
	public List<Prod> findProdListByParam(String name, String cname,
			int minprice, int maxprice);

	public List<Prod> findProdListByCname(String cname);
}
