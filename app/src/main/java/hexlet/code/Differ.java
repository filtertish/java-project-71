package hexlet.code;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Parser.readFileToMap;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath) {
        var firstFileAsMap = readFileToMap(firstFilePath);
        var secondFileAsMap = readFileToMap(secondFilePath);
        var result = new StringBuilder("{\n");

        for (var key : makeUniqueSortedKeys(firstFileAsMap, secondFileAsMap)) {
            result.append("  ");

            // Check values for difference if key is in both files
            if (firstFileAsMap.containsKey(key) && secondFileAsMap.containsKey(key)) {
                var firstValue = firstFileAsMap.get(key);
                var secondValue = secondFileAsMap.get(key);

                if (!firstValue.equals(secondValue)) {
                    result.append(String.format("- %s: %s\n", key, firstValue));
                    result.append("  ");
                    result.append(String.format("+ %s: %s\n", key, secondValue));
                    continue;
                }

                result.append(String.format("  %s: %s\n", key, secondValue));
                continue;
            }

            // Check if key was removed in second file (exist in first file)
            if (firstFileAsMap.containsKey(key)) {
                result.append(String.format("- %s: %s\n", key, firstFileAsMap.get(key)));
                continue;
            }

            // Key not in the first file, so it was added to the second file
            result.append(String.format("+ %s: %s\n", key, secondFileAsMap.get(key)));
        }

        result.append("}");
        return result.toString();
    }

    private static List<String> makeUniqueSortedKeys(Map<String, String> first, Map<String, String> second) {
        Set<String> uniqueKeys = new HashSet<>(first.keySet());
        uniqueKeys.addAll(second.keySet());

        return uniqueKeys.stream().sorted().toList();
    }
}
