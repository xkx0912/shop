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
 * ��̨��Ʒ�����Action
 * @author xkx
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{

	//ģ������ʹ�õĶ���
	private Product product=new Product();
	public Product getModel() {
		return product;
	}
	
	//ע����Ʒ��Service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//����page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	//ע����������Service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	//�ļ��ϴ���Ҫ�Ĳ���
	private File upload;//�ϴ����ļ�
	private String uploadFileName;//���ڽ����ļ��ϴ����ļ���
	private String uploadContextType;//�����ļ��ϴ����ļ�����
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	//����ҳ�Ĳ�ѯ��Ʒ��ִ�з���
	public String findAll(){
		//����service��ɲ�ѯ����
		PageBean<Product> pageBean=productService.findByPage(page);
		//���뵽ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}

	//�����Ʒҳ����ת
	public String addPage(){
		//��ѯ���ж�������
		List<CategorySecond> csList=categorySecondService.findAll();
		//���浽ֵջ
		ActionContext.getContext().getValueStack().set("csList", csList);
		//ҳ����ת
		return "addPageSuccess";
	}
	
	//������Ʒ�ķ���
	public String save() throws IOException{
		//����Service
		product.setPdate(new Date());
		if(upload!=null){
			//����ļ��ϴ��ľ���·��
			//String realPath="E://MyEclipse_workspace/shopProductImage";
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			
			//����һ���ļ�
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
		}
		product.setImage("products/"+uploadFileName);
		//�����ݱ��浽���ݿ�
		productService.save(product);
		return "saveSuccess";
	}
	
	//ɾ����Ʒ����
	public String delete(){
		//�Ȳ�ѯ����ɾ��
		product = productService.findByPid(product.getPid());
		//ɾ���ϴ���ͼƬ
		String path=product.getImage();
		if(path != null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);//ͼƬ����·��
			//String realPath="E:/MyEclipse_workspace/shopProductImage/"+path;
			File file=new File(realPath);
			file.delete();
		}
		//ɾ����Ʒ
		productService.delete(product);
		//ҳ����ת
		return "deleteSuccess";
	}
	
	//�༭��Ʒ
	public String edit(){
		//������Ʒpid��ѯ��Ʒ
		product=productService.findByPid(product.getPid());
		//��ѯ���ж�������
		List<CategorySecond> csList=categorySecondService.findAll();
		//���浽ֵջ
		ActionContext.getContext().getValueStack().set("csList", csList);
		//ҳ����ת
		return "editSuccess";
	}
	
	//�޸���Ʒ����
	public String update() throws IOException{
		//�޸���Ʒ���ݵ����ݿ�
		product.setPdate(new Date());
		if(upload != null){
			//��ɾ��ԭ����ͼƬ
			String path=product.getImage();
			File file=new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			
			//����ļ��ϴ��ľ���·��
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//����һ���ļ�
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
		}
		product.setImage("products/"+uploadFileName);
		productService.update(product);
		//ҳ����ת
		return "updateSuccess";
	}
}
