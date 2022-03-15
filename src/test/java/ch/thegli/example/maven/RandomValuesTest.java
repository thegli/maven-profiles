package ch.thegli.example.maven;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class RandomValuesTest {

    @Test
    void randomLong_multiple_notAllEquals() {
        int samples = 10000;
        Set<Long> randomValues = new HashSet<>();

        for (int i = 0; i < samples; i++) {
            randomValues.add(RandomValues.randomLong());
        }

        Assertions.assertTrue(randomValues.size() > samples - 10);
    }

    @Test
    void randomAsciiString_multiple_allDifferent() {
        int length = 10;
        int samples = 10000;
        Set<String> randomValues = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            String randomString = RandomValues.randomAsciiString(length);
            Assertions.assertEquals(length, randomString.length());
            randomValues.add(randomString);
        }

        Assertions.assertEquals(samples, randomValues.size());
    }

    @Test
    void randomAsciiString_zeroLength_emptyString() {
        Assertions.assertEquals("", RandomValues.randomAsciiString(0));
    }

    @Test
    void randomAsciiString_negativeLength_runtimeException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> RandomValues.randomAsciiString(-1));
    }
}
