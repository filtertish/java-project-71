package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(List<Map<String, Object>> differ) {
        var result = new StringBuilder("{");

        for (var diff : differ) {
            result.append("\n");
            result.append("  ");

            switch (String.valueOf(diff.get("type"))) {
                case "UNCHANGED" -> result.append(String.format("  %s: %s", diff.get("key"), diff.get("value")));
                case "CHANGED" -> {
                    result.append(String.format("- %s: %s", diff.get("key"), diff.get("value1")));
                    result.append("\n  ");
                    result.append(String.format("+ %s: %s", diff.get("key"), diff.get("value2")));
                }
                case "ADDED" -> result.append(String.format("+ %s: %s", diff.get("key"), diff.get("value")));
                case "REMOVED" -> result.append(String.format("- %s: %s", diff.get("key"), diff.get("value")));
                default -> throw new IllegalStateException("Unexpected \"type\" value");
            }
        }

        result.append("\n}");
        return result.toString();
    }
}
