package io.github.JustusDiestel.openbgp.app;

import java.nio.file.Files;
import java.nio.file.Path;

public final class Cli {

    public int run(String[] args) {
        if (args.length == 0 || has(args, "--help") || has(args, "-h")) {
            printHelp();
            return 0;
        }

        // MVP: erstmal nur "analyze --mrt <file> --out <file>"
        String cmd = args[0];
        if (!"analyze".equals(cmd)) {
            System.err.println("Unknown command: " + cmd);
            printHelp();
            return 2;
        }

        Path mrt = valueAfter(args, "--mrt");
        Path out = valueAfter(args, "--out");

        if (mrt == null) {
            System.err.println("Missing required option: --mrt <file>");
            return 2;
        }
        if (out == null) {
            System.err.println("Missing required option: --out <file>");
            return 2;
        }

        System.out.println("OpenBGP-Insight started");
        System.out.println("MRT file: " + mrt.toAbsolutePath());
        System.out.println("Output : " + out.toAbsolutePath());

        if (!Files.isRegularFile(mrt)) {
            System.err.println("MRT file not found: " + mrt.toAbsolutePath());
            return 3;
        }


        return 0;
    }

    private static void printHelp() {
        System.out.println("""
        Usage:
          openbgp-insight analyze --mrt <mrt-file> --out <output-file>

        Options:
          --mrt   Path to MRT dump file (RIB or updates)
          --out   Output file (JSON/CSV later)
          -h, --help  Show help
        """);
    }

    private static boolean has(String[] args, String key) {
        for (String a : args) if (key.equals(a)) return true;
        return false;
    }

    private static Path valueAfter(String[] args, String key) {
        for (int i = 0; i < args.length - 1; i++) {
            if (key.equals(args[i])) return Path.of(args[i + 1]);
        }
        return null;
    }
}