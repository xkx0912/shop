package cn.itcast.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;
/**
 * ��Ʒ��ҵ������
 * @author xkx
 *
 */
@Transactional
public class ProductService {
	//ע��ProductDao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		
		return productDao.findHot();
	}

	//��ҳ��������Ʒ��ѯ
	public List<Product> findNew() {
		
		return productDao.findNew();
	}

	//������Ʒ��pid����ѯ��Ʒ
	public Product findByPid(Integer pid) {
		
		return productDao.findByPid(pid);
	}

	//����һ������cid����ҳ�Ĳ�ѯ��Ʒ
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ����Ʒ����
		int limit=8;
		pageBean.setLimit(limit);
		//�ܼ�¼��
		int totalCount =0;
		totalCount=productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//��ҳ��
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if(totalCount % limit == 0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ�����ݵļ���:
		//���Ŀ�ʼ:
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//���ݶ��������csid��ѯ��Ʒ
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ����Ʒ����
		int limit=8;
		pageBean.setLimit(limit);
		//�ܼ�¼��
		int totalCount =0;
		totalCount=productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//��ҳ��
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if(totalCount % limit == 0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ�����ݵļ���:
		//���Ŀ�ʼ:
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ����ѯ��Ʒ����ҳ�ķ���
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ����Ʒ����
		int limit=10;
		pageBean.setLimit(limit);
		//�ܼ�¼��
		int totalCount =0;
		totalCount=productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��ҳ��
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if(totalCount % limit == 0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ�����ݵļ���:
		//���Ŀ�ʼ:
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPage(begin,limit);
		pageBean.setList(list);			
		return pageBean;
	}

	//ҵ��㱣����Ʒ����
	public void save(Product product) {
		productDao.save(product);
	}

	//ҵ���ɾ����Ʒ�ķ���
	public void delete(Product product) {
		productDao.delete(product);
	}

	//ҵ����޸���Ʒ
	public void update(Product product) {
		productDao.update(product);
	}
	
}
