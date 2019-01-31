package tn.esprit.b2.esprit1718b2eventmanagement.mBeans.eventroom;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Product;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.ProductServicesLocal;

@ManagedBean
@ViewScoped
public class productviewbean {
	private String id;
	@EJB
	ProductServicesLocal productServicesLocal;
	private Product product;
	private List<Product> products;
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String id = request.getParameter("id");
		product=productServicesLocal.find(Integer.parseInt(id));
		setProducts(productServicesLocal.findAll());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
