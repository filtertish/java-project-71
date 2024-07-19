package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) throws IOException {
        var firstFileString = Files.readString(Path.of(firstFilePath));
        var firstFileExtension = firstFilePath.split("\\.")[1];
        var secondFileString = Files.readString(Path.of(secondFilePath));
        var secondFileExtension = secondFilePath.split("\\.")[1];

        var firstFile = Parser.readFileToMap(firstFileString, firstFileExtension);
        var secondFile = Parser.readFileToMap(secondFileString, secondFileExtension);

        var diff = Generator.makeDiff(firstFile, secondFile);

        return Formatter.format(diff, format);
    }

    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        return generate(firstFilePath, secondFilePath, "stylish");
    }
}
