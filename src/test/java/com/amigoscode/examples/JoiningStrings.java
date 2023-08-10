package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JoiningStrings {

    @Test
    public void joiningStrings() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // "Anna, John, Marcos, Helena, Yasmin"
        StringBuilder oneLine = new StringBuilder();
        for (String name : names) {
            oneLine.append(String.valueOf(name.charAt(0)).toUpperCase()).append(name, 1, name.length()).append(", ");
        }
        System.out.println(oneLine.substring(0,oneLine.length() - 2));
    }

    @Test
    public void joiningStringsWithStream() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // "Anna, John, Marcos, Helena, Yasmin"
        String oneLine = names.stream()
                .reduce((s, s2) ->
                        String.valueOf(s.charAt(0)).toUpperCase()
                                .concat(s.substring(1))
                                .concat(", ")
                                .concat(String.valueOf(s2.charAt(0)).toUpperCase().concat(s2.substring(1))))
                .orElse("");

        String oneLine2 = names.stream()
                .map(s -> String.valueOf(s.charAt(0)).toUpperCase().concat(s.substring(1)))
                .collect(Collectors.joining(", "));
        System.out.println(oneLine);

    }

}
