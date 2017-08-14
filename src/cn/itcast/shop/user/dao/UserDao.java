package cn.itcast.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.user.vo.User;

/**
 * �û�ģ��־ò����
 * @author xkx
 *
 */
public class UserDao extends HibernateDaoSupport{
	//�����Ʋ�ѯ�Ƿ���ڸ��û�
	public User findByUsername(String username){
		String sql=" FROM User WHERE username=?";
		List<User> list=this.getHibernateTemplate().find(sql, username);
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return null;
 	}

	//ע���û��������ݿ����ʵ��
	public void save(User user) {
		this.getHibernateTemplate().save(user);
		
	}

	//���ݼ������ѯ�û�
	public User findByCode(String code) {
		String hql="from User where code=?";
		List<User> list=this.getHibernateTemplate().find(hql, code);
		if(list!=null &&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	//�޸��û�����״̬
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
		
	}

	//�û���¼
	public User login(User user) {
		String hql="from User where username=? and password=? and state=?";
		List<User> list=this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
