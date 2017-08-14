package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * ��̨Ȩ��У���������
 * @author xkx
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	//ִ�����صķ���
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//�ж�session���Ƿ񱣴��˺�̨�û���Ϣ
		AdminUser existAdminUser=(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if (existAdminUser==null) {
			//û�е�¼
			ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();//�������ִ�е�Action
			actionSupport.addActionError("���ȵ�¼!");
			return "loginAdminFail";
		}else {
			//�Ѿ���½������
			return actionInvocation.invoke();
		}
	}

}
