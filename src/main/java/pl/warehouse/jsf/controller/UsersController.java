package pl.warehouse.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.model.basic.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.warehouse.jsf.dao.UserDAO;
import pl.warehouse.jsf.services.UsersService;

@Named
@SessionScoped
@NoArgsConstructor
public class UsersController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserDAO userDAO;
	
	@Inject
	private UsersService usersService;
	
	@Getter
	private List<User> users;

	@Getter
	@Setter
	private User newUser;
	
	@Getter
	@Setter
	private User userToUpdate;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter	
	private Boolean isAdmin;
	
	@Getter
	@Setter
	private Boolean isServiceman;
	
	@Getter
	@Setter
	private Boolean isCustomer;
	
	@PostConstruct
	public void OnSetup() {
		users = userDAO.getUsersList();
		newUser = new User();
		userToUpdate = new User();
		password = new String();
		
		isAdmin = new Boolean(false);
		isServiceman = new Boolean(false);
		isCustomer = new Boolean(false);
	}
	

	public String createNewUser()
	{
		try {
		    
		    usersService.saveNewUser(newUser, password, isAdmin, isServiceman, isCustomer);
		    
			users = userDAO.getUsersList();
			
			newUser = new User();
			password = new String();
			
			return "manage_users";
		} catch (Exception e) {
			
			newUser = new User();
			password = new String();
			
			return "manage_users";
		}
		
	}
	public String onUpdate(User user) {
		userToUpdate = user;
		return "update_user_form";
	}

	public String updateUser() {
		try {
			usersService.updateUser(userToUpdate, isAdmin, isServiceman, isCustomer);
			
			users = userDAO.getUsersList();
			userToUpdate = new User();
			return "manage_users";
		} catch (Exception e) {
			
			userToUpdate = new User();
			return "manage_users";
		}
	}
	
	public String deleteUser(User user) {
		try {
			usersService.deleteUser(user);
			users = userDAO.getUsersList();
			return "manage_users";
		} catch (Exception e) {
			return "manage_users";
		}
	}
	
	public String changePassForUser(User user) {
		userToUpdate = user;
		return "edit_user_pass.xhtml";
	}
	
}
