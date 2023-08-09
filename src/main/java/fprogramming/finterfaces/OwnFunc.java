package fprogramming.finterfaces;

import java.util.Arrays;
import java.util.List;

public class OwnFunc {
    public static void main(String[] args) {
        EmailValidator emailValidator = new EmailValidator();

        List<String> mails = Arrays.asList("lela@mail.com", "angela@mail.com", "veronicamail.com");
        mails.forEach(m -> {
            System.out.println("Is email valid: " + emailValidator.apply(m));
        });
    }
}
