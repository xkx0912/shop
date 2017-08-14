package cn.itcast.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.categorysecond.dao.CategorySecondDao;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

/**
 * �����������ҵ���
 * @author xkx
 *
 */
@Transactional
public class CategorySecondService {

	//ע��dao
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	//��ҳ��ѯ�������෽��
	public PageBean<CategorySecond> findAll(Integer page) {
		PageBean<CategorySecond> pageBean=new PageBean<CategorySecond>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//ÿҳ��ʾ��¼��
		Integer limit=10;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount=categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//����ÿҳ��ʾ�����ݵļ���
		int begin=(page-1)*limit;
		List<CategorySecond> list=categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ��㱣���������ķ���
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	
	//ҵ�����ݶ�������id��ѯ����������
	public CategorySecond findByCsid(Integer csid) {
		
		return categorySecondDao.findByCsid(csid);
	}
	
	//ɾ����������ҵ���
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	//ҵ����޸Ķ������෽��
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	//ҵ����ѯ���ж������෽��
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

	
	
}
