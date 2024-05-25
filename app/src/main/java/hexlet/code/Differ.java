package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath) {
        var firstFileAsMap = readFileToMap(firstFilePath);
        var secondFileAsMap = readFileToMap(secondFilePath);
        var result = new StringBuilder();

        result.append("{\n");

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

    private static Map<String, String> readFileToMap(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        Path pathToFile = Path.of(filePath);

        try {
            return mapper.readValue(Files.readString(pathToFile), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> makeUniqueSortedKeys(Map<String, String> first, Map<String, String> second) {
        Set<String> uniqueKeys = new HashSet<>(first.keySet());
        uniqueKeys.addAll(second.keySet());

        return uniqueKeys.stream().sorted().toList();
    }
}
