import java.awt.event.*;
import java.io.File;
import java.awt.*;
import javax.swing.*;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map;

public class RomanNumeralGUI extends JFrame implements ActionListener {
    // This creates the menu items
    private JMenuItem openMenuItem, quitMenuItem, romanToArabicMenuItem;

    public RomanNumeralGUI() {

        // This is the title of the GUi
        super("Roman Numeral Converter");
        // This is for the size of the GUI
        setSize(500, 250);

        // This will exit the GUI when you click the X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This creates the menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // This creates the File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // This creates the Open menu item
        openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(this);
        fileMenu.add(openMenuItem);

        // This creates the Quit menu item
        quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(this);
        fileMenu.add(quitMenuItem);

        // This creates the Convert menu
        JMenu convertMenu = new JMenu("Convert");
        menuBar.add(convertMenu);

        // This creates the Roman to Arabic menu item
        romanToArabicMenuItem = new JMenuItem("Roman to Arabic");
        romanToArabicMenuItem.addActionListener(this);
        convertMenu.add(romanToArabicMenuItem);

    }

    static JFrame mainFrame;

    // This is setting up the JFrame, but it will not be visible
    public static void initialize() {
        mainFrame = new JFrame();
        mainFrame.setSize(500, 400);
        mainFrame.setLocation(100, 200);
        mainFrame.setTitle("RomanNumeral Conversion");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(1, 3));
    }

    // This is the function that will show the JFrame, using the Treemap
    public static void ShowStuffOnJFrame(TreeMap<String, Integer> sortedMap) {
        Container contentPane = mainFrame.getContentPane();
        JTextArea romanPane = new JTextArea();
        JTextArea arabicPane = new JTextArea();
        contentPane.add(romanPane);
        contentPane.add(arabicPane);
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            romanPane.append(entry.getKey() + "\n");
            arabicPane.append(entry.getValue() + "\n");
        }
        mainFrame.setVisible(true);
    }

    // This is the function that reads the input from the file
    public static void inputFromFile(String filename, TreeMap<String, Integer> sortedMap) {
        TextFileInput file = new TextFileInput(filename);
        String line = file.readLine();
        while (line != null) {
            StringTokenizer Tokens = new StringTokenizer(line, ",");
            while (Tokens.countTokens() != 0) {
                String tokenPlaceholder = Tokens.nextToken();
                RomanNumeral tokenNumeral = new RomanNumeral(tokenPlaceholder);
                sortedMap.put(tokenNumeral.getromanNum(), tokenNumeral.getarabicVal());
            }
            line = file.readLine();
        }
        file.close();
    }

    // This void function implements the action listener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openMenuItem) {

            JFileChooser fileChooser = new JFileChooser();
            // this goes through the files in your directory and you are allowed to open
            // whatever file you want
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();
                if (fileName.equals("input.txt")) {
                    // Perform action for input.txt file
                    JOptionPane.showMessageDialog(this, "You chose the Conversion chart!");

                    // Read input from file and use TreeMap to sort the roman numerals through the comparator class
                    TreeMap<String, Integer> sortedMap = new TreeMap<>(new RomanNumeralComparator());
                    initialize();
                    inputFromFile(selectedFile.getAbsolutePath(), sortedMap);
                    ShowStuffOnJFrame(sortedMap);
                }

                else {
                    // Perform action for other files
                    JOptionPane.showMessageDialog(this, "You chose " + fileName);
                }
            }
            // exits the code if you instead choose the quit option
        } else if (e.getSource() == quitMenuItem) {
            System.exit(0);
            // this is the roman to arabic converter
        } else if (e.getSource() == romanToArabicMenuItem) {
            String input = JOptionPane.showInputDialog(this, "Enter a Roman numeral:");

            // If it is a valid roman numeral, it will convert it to an arabic numeral and
            // show it in the message dialog

            try {
                RomanNumeral.valueOf(input);
                JOptionPane.showMessageDialog(this, "Arabic value: " + RomanNumeral.valueOf(input));
            } catch (IllegalRomanNumeralException i) {
                JOptionPane.showMessageDialog(this, i.getMessage());
            }
        }
    }

}
