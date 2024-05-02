import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Encryption App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextArea inputText = new JTextArea(7, 20);
        JTextArea keyText = new JTextArea(4, 20);
        JTextArea keyIntText = new JTextArea(4, 20);
        JTextArea out = new JTextArea(7, 40);
        out.setEditable(false);

        JButton reset = new JButton("Reset");
        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        JCheckBox file = new JCheckBox("Load file");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(reset);
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        buttonPanel.add(file);

        panel.add(new JLabel("Input Text:"));
        panel.add(new JScrollPane(inputText));
        panel.add(new JLabel("Text Key:"));
        panel.add(keyText);
        panel.add(new JLabel("Key:"));
        panel.add(keyIntText);
        panel.add(buttonPanel);
        panel.add(new JLabel("Output:"));
        panel.add(new JScrollPane(out));

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.setText("");
                inputText.setText("");
                keyText.setText("");
                keyIntText.setText("");
                inputText.setEditable(true);
            }
        });

        inputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (inputText.getText().length() >= inputText.getDocument().getLength() && file.isSelected()) {
                    inputText.append("\n");
                }
                if(e.getKeyChar() == KeyEvent.VK_ENTER) inputText.append("\n");
            }
        });

        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file.isSelected()) {
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
                    fileChooser.setFileFilter(filter);
                    StringBuilder content = new StringBuilder();

                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();

                        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                content.append(line).append("\n");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        inputText.setText(content.toString());
                        inputText.setEditable(false);
                    }
                }
            }
        });

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String map_in = inputText.getText();
                String map = keyText.getText();
                int key;
                String keyint = keyIntText.getText();
                if (!keyint.isBlank() && !keyint.matches(".*[a-zA-Z].*")) {
                    key = Integer.parseInt(keyint);
                } else key = 0;

                if (map_in.isBlank() || key < 1 || key > 25) {
                    JOptionPane.showMessageDialog(panel, "You haven't entered the Text or Key correctly");
                    return;
                }
                if (map.length() < 7 && !map.isBlank() || map.matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(panel, "The length of the text key is less than 7 or contains numbers");
                    return;
                }
                Encryption encrypt = new Encryption();
                out.setText(encrypt.Encrypt(map_in, key, map));
                inputText.setEditable(true);
                file.setSelected(false);
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String map_in = inputText.getText();
                String map = keyText.getText();
                int key;
                String keyint = keyIntText.getText();
                if (!keyint.isBlank() && !keyint.matches(".*[a-zA-Z].*")) {
                    key = Integer.parseInt(keyint);
                } else key = 0;
                if (map_in.isBlank() || key < 1 || key > 25) {
                    JOptionPane.showMessageDialog(panel, "You haven't entered the Text or Key correctly");
                    return;
                }
                if (map.length() < 7 && !map.isBlank() || map.matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(panel, "The length of the text key is less than 7 or contains numbers");
                    return;
                }
                Encryption encrypt = new Encryption();
                out.setText(encrypt.Decrypt(map_in, key, map));
                inputText.setEditable(true);
                file.setSelected(false);
            }
        });
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}//lab8