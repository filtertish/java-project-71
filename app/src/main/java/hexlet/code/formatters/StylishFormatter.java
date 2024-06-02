package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(Map<String, Object> firstFileAsMap, Map<String, Object> secondFileAsMap,
                                List<String> uniqueKeys) {
        var result = new StringBuilder("{\n");

        for (var key : uniqueKeys) {
            result.append("  ");

            // Check values for difference if key is in both files
            if (firstFileAsMap.containsKey(key) && secondFileAsMap.containsKey(key)) {
                var firstValue = String.valueOf(firstFileAsMap.get(key));
                var secondValue = String.valueOf(secondFileAsMap.get(key));

                if (!firstValue.equals(secondValue)) {
                    result.append(String.format("- %s: %s\n", key, firstValue));
                    result.append("  ");
                    result.append(String.format("+ %s: %s\n", key, secondValue));
                    continue;
                }

                result.append(String.format("  %s: %s\n", key, secondValue));
                continue;
            }

            // Check if key was removed in second file (exists in first file)
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
}
