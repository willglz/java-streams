package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkingWithStreams {

    @Test
    void steams() {
        List<String> names = List.of("Lela", "Veronica", "Angela", "Kendra");
        Stream<String> stream = names.stream();

        Stream<String> streamNames = Stream.of("Lela", "Veronica", "Angela", "Kendra");
    }
}
