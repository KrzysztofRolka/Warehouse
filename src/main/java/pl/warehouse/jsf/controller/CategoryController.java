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
import pl.warehouse.jsf.dao.CategoryDAO;
import pl.warehouse.jsf.entities.CategoryEntity;

@Named
@SessionScoped
@NoArgsConstructor
public class CategoryController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private CategoryDAO categoryDAO;
	
	@Getter
	private List<CategoryEntity> categories;
	
	@Getter
	@Setter
	private CategoryEntity category;
	
	@Getter
	private CategoryEntity categoryToUpdate;
	
	@PostConstruct
	public void OnSetup() {
		category = new CategoryEntity();
		categories = categoryDAO.findAll();
	}
	
	public String addCategory() {
		
		try {
			categoryDAO.add(category);
			category = new CategoryEntity();
			categories = categoryDAO.findAll();
			FacesMessages.info("Kategoria poprawnie dodana","");
			return "categories_list";
		} catch (Exception e) {
			category = new CategoryEntity();
			FacesMessages.error("Blad dodawania kategorii!", "");
			return "categories_list";
		}
	}
	
	public void selectCategoryToDelete(CategoryEntity category) {
		this.category = category;
	}

	public String deleteCategory() {
		try {
			categoryDAO.delete(category.getCategoryId());
			categories = categoryDAO.findAll();
			category = new CategoryEntity();
			FacesMessages.info("Kategoria poprawnie usunieta","");
			return "categories_list";	
		}catch (Exception e) {
			category = new CategoryEntity();
			FacesMessages.error("Blad usuwania! Kategoria uzywana jest w co najmniej jednym produkcie!", "");
			return "categories_list";	
		}
	}

	public String updateCategory() {
		try {
			categoryDAO.update(categoryToUpdate);
			categoryToUpdate = new CategoryEntity();
			categories = categoryDAO.findAll();
			FacesMessages.info("Kategoria poprawnie zedytowana","");
			return "categories_list";
		} catch (Exception e) {
			categoryToUpdate = new CategoryEntity();
			FacesMessages.error("Blad edytowania kategorii!", "");
			return "categories_list";
		}
	}
	
	public String onUpdate(CategoryEntity category) {
		categoryToUpdate = category;
		return "update_category_form";
	}
	

}
