package fr.lernejo;

import fr.lernejo.navy_battle.Launcher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class LauncherTest {

    @Test
    void main() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Launcher.main(new String[0]))
            .withMessage("Should have at least 1 argument : port number");
    }

    @ParameterizedTest
    @CsvSource({
        "9876, 9876"
    })
    void main_test_cases(String a, String b) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String[] test = new String[1];
        test[0] = a;
        Launcher.main(test);
        Assertions.assertThat(outContent.toString()).isEqualTo(b+"\n");
        System.setOut(originalOut);
    }
}
