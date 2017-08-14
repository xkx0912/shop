package cn.itcast.shop.index.action;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ��ҳ���ʵ�Action
 * @author xkx
 *
 */
public class IndexAction extends ActionSupport{
/**
 * ִ�еķ�����ҳ�ķ���
 */
	public String execute(){
		List<Category> cList=categoryService.findAll();
		//��һ���������session��Χ
		ActionContext.getContext().getSession().put("cList", cList);
		//��ѯ������Ʒ
		List<Product> hList=productService.findHot();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("hList",hList);//���ֵջ
		
		//��ѯ������Ʒ
		List<Product> nList=productService.findNew();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
	//ע��һ�������categoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	//ע����Ʒ��Service(ProductService)
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	

}
