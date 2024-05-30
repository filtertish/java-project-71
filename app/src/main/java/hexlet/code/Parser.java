package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    public static Map<String, String> readFileToMap(String filePath) {
        Path pathToFile = Path.of(filePath);
        String fileExtension = pathToFile.toString().split("\\.")[1];

        try {
            ObjectMapper mapper = switch (fileExtension) {
                case "json" -> new ObjectMapper();
                case "yml", "yaml" -> new YAMLMapper();
                default -> throw new IOException("File type unknown");
            };

            return mapper.readValue(Files.readString(pathToFile), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
