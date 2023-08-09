package fprogramming;

import java.util.function.Predicate;

public class Predicatee {
    public static void main(String[] args) {
        String myEmail = "lela@mail.com";
        String myEmail2 = "angela@mailcom";
        boolean email = isEmailValid.test(myEmail);
        boolean dot = areDotInEmail.test(myEmail);
        System.out.println(email);
        System.out.println(dot);
        boolean second = isEmailValid.or(areDotInEmail).test(myEmail2);
        boolean third = isEmailValid.and(areDotInEmail).test(myEmail2);
        System.out.println("OR: " + second);
        System.out.println("AND: " + third);
    }

    static Predicate<String> isEmailValid = email -> email.contains("@");
    static Predicate<String> areDotInEmail = email -> email.contains(".");
}
