package hexlet.code;

import static hexlet.code.Parser.readFileToMap;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) {
        var firstFileAsMap = readFileToMap(firstFilePath);
        var secondFileAsMap = readFileToMap(secondFilePath);

        return Formatter.format(firstFileAsMap, secondFileAsMap, format);
    }
}
