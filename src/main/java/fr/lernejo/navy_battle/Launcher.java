package fr.lernejo.navy_battle;

public class Launcher {
    public static void main(String [] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Should have at least 1 argument : port number");
        }
        System.out.println("hello");
    }
}
