package io.github.JustusDiestel.openbgp.app;

public final class Main {
    public static void main(String[] args) {
        int exit = new Cli().run(args);
        System.exit(exit);
    }
}