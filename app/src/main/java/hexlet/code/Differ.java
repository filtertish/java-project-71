package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static hexlet.code.Parser.readFileToMap;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) {
        var firstFileAsMap = readFileToMap(firstFilePath);
        var secondFileAsMap = readFileToMap(secondFilePath);

        var differ = new ArrayList<Map<String, Object>>();

        for (var key : makeUniqueSortedKeys(firstFileAsMap, secondFileAsMap)) {
            // Check values for difference if key is in both files
            if (firstFileAsMap.containsKey(key) && secondFileAsMap.containsKey(key)) {
                var firstValue = firstFileAsMap.get(key);
                var secondValue = secondFileAsMap.get(key);

                // Null safety check
                if (firstValue == null) {
                    differ.add(secondValue == null
                            ? computeMap(key, "UNCHANGED", null)
                            : computeMap(key, (Object) null, secondValue));
                    continue;
                }

                if (secondValue == null) {
                    differ.add(computeMap(key, firstValue, null));
                    continue;
                }

                if (Objects.equals(firstValue, secondValue)) {
                    differ.add(computeMap(key, "UNCHANGED", firstValue));
                    continue;
                }

                differ.add(computeMap(key, firstValue, secondValue));
                continue;
            }

            // Check if key was removed in second file (exists in first file)
            if (firstFileAsMap.containsKey(key)) {
                var value = firstFileAsMap.get(key);
                differ.add(computeMap(key, "REMOVED", value));
                continue;
            }

            // Key not in the first file, so it was added to the second file
            var value = secondFileAsMap.get(key);

            differ.add(computeMap(key, "ADDED", value));
        }

        return Formatter.format(differ, format);
    }

    public static String generate(String firstFilePath, String secondFilePath) {
        return generate(firstFilePath, secondFilePath, "stylish");
    }

    private static List<String> makeUniqueSortedKeys(Map<String, Object> first, Map<String, Object> second) {
        Set<String> uniqueKeys = new HashSet<>(first.keySet());
        uniqueKeys.addAll(second.keySet());

        return uniqueKeys.stream().sorted().toList();
    }

    private static Map<String, Object> computeMap(String key, Object firstValue, Object secondValue) {
        var map = new HashMap<String, Object>();

        map.put("key", key);
        map.put("type", "CHANGED");
        map.put("value1", firstValue);
        map.put("value2", secondValue);

        return map;
    }

    private static Map<String, Object> computeMap(String key, String type, Object value) {
        var map = new HashMap<String, Object>();

        map.put("key", key);
        map.put("type", type);
        map.put("value", value);

        return map;
    }
}
