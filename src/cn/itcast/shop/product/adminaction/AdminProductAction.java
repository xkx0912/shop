package cn.itcast.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理的Action
 * @author xkx
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{

	//模型驱动使用的对象
	private Product product=new Product();
	public Product getModel() {
		return product;
	}
	
	//注入商品的Service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//接收page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	//注入二级分类的Service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	//文件上传需要的参数
	private File upload;//上传的文件
	private String uploadFileName;//用于接收文件上传的文件名
	private String uploadContextType;//接收文件上传的文件类型
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	//带分页的查询商品的执行方法
	public String findAll(){
		//调用service完成查询操作
		PageBean<Product> pageBean=productService.findByPage(page);
		//存入到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}

	//添加商品页面跳转
	public String addPage(){
		//查询所有二级分类
		List<CategorySecond> csList=categorySecondService.findAll();
		//保存到值栈
		ActionContext.getContext().getValueStack().set("csList", csList);
		//页面跳转
		return "addPageSuccess";
	}
	
	//保存商品的方法
	public String save() throws IOException{
		//调用Service
		product.setPdate(new Date());
		if(upload!=null){
			//获得文件上传的绝对路径
			//String realPath="E://MyEclipse_workspace/shopProductImage";
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			
			//创建一个文件
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
		}
		product.setImage("products/"+uploadFileName);
		//将数据保存到数据库
		productService.save(product);
		return "saveSuccess";
	}
	
	//删除商品方法
	public String delete(){
		//先查询，再删除
		product = productService.findByPid(product.getPid());
		//删除上传的图片
		String path=product.getImage();
		if(path != null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);//图片绝对路径
			//String realPath="E:/MyEclipse_workspace/shopProductImage/"+path;
			File file=new File(realPath);
			file.delete();
		}
		//删除商品
		productService.delete(product);
		//页面跳转
		return "deleteSuccess";
	}
	
	//编辑商品
	public String edit(){
		//根据商品pid查询商品
		product=productService.findByPid(product.getPid());
		//查询所有二级分类
		List<CategorySecond> csList=categorySecondService.findAll();
		//保存到值栈
		ActionContext.getContext().getValueStack().set("csList", csList);
		//页面跳转
		return "editSuccess";
	}
	
	//修改商品方法
	public String update() throws IOException{
		//修改商品数据到数据库
		product.setPdate(new Date());
		if(upload != null){
			//先删除原来的图片
			String path=product.getImage();
			File file=new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			
			//获得文件上传的绝对路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
		}
		product.setImage("products/"+uploadFileName);
		productService.update(product);
		//页面跳转
		return "updateSuccess";
	}
}
