package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) throws IOException {
        var firstFileString = Files.readString(Path.of(firstFilePath));
        var secondFileString = Files.readString(Path.of(secondFilePath));

        var firstFile = Parser.readFileToMap(firstFileString, extractExtension(firstFilePath));
        var secondFile = Parser.readFileToMap(secondFileString, extractExtension(secondFilePath));

        var diff = Generator.makeDiff(firstFile, secondFile);

        return Formatter.format(diff, format);
    }

    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        return generate(firstFilePath, secondFilePath, "stylish");
    }

    private static String extractExtension(String filePath) {
        return filePath.split("\\.")[1];
    }
}
