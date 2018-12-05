package com.gx.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.gx.constant.Constant;
import com.gx.entity.Category;
import com.gx.entity.Product;
import com.gx.service.ProductService;
import com.gx.utils.BeanFactory;
import com.gx.utils.UUIDUtils;
import com.gx.utils.UploadUtils;

/**
 * 后台添加商品
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//ProductService productService = new ProductServiceImpl();
	ProductService productService = (ProductService)BeanFactory.getBean("ProductService");

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1、使用fileuload保存图片，将商品的信息放入到map中
			//创建map，存放商品的信息
			Map<String , Object> map = new HashMap<String, Object>();
			//1.1创建磁盘文件项工厂（设置临时文件的的大小和位置）
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//1.2创建核心上传对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			//1.3、解析request
			List<FileItem> list = upload.parseRequest(request);
			//1.4、遍历list 获取每一个文件项
			for (FileItem fi : list) {
				//获取name属性值
				String key = fi.getFieldName();
				//判断是否是普通的上传组件
				if(fi.isFormField()){
					//普通的
					map.put(key, fi.getString("utf-8"));
				}else{
					//文件
					//获取文件的名称
					String name = fi.getName();
					//获取文件的真实名称
					String realName = UploadUtils.getRealName(name);
					//获取文件的随机名称
					String uuidName = UploadUtils.getUUIDName(realName);
					//获取随机目录
					String dir = UploadUtils.getDir();
					//获取文件内容（输入流）
					InputStream is = fi.getInputStream();
					//创建输出流
					//获取products文件夹目录的真实路径
					String productPath = getServletContext().getRealPath("/products");
					//创建随机目录
					File dirFile = new File(productPath,dir);
					if(!dirFile.exists()){
						dirFile.mkdirs();
					}
					FileOutputStream os = new FileOutputStream(new File(dirFile,uuidName));
					//对考流
					IOUtils.copy(is, os);
					//释放资源
					os.close();
					is.close();
					//删除临时文件
					fi.delete();
					//将商品的路径放入map中
					map.put(key, "products"+dir+"/"+uuidName);
				}
			}
			//3、封装product对象
			Product product = new Product(); 
			//手动封装pid，pdate，pflog（上架），cotegory
			map.put("pid", UUIDUtils.getId());
			map.put("pdate", new Date());
			map.put("pflag", Constant.PRODUCT_IS_UP);
			BeanUtils.populate(product, map);
			Category category = new Category();
			category.setCid((String)map.get("cid"));
			product.setCategory(category);
			//4、调用service 完成保存
			productService.save(product);
			//5、重定向
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=findByPage");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存商品失败！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
