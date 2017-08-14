package cn.itcast.shop.product.action;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品的action对象
 * @author xkx
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//用于接收数据的模型驱动
	private Product product=new Product();
	//注入商品的service
	private ProductService productService;
	//接受分类的cid
	private Integer cid;
	//接受二级分类csid
	private Integer csid;
	
	//注入一级分类的CategoryService
	private CategoryService categoryService;
	//接受当前页
	private int page;
	
	public void setPage(int page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}

	
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getModel() {
		return product;
	}

	//根据商品的pid查询商品
	public String findByPid(){
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//根据分类id查询商品
	public String findByCid(){
		//List<Category> cList=categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);//根据一级分类带分页查询商品
		
		//将pageBean 存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//根据二级分类的csid查询商品
	public String findByCsid(){
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		//将pageBean 存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
