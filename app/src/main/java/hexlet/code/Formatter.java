package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> differ, String format) throws IOException {
        return switch (format) {
            case "stylish" -> StylishFormatter.format(differ);
            case "plain" -> PlainFormatter.format(differ);
            case "json" -> JsonFormatter.format(differ);
            default -> throw new RuntimeException("Provided format doesn't exist");
        };
    }

}
