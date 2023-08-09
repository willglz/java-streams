package fprogramming.finterfaces;

public class ImplPersonInt {
    public static void main(String[] args) {
        Person lela = new Person("Lela", "+00938973", "lela@mail.com");
        Person veronica = new Person("Vero", "+00938973", "avluv@mail.com");

        PersonRegistrationValidator validator =
                PersonRegistrationValidator.isEmailTaken()
                        .and(PersonRegistrationValidator.isEmailValid())
                        .and(PersonRegistrationValidator.isPhoneNumberValid());
        System.out.println(validator.apply(lela));
        System.out.println(validator.apply(veronica));
    }
}
