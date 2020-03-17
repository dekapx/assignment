package net.dekapx.thatsmine.util;

import java.util.Random;

import static net.dekapx.thatsmine.common.CommonConstants.NUMBER_OF_COMPONENTS;

public class KeyGenerator {
    private static Random random = new Random();

    public static int getRandomKey() {
        return random.nextInt(NUMBER_OF_COMPONENTS) + 1;
    }
}
