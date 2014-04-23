package eu.ibacz.sample.portlet.bascispring;

import eu.ibacz.sample.portlet.bascispring.pto.PersonPto;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Miroslav Ligas <miroslav.ligas@ibacz.eu>
 */
@Component
public class PersonPtoValidator implements Validator {
    private static final DateTime minDate = DateTime.now().minusYears(130);
    private static final DateTime maxDate = DateTime.now().plusYears(130);

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonPtoValidator.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "basicspring-err-null-value");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "basicspring-err-null-value");
        if (!errors.hasErrors()) {
            PersonPto personPto = (PersonPto) target;
            if (personPto.getDateOfBirth().isBefore(minDate)) {
                errors.rejectValue("dateOfBirth", "basicspring-err-to-early");
            }
            if (personPto.getDateOfBirth().isAfter(maxDate)) {
                errors.rejectValue("dateOfBirth", "basicspring-err-to-late");
            }
        }
    }
}
