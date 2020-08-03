package pl.warehouse.jsf.validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "inputTextValidator")
public class InputTextValidator implements Validator<Object> {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String inputText = value.toString();
		FacesMessage message = null;
		
		try {
			
			//String length more then 4 char.
			if (inputText.length() <= 3) {
				message = new FacesMessage("Pole nie powinno byc krótsze niz 4 znaki.");
			}
			
			// String must contain only letters, numbers,polish signs, space and dash
			if (!Pattern.matches("^[0-9a-zA-ZąĄęĘóÓśŚłŁżŻźŹćĆńŃ -]*",inputText)) {
				message = new FacesMessage("Pole nie może zawierać znaków specjalnych.");
			}
			
			if (message != null && !message.getDetail().isEmpty()) {
				throw new ValidatorException(message);
			}

		} catch (Exception ex) {
			throw new ValidatorException(new FacesMessage(ex.getMessage()));
		}

	}

}
