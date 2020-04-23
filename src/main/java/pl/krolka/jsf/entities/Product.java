package pl.krolka.jsf.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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

	@ManyToOne 
	@JoinColumn(name = "category_id")
	private Category categoryId;

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

	@Column(name = "price")
	private int price;
	
	@Column(name = "required_item")
	private boolean requiredItem;
	
	@Column(name = "description")
	private String description;
	
	@Transient
	private int sumOfStocks;
	
	@Transient
	private double priceWithVAT;
	
	
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

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public boolean isRequiredItem() {
		return requiredItem;
	}

	public void setRequiredItem(boolean requiredItem) {
		this.requiredItem = requiredItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSumOfStocks() {
		return calculateSumOfStocks();
	}

	public void setSumOfStocks(int sumOfStocks) {
		this.sumOfStocks = sumOfStocks;
	}
	

	public double getPriceWithVAT() {
		return calculatePriceWithVat();
	}

	public void setPriceWithVAT(double priceWithVAT) {
		this.priceWithVAT = priceWithVAT;
	}

	private int calculateSumOfStocks() {
		return this.inStockA + this.inStockB + this.inStockC + this.inStockD;
	}
	
	private double calculatePriceWithVat() {
		return this.price * 1.23; 
	}
	
}
