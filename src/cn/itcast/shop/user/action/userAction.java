package cn.itcast.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * �û�ģ���Action��
 * @author xkx
 *
 */
public class userAction extends ActionSupport implements ModelDriven<User>{
	//ģ������ʹ�õĶ���
	private User user=new User();
	
	//ע��UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//������֤��
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/*
	 * ��ת��ע��ҳ���ִ�з���
	 */
	public String registPage(){
		return "registPage";
	}
	
	/**
	 * AJAXУ���û�����ִ�з���
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		//����service��ѯ
		User existUser= userService.findByUsername(user.getUsername());
		//���response����
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(existUser!=null){
			//��ѯ���ˣ��û�������
			response.getWriter().print("<font color='red'>�û����Ѵ���</font>");
		}else {
			//û�в�ѯ�����û�  �û�������ʹ��
			response.getWriter().print("<font color='green'>�û�������</font>");
		}
		return NONE;
	}
	
	//ʵ�ָ���ӿڵķ���
	public User getModel() {
		return user;
	}
	/*
	 * �û�ע��ִ�з���
	 */
	public String regist(){
		//�ж���֤��
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("��֤�����!");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("ע��ɹ�����ȥ���伤��!");
		return "msg";
	}
	
	/*
	 * �û������
	 */
	public String active(){
		//���ݼ������ѯ�û�
		User existUser = userService.findByCode(user.getCode());
		//�ж�
		if(existUser==null){
			//����ʧ��
			this.addActionMessage("����ʧ��:���������!");
		}else {
			//����ɹ�
			//�޸�״̬��state
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("����ɹ�:��ȥ��¼!");
		}
		return "msg";
	}
	
	
	/**
	 * ��ת����¼ҳ��
	 */
	public String loginPage(){
		return "loginPage";
	}
	
	/**
	 * ��¼����
	 */
	public String login(){
		User existUser=userService.login(user);
		if(existUser==null){
			//��¼ʧ��
			this.addActionError("��¼ʧ��:�û����������������˺�δ����!");
			return LOGIN;
		}else {
			//��¼�ɹ�
			//���û���Ϣ���뵽Session��
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//ҳ����ת
			return "loginSuccess";
		}
	}
	/**
	 * �˳���¼
	 */
	public String quit(){
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		//ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
