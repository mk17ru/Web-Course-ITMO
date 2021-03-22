package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.NoticeCredentials;

@Component
public class NoticeCredentialValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return NoticeCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            NoticeCredentials enterForm = (NoticeCredentials) target;
            String s = enterForm.getContent().trim();
            if (s.isEmpty()) {
                errors.rejectValue("content", "content.error", "Empty Input!!!");
            }
        }
    }
}
