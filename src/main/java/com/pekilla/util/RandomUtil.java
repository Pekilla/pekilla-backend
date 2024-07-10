package com.pekilla.util;

import java.util.Random;

/**
 * Forked from Opensell.
 */
public class RandomUtil {
    private static final Random RANDOM = new Random();

    public static int FILE_LENGTH = 10;

    // POSTION FROM ASCI TABLE
    public static final byte FIRST_LOWER_LETTER = 97;
    public static final byte LAST_LOWER_LETTER = 122;
    public static final byte FIRST_UPPER_LETTER = 65;
    public static final byte LAST_UPPER_LETTER = 90;

    /**
     * Function that return a Random letter using the position of the ASCII table.
     * For example, 'a' is at position 97 in the ASCI table.
     *
     * @author Achraf
     */
    public static char randAsciiLetter() {
        // RANDOM to choose between upper(1) or lower(0).
        if(RANDOM.nextInt(0, 2) == 1) {
            return (char) RANDOM.nextInt(FIRST_UPPER_LETTER, LAST_UPPER_LETTER + 1);
        } else {
            return (char) RANDOM.nextInt(FIRST_LOWER_LETTER, LAST_LOWER_LETTER + 1);
        }
    }

    /**
     * Create a random file name.
     */
    public static String randFileName(String folder, String extension) {
        StringBuilder fileNameBuilder = new StringBuilder(folder+"/");

        for(int i = 0; i < FILE_LENGTH; i++) {
            fileNameBuilder.append(randAsciiLetter());
        }

        fileNameBuilder.append(".").append(extension);

        return fileNameBuilder.toString();
    }
}
