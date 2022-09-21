package utils.random_generators;

import constants.StringConstants;

import java.util.Random;

public class RandomText {

    public static String getRandomString() {

        Random random = new Random();

        return random.ints(StringConstants.LEFT_INTERVAL_FOR_RANDOM_STRING, StringConstants.RIGHT_INTERVAL_FOR_RANDOM_STRING + 1)
                .limit(StringConstants.STRING_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
