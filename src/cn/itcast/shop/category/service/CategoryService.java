package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

/**
 * һ������ҵ������
 * @author xkx
 *
 */
@Transactional
public class CategoryService {
//ע��categoryDao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//ҵ����ѯ����һ������ķ���
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}

	//ҵ������һ������ķ���
	public void save(Category category) {
		categoryDao.save(category);
	}

	//ҵ������cid��ѯһ������ķ���
	public Category findByCid(Integer cid) {
		//����Dao��
		return categoryDao.findByCid(cid);
	}

	//ҵ���ɾ���Լ�����
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	//��̨�޸�һ������
	public void update(Category category) {
		categoryDao.update(category);
	}
	
}
