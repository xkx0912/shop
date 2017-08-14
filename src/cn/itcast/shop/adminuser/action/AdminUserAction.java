package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台登陆的Action
 * @author xkx
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUser adminUser=new AdminUser();
	public AdminUser getModel() {
		return adminUser;
	}
	
	//注入service
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	/**
	 * 后台登陆方法
	 */
	public String login(){
		//调用后台的Service实现登陆
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser==null){
			//登录失败
			this.addActionError("用户名或密码错误,登录失败!");
			return "loginFail";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
}
