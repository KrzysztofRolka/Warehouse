package pl.krolka.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pl.krolka.jsf.entities.Product;

@Stateless
public class ProductDAO {

	@PersistenceContext
	protected EntityManager entityManager;

	public List<Product> findAll() {
		TypedQuery<Product> query = entityManager.createQuery("SELECT s FROM Product s", Product.class);
		return query.getResultList();
	}

	public List<Product> findAllLowStock() {
		TypedQuery<Product> query = entityManager.createQuery("SELECT s FROM Product s WHERE s.minStock != -1",
				Product.class);

		List<Product> lowStockList = query.getResultList();

		for (Product product : query.getResultList()) {

			if ((product.getInStockA() + product.getInStockB() + product.getInStockC()
					+ product.getInStockD()) > product.getMinStock()) {
				lowStockList.remove(product);
			}
		}

		return lowStockList;
	}

	public void add(Product product) {
		entityManager.persist(product);
		entityManager.flush();
	}

	public void delete(int id) {
		Product productToRemove = entityManager.find(Product.class, id);
		entityManager.remove(productToRemove);
		entityManager.flush();
	}

	public void update(Product product) {
		entityManager.merge(product);
		entityManager.flush();
	}
}
