import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BruteForceAttack{
public static void bruteForceAttack(String encryptedText, JTextArea out, JButton yesButton, JButton noButton, String keyText) {
    final int[] k = {0};
    double maxScore = -1;
    double[] score = new double[26];
    int[] index = new int[26];
    String[] decryptedString = new String[26];
    for (int key = 0; key < 26; key++) {
        decryptedString[key] = decryptWithKey(encryptedText, key, keyText);
        score[key] = scoreText(decryptedString[key]);
    }
    for (int i = 0; i < 26; i++) {
        for (int j = 0; j < 26; j++) {
            if (maxScore<score[j]) {maxScore=score[j]; index[i]=j;}
        }
        score[index[i]]=0;
        maxScore=-1;
    }
    for (int i = 0; i < 26; i++) {
        System.out.println(STR."Index\{index[i]}");
    }
    out.setText(decryptedString[index[0]]);
    numberOfLetters(decryptedString[index[0]]);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cheia este:"+index[k[0]]);
                k[0]=0;
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                k[0]++;
                out.setText(decryptedString[index[k[0]]]);
                clearConsole();
                numberOfLetters(decryptedString[index[k[0]]]);
            }
        });
}

    private static String decryptWithKey(String encryptedText, int key, String keyText) {
        Encryption encryption = new Encryption();
        return encryption.Decrypt(encryptedText, key, keyText);
    }

    private static double scoreText(String text) {
        Map<Character, Double> englishFreq = new HashMap<>();
        englishFreq.put('a', 8.2);
        englishFreq.put('b', 1.5);
        englishFreq.put('c', 2.8);
        englishFreq.put('d', 4.3);
        englishFreq.put('e', 13.0);
        englishFreq.put('f', 2.2);
        englishFreq.put('g', 2.0);
        englishFreq.put('h', 6.1);
        englishFreq.put('i', 7.0);
        englishFreq.put('j', 0.2);
        englishFreq.put('k', 0.8);
        englishFreq.put('l', 4.0);
        englishFreq.put('m', 2.4);
        englishFreq.put('n', 6.7);
        englishFreq.put('o', 7.5);
        englishFreq.put('p', 1.9);
        englishFreq.put('q', 0.1);
        englishFreq.put('r', 6.0);
        englishFreq.put('s', 6.3);
        englishFreq.put('t', 9.1);
        englishFreq.put('u', 2.8);
        englishFreq.put('v', 1.0);
        englishFreq.put('w', 2.4);
        englishFreq.put('x', 0.2);
        englishFreq.put('y', 2.0);
        englishFreq.put('z', 0.1);

        double score = 0.0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (englishFreq.containsKey(c)) {
                score += englishFreq.get(c);
            }
        }
        return score;
    }
    private static void numberOfLetters(String text){
    String txt = text.replace(" ","");
        System.out.println(txt.length());
    int[] frecventa = frequency(text);
        for (int i = 0; i < 26; i++) {
            double score;
            if(frecventa[i] == 0) score=0; else score= (double) frecventa[i] /txt.length()*100;
            System.out.println("Caracterul '" + (char) (i + 'a') + "' apare de " + frecventa[i] + " ori cu procentajul de "+ score);
        }
    }
    private static int[] frequency(String str) {
        int[] frecv = new int[26];
        for (char c : str.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                frecv[c - 'a']++;
            }
        }
        return frecv;
    }
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
