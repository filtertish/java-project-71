package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(Map<String, Object> firstFileAsMap, Map<String, Object> secondFileAsMap,
                                List<String> uniqueKeys) {
        var result = new StringBuilder();

        for (var key : uniqueKeys) {
            var firstValue = firstFileAsMap.get(key);
            var secondValue = secondFileAsMap.get(key);

            // Check values for difference if key is in both files
            if (firstFileAsMap.containsKey(key) && secondFileAsMap.containsKey(key)) {
                // Case when first value is null
                if (firstValue == null) {
                    if (secondValue != null) {
                        result.append(String.format("Property '%s' was updated. From %s to %s\n",
                                key, "null", formatValue(secondValue)));
                    }
                    continue;
                }

                //Case when second value is null
                if (secondValue == null) {
                    result.append(String.format("Property '%s' was updated. From %s to %s\n",
                            key, formatValue(firstValue), "null"));
                    continue;
                }

                //Case when both values are not null and equals
                if (firstValue.equals(secondValue)) {
                    continue;
                }

                //Case when both values are not null and not equals
                result.append(String.format("Property '%s' was updated. From %s to %s\n",
                        key, formatValue(firstValue), formatValue(secondValue)));
                continue;
            }

            // Check if key was removed in second file (exists in first file)
            if (firstFileAsMap.containsKey(key)) {
                result.append(String.format("Property '%s' was removed\n", key));
                continue;
            }

            // Key not in the first file, so it was added to the second file
            result.append(String.format("Property '%s' was added with value: %s\n",
                    key, formatValue(secondValue)));
        }

        return result.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }

        String valueClassName = value.getClass().toString();

        if (valueClassName.endsWith("LinkedHashMap")) {
            return "[complex value]";
        }

        if (valueClassName.endsWith("ArrayList")) {
            return "[complex value]";
        }

        if (valueClassName.endsWith("String")) {
            return "'" + value + "'";
        }

        return "" + value;
    }
}
