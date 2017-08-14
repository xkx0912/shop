package cn.itcast.shop.category.adminaction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理的Action
 * @author xkx
 *
 */
public class AdminCartgoryAction extends ActionSupport implements ModelDriven<Category>{
	
	//注入categoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//模型驱动使用的类
	private Category category=new Category();
	public Category getModel() {
		return category;
	}

	//执行查询所有一级分类的方法
	public String findAll(){
		//查询所有一级分类
		List<Category> cList=categoryService.findAll();
		//将集合数据显示到页面
		ActionContext.getContext().getValueStack().set("cList",cList);
		return "findAll";
	}
	
	//执行添加一级分类的方法
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	
	//执行删除一级分类的方法
	public String delete(){
		//先查询一级分类
		category=categoryService.findByCid(category.getCid());
		//再删除一级分类
		categoryService.delete(category);
		//页面跳转
		return "deleteSuccess";
	}
	
	//后台编辑一级分类的方法 
	public String edit(){
		//先查询一级分类
		category=categoryService.findByCid(category.getCid());
		//页面跳转
		return "editSuccess";
	}
	
	//后台修改一级分类的方法 
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}
