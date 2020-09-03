package pl.warehouse.jsf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.warehouse.jsf.dao.UserDAO;
import pl.warehouse.jsf.entities.UserEntity;

@Named
@SessionScoped
@NoArgsConstructor
public class EditUserController implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Inject
	UserDAO userDAO;
	
	@Inject
	private Identity identity;
	
	@Getter
	@Setter
	UserEntity currentUser;
	
	@PostConstruct
	public void OnSetup() {
		currentUser = userDAO.getById(identity.getAccount().getId());
	}
	
	public String updateUser() {
		try {
			userDAO.updateUser(currentUser);
			currentUser = userDAO.getById(identity.getAccount().getId());
			return "edit_profile";
		} catch (Exception e) {
			return "edit_profile";
		}
	}
}
