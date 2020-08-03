package pl.warehouse.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pl.warehouse.jsf.entities.CategoryEntity;

@Stateless
public class CategoryDAO {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public List<CategoryEntity> findAll(){
		TypedQuery<CategoryEntity> query =  entityManager.createQuery("SELECT s FROM CategoryEntity s", CategoryEntity.class);
		 return query.getResultList();
	}
	public void add(CategoryEntity category) {
		entityManager.persist(category);
		entityManager.flush();
	}
	
	public void delete(int id) {
		CategoryEntity categoryToRemove = entityManager.find(CategoryEntity.class, id);
		entityManager.remove(categoryToRemove);
		entityManager.flush();
	}
	
	public void update(CategoryEntity category) {
		entityManager.merge(category);
		entityManager.flush();
	}

}
