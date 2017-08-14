package cn.itcast.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 用户模块的Action类
 * @author xkx
 *
 */
public class userAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动使用的对象
	private User user=new User();
	
	//注入UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//接受验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/*
	 * 跳转到注册页面的执行方法
	 */
	public String registPage(){
		return "registPage";
	}
	
	/**
	 * AJAX校验用户名的执行方法
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		//调用service查询
		User existUser= userService.findByUsername(user.getUsername());
		//获得response对象
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(existUser!=null){
			//查询到了，用户名存在
			response.getWriter().print("<font color='red'>用户名已存在</font>");
		}else {
			//没有查询到该用户  用户名可以使用
			response.getWriter().print("<font color='green'>用户名可用</font>");
		}
		return NONE;
	}
	
	//实现父类接口的方法
	public User getModel() {
		return user;
	}
	/*
	 * 用户注册执行方法
	 */
	public String regist(){
		//判断验证码
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("验证码错误!");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功，请去邮箱激活!");
		return "msg";
	}
	
	/*
	 * 用户激活方法
	 */
	public String active(){
		//根据激活码查询用户
		User existUser = userService.findByCode(user.getCode());
		//判断
		if(existUser==null){
			//激活失败
			this.addActionMessage("激活失败:激活码错误!");
		}else {
			//激活成功
			//修改状态吗state
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功:请去登录!");
		}
		return "msg";
	}
	
	
	/**
	 * 跳转到登录页面
	 */
	public String loginPage(){
		return "loginPage";
	}
	
	/**
	 * 登录方法
	 */
	public String login(){
		User existUser=userService.login(user);
		if(existUser==null){
			//登录失败
			this.addActionError("登录失败:用户名或者密码错误或账号未激活!");
			return LOGIN;
		}else {
			//登录成功
			//将用户信息存入到Session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//页面跳转
			return "loginSuccess";
		}
	}
	/**
	 * 退出登录
	 */
	public String quit(){
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		//ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
