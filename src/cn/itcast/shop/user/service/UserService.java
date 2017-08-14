package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.MailUtils;
import cn.itcast.shop.utils.UUIDUtils;

/**
 * �û�ģ��ҵ������
 * @author xkx
 */
@Transactional
public class UserService {
	//ע��UserDao
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//���û�����ѯ�û�
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	//ҵ�������û�ע��
	public void save(User user) {
		user.setState(0);//0����δ���1�����Ѿ�����
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//���ͼ����ʼ�
		MailUtils.sendMail(user.getEmail(), code);
	}

	//ҵ�����ݼ������ѯ�û�
	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}

	//�޸��û�����״̬
	public void update(User existUser) {
		userDao. update(existUser);
	}

	//�û���¼����
	public User login(User user) {
		
		return userDao.login(user);
	}
}
