package org.example.lab10;

import java.util.*;

public class VigenereAnalize {
    private static final String map = "aăâbcdefghiîjklmnopqrsștțuvwxyz";

    public String frequencyAttack(String encryptedText) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : encryptedText.toCharArray()) {
            if (map.indexOf(c) != -1) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort(Map.Entry.comparingByValue());

        char mostFrequentChar = list.get(list.size() - 1).getKey();
        int key = map.indexOf(mostFrequentChar) - map.indexOf('e');
        if (key < 0) {
            key += map.length();
        }

        return String.valueOf(map.charAt(key));
    }

}
