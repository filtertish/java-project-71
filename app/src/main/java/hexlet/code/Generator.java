package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class Generator {
    public static List<Map<String, Object>> makeDiff(Map<String, Object> firstFile, Map<String, Object> secondFile) {
        var uniqueSortedKeys = new TreeSet<>(firstFile.keySet());
        uniqueSortedKeys.addAll(secondFile.keySet());

        var differ = new ArrayList<Map<String, Object>>();


        for (var key : uniqueSortedKeys) {
            var firstValue = firstFile.get(key);
            var secondValue = secondFile.get(key);

            if (!firstFile.containsKey(key)) {
                differ.add(computeMap(key, "ADDED", secondValue));
            } else if (!secondFile.containsKey(key)) {
                differ.add(computeMap(key, "REMOVED", firstValue));
            } else {
                differ.add(Objects.equals(firstValue, secondValue)
                        ? computeMap(key, "UNCHANGED", firstValue)
                        : computeMap(key, firstValue, secondValue));
            }
        }

        return differ;
    }

    private static Map<String, Object> computeMap(String key, Object firstValue, Object secondValue) {
        var map = new LinkedHashMap<String, Object>();

        map.put("key", key);
        map.put("type", "CHANGED");
        map.put("value1", firstValue);
        map.put("value2", secondValue);

        return map;
    }

    private static Map<String, Object> computeMap(String key, String type, Object value) {
        var map = new LinkedHashMap<String, Object>();

        map.put("key", key);
        map.put("type", type);
        map.put("value", value);

        return map;
    }
}
