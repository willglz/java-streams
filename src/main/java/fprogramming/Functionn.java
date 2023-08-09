package fprogramming;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Functionn {
    public static void main(String[] args) {
        Function<Integer, Integer> combFunction = incrementByOne.andThen(doubleNumber);
        System.out.println(incrementByOne.andThen(doubleNumber).apply(2));
        System.out.println(combFunction.apply(7));
        System.out.println(messageFunc.apply("lela", 47));
    }

    static Function<Integer, Integer> incrementByOne = n -> n + 1;
    static Function<Integer, Integer> doubleNumber = n -> n * 2;

    record Person(String name, Integer age){}

    static BiFunction<String, Integer, Person> messageFunc = (name, age) -> new Person(name.toUpperCase(), age);
}
