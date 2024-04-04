public class Encryption {
    public String Encrypt(String text, int key, String mapIn){
        String mapstring;
        if(mapIn == null)mapstring = "abcdefghijklmnopqrstuvwxyz"; else mapstring = defaultMap(mapIn);
        char[] mapDefault = mapstring.toCharArray();
          StringBuilder toReturn = new StringBuilder();
          String[] map = {text.toLowerCase(), text.toLowerCase(), text.toLowerCase()};
        procesEncrypt(key, mapstring, mapDefault, map);
        return mapEncrypted(toReturn, map).replace(" ","");
    }

    private void procesEncrypt(int key, String mapstring, char[] mapDefault, String[] map) {
        for (int i = mapstring.length() -1 - key ; i >= 0; i--) {
            int k;
            if (i+key > 25) k = i+key-26; else k = i+key;
            map[0] = map[0].replace(mapDefault[i],mapDefault[k]);
        }
        for (int i = mapstring.length()-key; i < mapstring.length(); i++) {
            int k;
            if (i+key > 25) k = i+key-26; else k = i+key;
            map[1] = map[1].replace(mapDefault[i], mapDefault[k]);
        }
    }

    public String Decrypt(String text, int key, String mapIn){
        String mapstring;
        if(mapIn == null)mapstring = "abcdefghijklmnopqrstuvwxyz"; else mapstring = defaultMap(mapIn);
        char[] mapDefault = mapstring.toCharArray();
        StringBuilder toReturn = new StringBuilder();
        String[] map = {text.toLowerCase(), text.toLowerCase(), text.toLowerCase()};
        return processDecrypt(key, mapstring, mapDefault, toReturn, map);
    }

    private String processDecrypt(int key, String mapstring, char[] mapDefault, StringBuilder toReturn, String[] map) {
        for (int i = 0 ; i < mapstring.length() -key; i++) {
            int k;
            if (i-key < 0) k = i-key+26; else k = i-key;
            map[0] = map[0].replace(mapDefault[i],mapDefault[k]);
        }
        for (int i = mapstring.length()-key; i < mapstring.length(); i++) {
            int k;
            if (i-key < 0) k = i-key+26; else k = i-key;
            map[1] = map[1].replace(mapDefault[i], mapDefault[k]);
        }
        return mapEncrypted(toReturn, map).replace(" ","");
    }

    private String mapEncrypted(StringBuilder toReturn, String[] map) {
        for (int i = 0; i < map[0].length(); i++) {
            if (map[1].charAt(i) == map[2].charAt(i)) toReturn.append(map[0].charAt(i));
            else toReturn.append(map[1].charAt(i));
        }
        return toReturn.toString();
    }
    private String defaultMap(String mapInt)
    {
        String mapstringDefault, mapIn = mapInt.toLowerCase() ;
        mapstringDefault = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder map = new StringBuilder();

        for (int i = 0; i < mapIn.length(); i++) {
            char character = mapIn.charAt(i);
            if (map.toString().indexOf(character) == -1)
                map.append(character);
        }
        for (int i = 0; i < mapstringDefault.length(); i++) {
            char character = mapstringDefault.charAt(i);
            if(map.toString().indexOf(character) == -1)
                map.append(character);
        }
        return map.toString();
    }
}
