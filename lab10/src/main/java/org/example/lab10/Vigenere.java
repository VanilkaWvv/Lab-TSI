package org.example.lab10;

public class Vigenere {
public String encrypt(String txt, String key){
    String map = "aăâbcdefghiîjklmnopqrsștțuvwxyz";
    StringBuilder toReturn = new StringBuilder();
    if (txt.isEmpty()|| key.isEmpty() || !verifyKey(key)){
        return "";
    }
    int k = 0;
    for (char word: txt.toCharArray()) {
        int i = map.indexOf(word) + map.indexOf(key.charAt(k));
        if (map.indexOf(word) == -1 || word == ' ') {
            toReturn.append(word);
        }
        else {
            if (i > 30)
                toReturn.append(map.charAt(i - 30));
            else toReturn.append(map.charAt(i));
            k++;
        }
        if (k == key.length()) k=0;
    }
    return toReturn.toString();
}
public String decrypt(String txt, String key){
    String map = "aăâbcdefghiîjklmnopqrsștțuvwxyz";
    StringBuilder toReturn = new StringBuilder();
    if (txt.isEmpty() || key.isEmpty() || !verifyKey(key)){
        return "";
    }
    int k = 0;
    for (char word: txt.toCharArray()) {
        int i = map.indexOf(word) - map.indexOf(key.charAt(k));
        if (map.indexOf(word) == -1 || word == ' ') {
            toReturn.append(word);
        }
        else {
            if (i < 0)
                toReturn.append(map.charAt(i + 30));
            else toReturn.append(map.charAt(i));
            k++;
        }
        if (k == key.length()) k=0;
    }
    return toReturn.toString();
}
private boolean verifyKey(String key){
    String map = "aăâbcdefghiîjklmnopqrsștțuvwxyz";
    for (int i = 0; i < key.length(); i++) {
        if (!map.contains(String.valueOf(key.charAt(i)))) {
            return false;
        }
    }
    return true;
}
}