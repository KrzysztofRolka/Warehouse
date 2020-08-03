package pl.warehouse.jsf.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	@Getter
	@Setter
	private int productId;

	@Column(name = "name")
	@Getter
	@Setter
	private String name;

	@Column(name = "part_numb")
	@Getter
	@Setter
	private String partNumb;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@Getter
	@Setter
	private CategoryEntity categoryId;

	@Column(name = "in_stock_a")
	@Getter
	@Setter
	private int inStockA;

	@Column(name = "in_stock_b")
	@Getter
	@Setter
	private int inStockB;

	@Column(name = "in_stock_c")
	@Getter
	@Setter
	private int inStockC;

	@Column(name = "in_stock_d")
	@Getter
	@Setter
	private int inStockD;

	@Column(name = "min_stock")
	@Getter
	@Setter
	private int minStock;

	@Column(name = "price")
	@Getter
	@Setter
	private int price;
	
	@Column(name = "required_item")
	@Getter
	@Setter
	private boolean requiredItem;
	
	@Column(name = "description")
	@Getter
	@Setter
	private String description;
	
	@Transient
	@Setter
	private int sumOfStocks;
	
	public int getSumOfStocks() {
		return calculateSumOfStocks();
	}
	
	@Transient
	@Setter
	private double priceWithVAT;		

	public double getPriceWithVAT() {
		return calculatePriceWithVat();
	}

	private int calculateSumOfStocks() {
		return this.inStockA + this.inStockB + this.inStockC + this.inStockD;
	}
	
	private double calculatePriceWithVat() {
		return this.price * 1.23; 
	}
	
}
