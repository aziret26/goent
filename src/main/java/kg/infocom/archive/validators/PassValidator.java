package kg.infocom.archive.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by timur on 14-Apr-17.
 */
@FacesValidator("password")
public class PassValidator implements Validator{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String password = (String) o;
        String confirm = (String) uiComponent.getAttributes().get("confirm");

        if (password == null || confirm == null) {
            return;
        }
        if (!password.equals(confirm)) {
            throw new ValidatorException(new FacesMessage("Passwords are not equal."));
        }
    }
}
