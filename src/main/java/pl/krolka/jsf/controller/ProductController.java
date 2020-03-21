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
	
	private List<Product> productsLowStock;
	
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
	

	public List<Product> getProductsLowStock() {
		return productsLowStock;
	}

	public Product getProductToUpdate() {
		return productToUpdate;
	}
	
	@PostConstruct
	public void OnSetup() {
		products = productDAO.findAll();
		productsLowStock = productDAO.findAllLowStock();
	}
	
	public String addProduct() {
		productDAO.add(product);
		product = new Product();
		products = productDAO.findAll();
		productsLowStock = productDAO.findAllLowStock();
		return "products_list";
	}
	
	public String deleteProduct(int id) {
		productDAO.delete(id);
		products = productDAO.findAll();
		productsLowStock = productDAO.findAllLowStock();
		return "products_list";
	}

	public String updateProduct() {
		productDAO.update(productToUpdate);
		products = productDAO.findAll();
		productsLowStock = productDAO.findAllLowStock();
		productToUpdate = new Product();
		return "products_list";
	}
	
	public String onUpdate(Product product) {
		productToUpdate = product;
		return "update_product_form";
	}
	
}
