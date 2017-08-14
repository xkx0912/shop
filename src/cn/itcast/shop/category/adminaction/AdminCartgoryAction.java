package cn.itcast.shop.category.adminaction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨һ����������Action
 * @author xkx
 *
 */
public class AdminCartgoryAction extends ActionSupport implements ModelDriven<Category>{
	
	//ע��categoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//ģ������ʹ�õ���
	private Category category=new Category();
	public Category getModel() {
		return category;
	}

	//ִ�в�ѯ����һ������ķ���
	public String findAll(){
		//��ѯ����һ������
		List<Category> cList=categoryService.findAll();
		//������������ʾ��ҳ��
		ActionContext.getContext().getValueStack().set("cList",cList);
		return "findAll";
	}
	
	//ִ�����һ������ķ���
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	
	//ִ��ɾ��һ������ķ���
	public String delete(){
		//�Ȳ�ѯһ������
		category=categoryService.findByCid(category.getCid());
		//��ɾ��һ������
		categoryService.delete(category);
		//ҳ����ת
		return "deleteSuccess";
	}
	
	//��̨�༭һ������ķ��� 
	public String edit(){
		//�Ȳ�ѯһ������
		category=categoryService.findByCid(category.getCid());
		//ҳ����ת
		return "editSuccess";
	}
	
	//��̨�޸�һ������ķ��� 
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}
