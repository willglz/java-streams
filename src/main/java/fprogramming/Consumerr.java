package fprogramming;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Consumerr {
    public static void main(String[] args) {
        sendEmail.accept("lela@mail.com");
        sendFromTo.accept("angela@mail.com", "jordi@mail.com");
    }

    static Consumer<String> sendEmail = (email) -> System.out.println("Sending email to " + email);

    static BiConsumer<String, String> sendFromTo = (from, to) -> System.out.println("Sending email from " + from + " to " + to);
}
