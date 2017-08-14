package cn.itcast.shop.index.action;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * @author xkx
 *
 */
public class IndexAction extends ActionSupport{
/**
 * 执行的访问首页的方法
 */
	public String execute(){
		List<Category> cList=categoryService.findAll();
		//将一级分类存入session范围
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品
		List<Product> hList=productService.findHot();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hList",hList);//获得值栈
		
		//查询最新商品
		List<Product> nList=productService.findNew();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
	//注入一级分类的categoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	//注入商品的Service(ProductService)
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	

}
