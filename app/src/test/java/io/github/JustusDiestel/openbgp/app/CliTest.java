package io.github.JustusDiestel.openbgp.app;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CliTest {

    @Test
    void helpReturns0() {
        int code = new Cli().run(new String[]{"--help"});
        assertEquals(0, code);
    }

    @Test
    void missingArgsReturns2() {
        int code = new Cli().run(new String[]{"analyze"});
        assertEquals(2, code);
    }

    @Test
    void existingFileIsAccepted() throws Exception {
        Path tmp = Files.createTempFile("openbgp", ".mrt");
        Files.writeString(tmp, "x"); // wichtig!

        int code = new Cli().run(new String[]{
                "analyze", "--mrt", tmp.toString(), "--out", "out.json"
        });

        assertEquals(0, code);
    }
}
