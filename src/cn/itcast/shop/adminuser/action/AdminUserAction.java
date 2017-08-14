package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨��½��Action
 * @author xkx
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUser adminUser=new AdminUser();
	public AdminUser getModel() {
		return adminUser;
	}
	
	//ע��service
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	/**
	 * ��̨��½����
	 */
	public String login(){
		//���ú�̨��Serviceʵ�ֵ�½
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser==null){
			//��¼ʧ��
			this.addActionError("�û������������,��¼ʧ��!");
			return "loginFail";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
}
