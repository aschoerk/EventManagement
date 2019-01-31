package tn.esprit.b2.esprit1718b2eventmanagement.infocenterBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Blog;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices.BlogServicesLocal;

@ManagedBean
@SessionScoped
public class BlogBean {
	@EJB
	private BlogServicesLocal blogServicesLocal;
	@EJB
	private CompanyServiceLocal companyServiceLocal;
	
	private Blog blog= new Blog();
	private List<Blog> blogs;
	private Company company = new Company();
	private List<Company> companys;
	private int id;
	
	@PostConstruct
	private void init(){
		blog = new Blog();
		blogs = blogServicesLocal.findAll();
		companys = companyServiceLocal.findAll();

	}
	
	public void doAddBlog(){
		//blogServicesLocal.update(blog);
		//blog.setCompany1(company);
		company = blogServicesLocal.findbyid(id);
		blog.setCompany1(company);
		blogServicesLocal.saveorupdate(blog);
		
		
		System.out.println("////////////"+id);
		for(Company c:companys)
		{
			System.out.println(c.getName());
		};
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public List<Company> getCompanys() {
		return companys;
	}

	public void setCompanys(List<Company> companys) {
		this.companys = companys;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
		
}
