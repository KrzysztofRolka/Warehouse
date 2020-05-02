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
	
	private List<Product> productsWithPrice;

	private Product product;
	
	private Product productToUpdate;
	
	private Product productDetail;
	
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
	

	public List<Product> getProductsWithPrice() {
		return productsWithPrice;
	}

	public Product getProductToUpdate() {
		return productToUpdate;
	}
	
	
	public Product getProductDetail() {
		return productDetail;
	}

	@PostConstruct
	public void OnSetup() {
		products = productDAO.findAll();
		productsLowStock = productDAO.findAllLowStock();
		productsWithPrice = productDAO.findAllWithPrice();
	}
	
	public String addProduct() {
		productDAO.add(product);
		product = new Product();
		products = productDAO.findAll();
		productsLowStock = productDAO.findAllLowStock();
		productsWithPrice = productDAO.findAllWithPrice();
		return "products_list";
	}
	
	public String deleteProduct(int id) {
		productDAO.delete(id);
		products = productDAO.findAll();
		productsLowStock = productDAO.findAllLowStock();
		productsWithPrice = productDAO.findAllWithPrice();
		return "products_list";
	}

	public String updateProduct() {
		productDAO.update(productToUpdate);
		products = productDAO.findAll();
		productsLowStock = productDAO.findAllLowStock();
		productsWithPrice = productDAO.findAllWithPrice();
		productToUpdate = new Product();
		return "products_list";
	}
	
	public String onUpdate(Product product) {
		productToUpdate = product;
		return "update_product_form";
	}
	
	public String showDetail(Product product) {
		productDetail = product;
		return "detail_product";
	}
	
}
