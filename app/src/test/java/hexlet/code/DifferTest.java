package hexlet.code;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DifferTest {
    private static String stylishResult;
    private static String plainResult;
    private static String jsonResult;
    private static String jsonActualResult;

    @BeforeAll
    public static void initialize() throws IOException {
        var stylishResultPath = Path.of(buildPath("stylishResult.txt"));
        var plainResultPath = Path.of(buildPath("plainResult.txt"));

        stylishResult = Files.readString(stylishResultPath);
        plainResult = Files.readString(plainResultPath);
        jsonResult = Differ.generate(buildPath("base.json"), buildPath("modified.json"), "json");
        jsonActualResult = Files.readString(Paths.get(buildPath("actualResult.json")));
    }

    @Test
    public void differDefaultTestJSON() throws IOException {
        String result = Differ.generate(buildPath("base.json"),
                buildPath("modified.json"));

        Assertions.assertEquals(stylishResult, result);
    }

    @Test
    public void differStylishTestJSON() throws IOException {
        String result = Differ.generate(buildPath("base.json"),
                buildPath("modified.json"),
                "stylish");

        Assertions.assertEquals(stylishResult, result);
    }

    @Test
    public void differStylishTestYAML() throws IOException {
        String result = Differ.generate(buildPath("base.yaml"),
                buildPath("modified.yaml"),
                "stylish");

        Assertions.assertEquals(stylishResult, result);
    }

    @Test
    public void differPlainTestJSON() throws IOException {
        String result = Differ.generate(buildPath("base.json"),
                buildPath("modified.json"),
                "plain");

        Assertions.assertEquals(plainResult, result);
    }

    @Test
    public void differPlainTestYAML() throws IOException {
        String result = Differ.generate(buildPath("base.yaml"),
                buildPath("modified.yaml"),
                "plain");

        Assertions.assertEquals(plainResult, result);
    }

    @Test
    public void differJsonTestJSON() throws JSONException {
        JSONAssert.assertEquals(jsonResult, jsonActualResult, false);
    }

    @Test
    public void differJsonTestYAML() throws JSONException {
        JSONAssert.assertEquals(jsonResult, jsonActualResult, false);
    }

    @Test
    public void differTestNonExistingFormat() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Differ.generate(buildPath("base.yaml"),
                        buildPath("modified.yaml"),
                        "non-existing")
        );
    }

    @Test
    public void differTestNonExistingFile() {
        Assertions.assertThrows(
                NoSuchFileException.class,
                () -> Differ.generate("non-exist.json", "non-exist.json", "stylish")
        );
    }

    private static String buildPath(String name) {
        return "src/test/resources/" + name;
    }
}
