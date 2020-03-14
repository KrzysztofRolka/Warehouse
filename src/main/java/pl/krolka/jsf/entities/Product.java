package pl.krolka.jsf.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Column(name = "name")
	private String name;

	@Column(name = "part_numb")
	private String partNumb;

	@Column(name = "categoty_id")
	private int categoryId;

	@Column(name = "in_stock_a")
	private int inStockA;

	@Column(name = "in_stock_b")
	private int inStockB;

	@Column(name = "in_stock_c")
	private int inStockC;

	@Column(name = "in_stock_d")
	private int inStockD;

	@Column(name = "min_stock")
	private int minStock;

	@Column(name = "description")
	private int description;

	public Product() {

	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartNumb() {
		return partNumb;
	}

	public void setPartNumb(String partNumb) {
		this.partNumb = partNumb;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getInStockA() {
		return inStockA;
	}

	public void setInStockA(int inStockA) {
		this.inStockA = inStockA;
	}

	public int getInStockB() {
		return inStockB;
	}

	public void setInStockB(int inStockB) {
		this.inStockB = inStockB;
	}

	public int getInStockC() {
		return inStockC;
	}

	public void setInStockC(int inStockC) {
		this.inStockC = inStockC;
	}

	public int getInStockD() {
		return inStockD;
	}

	public void setInStockD(int inStockD) {
		this.inStockD = inStockD;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getDescription() {
		return description;
	}

	public void setDescription(int description) {
		this.description = description;
	}

}
