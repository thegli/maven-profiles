package ch.thegli.example.maven;

import java.security.SecureRandom;

public class RandomValues {

    private static final SecureRandom RANDOMIZER = new SecureRandom();

    private RandomValues() {
    }

    public static long randomLong() {
        return RANDOMIZER.nextLong();
    }

    public static String randomAsciiString(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be equals or greater than 0");
        }

        StringBuilder randomString = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            randomString.append((char) ((RANDOMIZER.nextInt() % 95) + 32));
        }
        return randomString.toString();
    }
}
