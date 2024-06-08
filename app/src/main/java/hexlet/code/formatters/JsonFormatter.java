package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String format(Map<String, Object> firstFileAsMap, Map<String, Object> secondFileAsMap,
                                List<String> uniqueKeys) {
        List<Map<String, Object>> result = new LinkedList<>();

        for (var key : uniqueKeys) {
            var firstValue = firstFileAsMap.get(key);
            var secondValue = secondFileAsMap.get(key);

            // Check values for difference if key is in both files
            if (firstFileAsMap.containsKey(key) && secondFileAsMap.containsKey(key)) {
                // Check if first value is null
                if (firstValue == null) {
                    if (secondValue == null) {
                        result.add(generateMap(key, "UNCHANGED", null));
                        continue;
                    }

                    result.add(generateMap(key, null, secondValue));
                    continue;
                }

                // Check if second value is null
                if (secondValue == null) {
                    result.add(generateMap(key, firstValue, null));
                    continue;
                }

                if (firstValue.equals(secondValue)) {
                    result.add(generateMap(key, "UNCHANGED", firstValue));
                    continue;
                }

                result.add(generateMap(key, firstValue, secondValue));
                continue;
            }

            // Check if key was removed in second file (exists in first file)
            if (firstFileAsMap.containsKey(key)) {
                result.add(generateMap(key, "REMOVED", firstValue));
                continue;
            }

            result.add(generateMap(key, "ADDED", secondValue));
        }

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File("src/test/resources/result.json"), result);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Object> generateMap(String key, Object value1, Object value2) {
        Map<String, Object> e = new LinkedHashMap<>();
        e.put("key", key);
        e.put("type", "CHANGED");
        e.put("value1", value1);
        e.put("value2", value2);

        return e;
    }

    private static Map<String, Object> generateMap(String key, String type, Object value) {
        Map<String, Object> e = new LinkedHashMap<>();
        e.put("key", key);
        e.put("type", type);
        e.put("value1", value);

        return e;
    }
}
