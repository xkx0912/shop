package cn.itcast.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.categorysecond.dao.CategorySecondDao;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

/**
 * 二级分类管理业务层
 * @author xkx
 *
 */
@Transactional
public class CategorySecondService {

	//注入dao
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	//分页查询二级分类方法
	public PageBean<CategorySecond> findAll(Integer page) {
		PageBean<CategorySecond> pageBean=new PageBean<CategorySecond>();
		//设置当前页数
		pageBean.setPage(page);
		//每页显示记录数
		Integer limit=10;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount=categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据的集合
		int begin=(page-1)*limit;
		List<CategorySecond> list=categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//业务层保存二级分类的方法
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	
	//业务层根据二级分类id查询到二级分类
	public CategorySecond findByCsid(Integer csid) {
		
		return categorySecondDao.findByCsid(csid);
	}
	
	//删除二级分类业务层
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	//业务层修改二级分类方法
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	//业务层查询所有二级分类方法
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

	
	
}
