package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> readFileToMap(String file, String extension) throws IOException {
        ObjectMapper mapper = switch (extension) {
            case "json" -> new ObjectMapper();
            case "yml", "yaml" -> new YAMLMapper();
            default -> throw new IOException("File type unknown");
        };

        return mapper.readValue(file, new TypeReference<>() {
        });
    }
}

