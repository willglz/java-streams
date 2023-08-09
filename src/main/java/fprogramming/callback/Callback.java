package fprogramming.callback;

import java.util.function.Consumer;

public class Callback {
    public static void main(String[] args) {
        greet("lela", "Star", cb);
    }
    static Consumer<String> cb = input -> System.out.println(input + " you must have last name");
    static void greet(String firstName, String lastName, Consumer<String> cb){
        System.out.println(firstName);
        if (lastName != null){
            System.out.println(lastName);
        }else {
            cb.accept(firstName);
        }
    }
}
