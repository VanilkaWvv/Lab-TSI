import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JTextField inputText = new JTextField(20);
        JTextField keyText = new JTextField(20);
        JTextField keyIntText = new JTextField(20);
        JTextField out = new JTextField(40);
        out.setEditable(false);

        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        JCheckBox file = new JCheckBox("Load file");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        buttonPanel.add(file);

        panel.add(new JLabel("Input Text:"));
        panel.add(inputText);
        panel.add(new JLabel("Text Key:"));
        panel.add(keyText);
        panel.add(new JLabel("Key:"));
        panel.add(keyIntText);
        panel.add(buttonPanel);
        panel.add(new JLabel("Ouput:"));
        panel.add(out);

        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file.isSelected()) {
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Fișiere text (*.txt)", "txt");
                    fileChooser.setFileFilter(filter);
                    StringBuilder content = new StringBuilder();

                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        java.io.File selectedFile = fileChooser.getSelectedFile();
                        File file = new File(selectedFile.getAbsolutePath());

                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                content.append(line);
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
                } else key=0;

                if(map_in.isBlank() || key < 1 || key > 25)
                {
                    JOptionPane.showMessageDialog(panel, "Nu ai introdus Textul sau keya corect");
                    return;
                }
                if(map.length()<7 && !map.isBlank() || map.matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(panel, "Lungimea la keie de tip text este mai mica decat 7 sau contine numere");
                    return;
                }
                Encryption encrypt = new Encryption();
                out.setText("");
                inputText.setText("");
                keyText.setText("");
                keyIntText.setText("");
                out.setText(encrypt.Encrypt(map_in,key,map));
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
                } else key=0;
                if(map_in.isBlank() || key < 1 || key > 25)
                {
                    JOptionPane.showMessageDialog(panel, "Nu ai introdus Textul sau keya");
                    return;
                }
                if(map.length()<7 && !map.isBlank()|| map.matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(panel, "Lungimea la keie de tip text este mai mica decat 7 sau contine numere");
                    return;
                }
                Encryption encrypt = new Encryption();
                out.setText("");
                inputText.setText("");
                keyText.setText("");
                keyIntText.setText("");
                out.setText(encrypt.Decrypt(map_in,key,map));
                inputText.setEditable(true);
                file.setSelected(false);
            }
        });
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
