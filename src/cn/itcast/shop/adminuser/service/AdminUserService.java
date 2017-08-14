package cn.itcast.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.adminuser.dao.AdminUserDao;
import cn.itcast.shop.adminuser.vo.AdminUser;

/**
 * 后台登陆的业务层代码
 * @author xkx
 *
 */
@Transactional
public class AdminUserService {

	//注入AdminUserDao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	//业务层调用后台DAO实现登录
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
