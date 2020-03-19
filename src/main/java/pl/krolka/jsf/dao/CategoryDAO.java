package pl.krolka.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pl.krolka.jsf.entities.Category;

@Stateless
public class CategoryDAO {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public List<Category> findAll(){
		TypedQuery<Category> query =  entityManager.createQuery("SELECT s FROM Category s", Category.class);
		 return query.getResultList();
	}
	public void add(Category category) {
		entityManager.persist(category);
		entityManager.flush();
	}
	
	public void delete(int id) {
		Category categoryToRemove = entityManager.find(Category.class, id);
		entityManager.remove(categoryToRemove);
		entityManager.flush();
	}
	
	public void update(Category category) {
		entityManager.merge(category);
		entityManager.flush();
	}

}
