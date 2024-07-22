package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> differ) {
        var result = new StringBuilder();

        for (var diff : differ) {
            switch (String.valueOf(diff.get("type"))) {
                case "UNCHANGED" -> {
                }
                case "CHANGED" -> result.append(String.format("Property '%s' was updated. From %s to %s\n",
                        diff.get("key"), formatValue(diff.get("value1")), formatValue(diff.get("value2"))));
                case "ADDED" -> result.append(String.format("Property '%s' was added with value: %s\n",
                        diff.get("key"), formatValue(diff.get("value"))));
                case "REMOVED" -> result.append(String.format("Property '%s' was removed\n", diff.get("key")));
                default -> throw new IllegalStateException("Unexpected \"type\" value");
            }
        }

        return result.toString().trim();
    }

    @SuppressWarnings("rawtypes")
    private static String formatValue(Object value) {
        return switch (value) {
            case null -> "null";
            case Map ignored -> "[complex value]";
            case Collection ignored -> "[complex value]";
            case String ignored -> "'" + value + "'";
            default -> "" + value;
        };

    }
}
