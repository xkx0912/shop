package cn.itcast.shop.categorysecond.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台二级分类管理的Action
 * @author xkx
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	//模型驱动使用的对象
	private CategorySecond categorySecond=new CategorySecond();
	//注入categorySecondService
	private CategorySecondService categorySecondService;
	
	//注入一级分类的categoryService
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//接收page
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public CategorySecond getModel() {
		return categorySecond;
	}

	//分页查询查询二级分类方法
	public String findAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findAll(page);
		//将pageBean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//跳转到	二级分类添加页面
	public String addPage(){
		//查询所有一级分类
		List<Category> cList=categoryService.findAll();
		//把数据显示到页面的下拉列表
		ActionContext.getContext().getValueStack().set("cList",cList);
		//页面跳转
		return "addPageSuccess";
	}
	
	//保存二级分类的方法
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	//删除二级分类的方法
	public String delete(){
		//如果级联删除，先查询
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//查询到后删除
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	//编辑二级分类
	public String edit(){
		//根据二级分类id查询二级分类的对象
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//查询所有的一级分类
		List<Category> cList=categoryService.findAll();
		//存入值栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	//修改二级分类
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
