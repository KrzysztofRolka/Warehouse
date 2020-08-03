package pl.warehouse.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import pl.warehouse.jsf.dao.ProductDAO;
import pl.warehouse.jsf.entities.ProductEntity;

@Named
@SessionScoped
@NoArgsConstructor
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProductDAO productDAO;

	@Getter
	@Setter
	private ProductEntity product;
	
	@Getter
	private List<ProductEntity> products;

	@Getter
	private List<ProductEntity> productsLowStock;

	@Getter
	private List<ProductEntity> productsWithPrice;

	@Getter
	private ProductEntity productToUpdate;

	@Getter
	private ProductEntity productDetail;

	@PostConstruct
	public void OnSetup() {
		product = new ProductEntity();
		products = productDAO.findAll();
		productToUpdate = new ProductEntity();
		productsLowStock = productDAO.findAllLowStock();
		productsWithPrice = productDAO.findAllWithPrice();
	}

	public String addProduct() {
		try {
			productDAO.add(product);
			product = new ProductEntity();
			products = productDAO.findAll();
			productsLowStock = productDAO.findAllLowStock();
			productsWithPrice = productDAO.findAllWithPrice();
			FacesMessages.info("Produkt poprawnie dodany", "");
			return "products_list";
		} catch (Exception e) {
			product = new ProductEntity();
			FacesMessages.error("Blad dodawania produktu!", "");
			return "products_list";
		}

	}

	public void selectProductToDelete(ProductEntity product) {
		this.product = product;
	}

	public String deleteProduct() {

		try {
			productDAO.delete(product.getProductId());
			products = productDAO.findAll();
			productsLowStock = productDAO.findAllLowStock();
			productsWithPrice = productDAO.findAllWithPrice();
			product = new ProductEntity();
			FacesMessages.info("Produkt poprawnie usuniety", "");
			return "products_list";
		} catch (Exception e) {
			product = new ProductEntity();
			FacesMessages.error("Blad usuwania produktu!", "");
			return "products_list";
		}
	}
	
	public void fastUpdate(ProductEntity product) {
		productToUpdate = product;
		updateProduct();
	}
	

	public String updateProduct() {
		try {
			productDAO.update(productToUpdate);
			products = productDAO.findAll();
			productsLowStock = productDAO.findAllLowStock();
			productsWithPrice = productDAO.findAllWithPrice();
			productToUpdate = new ProductEntity();
			FacesMessages.info("Produkt poprawnie zedytowany", "");
			return "products_list";
		} catch (Exception e) {
			FacesMessages.error("Blad edycji produktu!", "");
			productToUpdate = new ProductEntity();
			return "products_list";
		}
	}

	public String onUpdate(ProductEntity product) {
		productToUpdate = product;
		return "update_product_form";
	}

	public String showDetail(ProductEntity product) {
		productDetail = product;
		return "detail_product";
	}

}
