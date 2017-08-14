package cn.itcast.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.user.vo.User;

/**
 * 用户模块持久层代码
 * @author xkx
 *
 */
public class UserDao extends HibernateDaoSupport{
	//按名称查询是否存在该用户
	public User findByUsername(String username){
		String sql=" FROM User WHERE username=?";
		List<User> list=this.getHibernateTemplate().find(sql, username);
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return null;
 	}

	//注册用户存入数据库代码实现
	public void save(User user) {
		this.getHibernateTemplate().save(user);
		
	}

	//根据激活码查询用户
	public User findByCode(String code) {
		String hql="from User where code=?";
		List<User> list=this.getHibernateTemplate().find(hql, code);
		if(list!=null &&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	//修改用户化的状态
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
		
	}

	//用户登录
	public User login(User user) {
		String hql="from User where username=? and password=? and state=?";
		List<User> list=this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
