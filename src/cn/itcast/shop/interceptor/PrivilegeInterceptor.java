package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 后台权限校验的拦截器
 * @author xkx
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	//执行拦截的方法
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session中是否保存了后台用户信息
		AdminUser existAdminUser=(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if (existAdminUser==null) {
			//没有登录
			ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();//获得正在执行的Action
			actionSupport.addActionError("请先登录!");
			return "loginAdminFail";
		}else {
			//已经登陆，放行
			return actionInvocation.invoke();
		}
	}

}
