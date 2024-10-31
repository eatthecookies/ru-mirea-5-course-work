package ru.mirea.familytaskmanagement.utils;

import java.util.Random;

public class FamilyIdentifierGenerator {

    public static String generateFamilyId() {
        StringBuilder familyId = new StringBuilder(4);
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(alphabet.length());
            familyId.append(alphabet.charAt(index));
        }

        return familyId.toString();
    }

}
