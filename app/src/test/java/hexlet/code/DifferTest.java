package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DifferTest {
    @Test
    public void differTestJSON() {
        String result = Differ.generate("src/test/resources/base.json", "src/test/resources/modified.json");
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
        Assertions.assertEquals(result, actualResult);
    }

    @Test
    public void differTestYAML() {
        String result = Differ.generate("src/test/resources/base.yaml", "src/test/resources/modified.yaml");
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

        Assertions.assertEquals(result, actualResult);
    }

    @Test
    public void differTestNonExistingFile() {
        Assertions.assertThrows(RuntimeException.class, () -> Differ.generate("non-exist.json", "non-exist.json"));
    }
}
