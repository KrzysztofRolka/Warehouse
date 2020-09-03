package pl.warehouse.jsf.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.Account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import pl.warehouse.jsf.services.IdentityService;

@Named
@RequestScoped
@NoArgsConstructor
public class ChangePasswordController implements Serializable {

	private static final long serialVersionUID = 1L;


	@Inject
	private IdentityService identityService;

	@Inject
	private Identity identity;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private String confirmPassword;
	
	
	public String changePasswordForCurrentUser() {
		
		Account user = identity.getAccount();
			
		
		if (passwordsAreTheSame()) {
			identityService.changeUserPassword(user, new Password(getPassword()));
			this.identity.logout();
			return "/pages/login.xhtml?faces-redirect=true";
		} else {
			return "";
		}
	}

	public boolean passwordsAreTheSame() {

		if (password != null & confirmPassword != null & password.equals(confirmPassword)) {
			return true;
		}
		FacesMessages.error("Hasla musza byc takie same!", "");
		return false;
		
	}

}