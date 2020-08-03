package pl.warehouse.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pl.warehouse.jsf.entities.ProductEntity;

@Stateless
public class ProductDAO {

	@PersistenceContext
	protected EntityManager entityManager;

	public List<ProductEntity> findAll() {
		TypedQuery<ProductEntity> query = entityManager.createQuery("SELECT s FROM ProductEntity s", ProductEntity.class);
		return query.getResultList();
	}

	public List<ProductEntity> findAllLowStock() {
		TypedQuery<ProductEntity> query = entityManager.createQuery("SELECT s FROM ProductEntity s WHERE s.minStock != -1",
				ProductEntity.class);

		List<ProductEntity> lowStockList = query.getResultList();

		for (ProductEntity product : query.getResultList()) {

			if ((product.getInStockA() + product.getInStockB() + product.getInStockC()
					+ product.getInStockD()) > product.getMinStock()) {
				lowStockList.remove(product);
			}
		}

		return lowStockList;
	}
	
	public List<ProductEntity> findAllWithPrice() {
		TypedQuery<ProductEntity> query = entityManager.createQuery("SELECT s FROM ProductEntity s WHERE s.price > 0", ProductEntity.class);
		return query.getResultList();
	}


	public void add(ProductEntity product) {
		entityManager.persist(product);
		entityManager.flush();
	}

	public void delete(int id) {
		ProductEntity productToRemove = entityManager.find(ProductEntity.class, id);
		entityManager.remove(productToRemove);
		entityManager.flush();
	}

	public void update(ProductEntity product) {
		entityManager.merge(product);
		entityManager.flush();
	}
}
