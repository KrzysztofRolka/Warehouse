package pl.warehouse.jsf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.idm.model.basic.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.warehouse.jsf.services.UsersService;

@Named
@SessionScoped
@NoArgsConstructor
public class EditUserController implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Inject
	UsersService usersService;
	
	@Inject
	private Identity identity;
	
	@Getter
	@Setter
	User currentUser;
	
	@PostConstruct
	public void OnSetup() {
		currentUser = (User) identity.getAccount();
	}
	
	public String updateUser() {
		try {
			usersService.updateUser(currentUser);
			currentUser = (User) identity.getAccount();
			return "edit_profile";
		} catch (Exception e) {
			return "edit_profile";
		}
	}
	
	public String deleteUser() {
		try {
			usersService.deleteUser(currentUser);
			this.identity.logout();
			return "/pages/login.xhtml?faces-redirect=true";
		} catch (Exception e) {
			return "edit_profile";
		}
	}
	
}
