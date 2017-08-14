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
 * ��Ʒ��action����
 * @author xkx
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//���ڽ������ݵ�ģ������
	private Product product=new Product();
	//ע����Ʒ��service
	private ProductService productService;
	//���ܷ����cid
	private Integer cid;
	//���ܶ�������csid
	private Integer csid;
	
	//ע��һ�������CategoryService
	private CategoryService categoryService;
	//���ܵ�ǰҳ
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

	//������Ʒ��pid��ѯ��Ʒ
	public String findByPid(){
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//���ݷ���id��ѯ��Ʒ
	public String findByCid(){
		//List<Category> cList=categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);//����һ���������ҳ��ѯ��Ʒ
		
		//��pageBean ����ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//���ݶ��������csid��ѯ��Ʒ
	public String findByCsid(){
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		//��pageBean ����ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
