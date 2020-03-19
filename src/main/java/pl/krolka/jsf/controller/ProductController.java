package pl.krolka.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pl.krolka.jsf.dao.ProductDAO;
import pl.krolka.jsf.entities.Product;

@Named
@SessionScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProductDAO productDAO;
	
	private List<Product> products;
	
	private Product product;
	
	private Product productToUpdate;
	
	public ProductController() {
		product = new Product();
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

	public Product getProductToUpdate() {
		return productToUpdate;
	}
	
	@PostConstruct
	public void OnSetup() {
		products = productDAO.findAll();
	}
	
	public String addProduct() {
		productDAO.add(product);
		product = new Product();
		products = productDAO.findAll();
		return "products_list";
	}
	
	public String deleteProduct(int id) {
		productDAO.delete(id);
		products = productDAO.findAll();
		return "products_list";
	}

	public String updateProduct() {
		productDAO.update(productToUpdate);
		products = productDAO.findAll();
		productToUpdate = new Product();
		return "products_list";
	}
	
	public String onUpdate(Product product) {
		productToUpdate = product;
		return "update_product_form";
	}
	
}
