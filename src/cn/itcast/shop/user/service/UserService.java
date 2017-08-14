package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.MailUtils;
import cn.itcast.shop.utils.UUIDUtils;

/**
 * 用户模块业务层代码
 * @author xkx
 */
@Transactional
public class UserService {
	//注入UserDao
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//按用户名查询用户
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	//业务层完成用户注册
	public void save(User user) {
		user.setState(0);//0代表未激活，1代表已经激活
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
	}

	//业务层根据激活码查询用户
	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}

	//修改用户化的状态
	public void update(User existUser) {
		userDao. update(existUser);
	}

	//用户登录方法
	public User login(User user) {
		
		return userDao.login(user);
	}
}
