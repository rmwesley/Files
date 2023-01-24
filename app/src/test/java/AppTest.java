import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
    @Test void appJoinsFiles() {
        assertEquals("a\nb\n",
            App.join(new String[] {"src/main/resources/dir1/file1", "src/main/resources/dir3/file2"})
        );
    }
    @Test void appFindsExactFiles() throws IOException {
        assertEquals(String.join("\n",
            "src/main/resources/dir1/file1",
            "src/main/resources/dir3/file1"),
            App.findAll("file1")
        );
    }
    @Test void appFindsFiles() throws IOException {
        assertEquals(String.join("\n",
        "src/main/resources/dir1/file1",
        "src/main/resources/dir3/file1",
        "src/main/resources/dir3/file3",
        "src/main/resources/dir3/file2"),
            App.findAll("file")
        );
    }
}