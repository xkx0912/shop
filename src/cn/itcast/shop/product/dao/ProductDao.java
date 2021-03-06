package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.org.apache.bcel.internal.generic.Select;

import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * 商品持久层操作
 * @author xkx
 *
 */
public class ProductDao extends HibernateDaoSupport {

	//首页上热门商品查询
	public List<Product> findHot() {
		//使用离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//查询热门商品  条件式is_hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒叙排序热门商品进行输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	//首页上最新商品查询
	public List<Product> findNew() {
		//使用离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//倒叙排序最新商品进行输出
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}

	//根据商品的pid查询商品信息
	public Product findByPid(Integer pid) {
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	//根据商品的分类id查询商品的个数
	public int findCountCid(Integer cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//根据分类的id查询商品的集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		//String hql="select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2";
		//select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ? 		//hql写法
		String hql="select p from  Product p join p.categorySecond cs join cs.category c where c.cid=?";
		//分页的另一种写法
		List<Product> list= this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	//根据二级分类查询个数
	public int findCountCsid(Integer csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,csid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//根据二级分类的csid查询商品
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	//DAO统计所有商品个数
	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPage(int begin, int limit) {
		String hql="from Product order by pdate desc";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null,begin,limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	//DAO层保存商品的方法
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	//DAO层删除商品的方法
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	//DAO层完成商品的修改
	public void update(Product product) {
	this.getHibernateTemplate().update(product);
	}

}
