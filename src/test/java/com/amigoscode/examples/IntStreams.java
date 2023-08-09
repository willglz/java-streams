package com.amigoscode.examples;


import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreams {

    @Test
    public void range() throws Exception {
        System.out.println("Using fori");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
        System.out.println("Using IntStream Exclusive");
        IntStream.range(1, 10).forEach(System.out::println);
        System.out.println("Using IntStream Inclusive");
        IntStream.rangeClosed(1, 10).forEach(System.out::println);
    }

    // Loop through people using IntStream
    @Test
    public void rangeIteratingLists() throws Exception {
        List<Person> people = MockData.getPeople();
        IntStream.range(0, people.size()).forEach(p -> {
            System.out.println(people.get(p));
        });
    }

    @Test
    public void intStreamIterate()  {
        IntStream.iterate(0, value -> value + 1)
                .limit(10)
                .forEach(System.out::println);
    }
}
