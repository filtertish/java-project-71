package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String format(List<Map<String, Object>> differ) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File("src/test/resources/result.json"), differ);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(differ);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
