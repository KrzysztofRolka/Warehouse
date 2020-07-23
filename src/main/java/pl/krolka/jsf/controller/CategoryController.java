package pl.krolka.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.bootsfaces.utils.FacesMessages;
import pl.krolka.jsf.dao.CategoryDAO;
import pl.krolka.jsf.entities.Category;

@Named
@SessionScoped
public class CategoryController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private CategoryDAO categoryDAO;
	
	private List<Category> categories;
	
	private Category category;
	
	private Category categoryToUpdate;
	
	public CategoryController() {
		category = new Category();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public Category getCategoryToUpdate() {
		return categoryToUpdate;
	}
	
	@PostConstruct
	public void OnSetup() {
		category = new Category();
		categories = categoryDAO.findAll();
	}
	
	public String addCategory() {
		categoryDAO.add(category);
		category = new Category();
		categories = categoryDAO.findAll();
		return "categories_list";
	}
	
	public void selectCategoryToDelete(Category category) {
		this.category = category;
	}

	public String deleteCategory() {
		try {
			categoryDAO.delete(category.getCategoryId());
			categories = categoryDAO.findAll();
			category = new Category();
			FacesMessages.info("Kategoria poprawnie usunieta","");
			return "categories_list";	
		}catch (Exception e) {
			category = new Category();
			FacesMessages.error("Blad usuwania! Kategoria uzywana jest w co najmniej jednym produkcie!", "Something has gone <strong>wrong</strong>.");
			return "categories_list";	
		}
		
		
	}

	public String updateCategory() {
		categoryDAO.update(categoryToUpdate);
		categories = categoryDAO.findAll();
		return "categories_list";
	}
	
	public String onUpdate(Category category) {
		categoryToUpdate = category;
		return "update_category_form"; //TO EDIT
	}
	

}
