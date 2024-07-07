package hexlet.code;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class DifferTest {
    @Test
    public void differStylishTestJSON() {
        String result = Differ.generate("src/test/resources/base.json",
                "src/test/resources/modified.json",
                "stylish");
        String actualResult = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        Assertions.assertEquals(actualResult, result);
    }

    @Test
    public void differStylishTestYAML() {
        String result = Differ.generate("src/test/resources/base.yaml",
                "src/test/resources/modified.yaml",
                "stylish");
        String actualResult = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        Assertions.assertEquals(actualResult, result);
    }

    @Test
    public void differPlainTestJSON() {
        String result = Differ.generate("src/test/resources/base.json",
                "src/test/resources/modified.json",
                "plain");
        String actualResult = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";

        Assertions.assertEquals(actualResult, result);
    }

    @Test
    public void differPlainTestYAML() {
        String result = Differ.generate("src/test/resources/base.yaml",
                "src/test/resources/modified.yaml",
                "plain");
        String actualResult = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";

        Assertions.assertEquals(actualResult, result);
    }

    @Test
    public void differJsonTestJSON() throws JSONException, IOException {
        Differ.generate("src/test/resources/base.json", "src/test/resources/modified.json", "json");

        String result = Files.readString(Paths.get("src/test/resources/result.json"));
        String actual = Files.readString(Paths.get("src/test/resources/actualResult.json"));

        JSONAssert.assertEquals(result, actual, false);
    }

    @Test
    public void differJsonTestYAML() throws IOException, JSONException {
        Differ.generate("src/test/resources/base.yaml", "src/test/resources/modified.yaml", "json");

        String result = Files.readString(Paths.get("src/test/resources/result.json"));
        String actual = Files.readString(Paths.get("src/test/resources/actualResult.json"));

        JSONAssert.assertEquals(result, actual, false);
    }

    @Test
    public void differTestNonExistingFormat() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Differ.generate("src/test/resources/base.yaml",
                        "src/test/resources/modified.yaml",
                        "non-existing")
        );
    }

    @Test
    public void differTestNonExistingFile() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> Differ.generate("non-exist.json", "non-exist.json", "stylish")
        );
    }
}
