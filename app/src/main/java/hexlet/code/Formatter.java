package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Formatter {
    public static String format(Map<String, Object> firstFileAsMap,
                                Map<String, Object> secondFileAsMap,
                                String format) {
        return switch (format) {
            case "stylish" -> StylishFormatter.format(
                    firstFileAsMap, secondFileAsMap, makeUniqueSortedKeys(firstFileAsMap, secondFileAsMap));
            case "plain" -> PlainFormatter.format(
                    firstFileAsMap, secondFileAsMap, makeUniqueSortedKeys(firstFileAsMap, secondFileAsMap));
            case "json" -> JsonFormatter.format(
                    firstFileAsMap, secondFileAsMap, makeUniqueSortedKeys(firstFileAsMap, secondFileAsMap));
            default -> throw new RuntimeException("Provided format doesn't exist");
        };
    }

    private static List<String> makeUniqueSortedKeys(Map<String, Object> first, Map<String, Object> second) {
        Set<String> uniqueKeys = new HashSet<>(first.keySet());
        uniqueKeys.addAll(second.keySet());

        return uniqueKeys.stream().sorted().toList();
    }
}
