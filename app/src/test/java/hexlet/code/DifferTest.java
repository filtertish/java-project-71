package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DifferTest {
    @Test
    public void differTestJSONAdd() {
        String result = Differ.generate("src/test/resources/base.json", "src/test/resources/add_to_base.json");
        String actualResult = """
            {
                admin: false
                age: 30
                email: hello_world@gmail.com
                name: Bob
              + newLine: i was just added
            }""";
        Assertions.assertEquals(result, actualResult);
    }

    @Test
    public void differTestJSONRemove() {
        String result = Differ.generate("src/test/resources/base.json", "src/test/resources/remove_from_base.json");
        String actualResult = """
            {
              - admin: false
                age: 30
              - email: hello_world@gmail.com
                name: Bob
            }""";

        Assertions.assertEquals(result, actualResult);
    }

    @Test
    public void differTestYAMLAdd() {
        String result = Differ.generate("src/test/resources/base.yml", "src/test/resources/add_to_base.yml");
        String actualResult = """
            {
                admin: false
                age: 30
                email: hello_world@gmail.com
                name: Bob
              + newLine: i was just added
            }""";
        Assertions.assertEquals(result, actualResult);
    }

    @Test
    public void differTestYAMLRemove() {
        String result = Differ.generate("src/test/resources/base.yml", "src/test/resources/remove_from_base.yml");
        String actualResult = """
            {
              - admin: false
                age: 30
              - email: hello_world@gmail.com
                name: Bob
            }""";

        Assertions.assertEquals(result, actualResult);
    }

    @Test
    public void differTestNonExistingFile() {
        Assertions.assertThrows(RuntimeException.class, () -> Differ.generate("non-exist.json", "non-exist.json"));
    }
}
