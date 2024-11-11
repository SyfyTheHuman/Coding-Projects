import javax.swing.JFrame;
import java.util.HashMap;
import java.util.Map;

public class RomanNumeral extends JFrame {

    private String romanNum; // this private instance variable will be used for roman numerals
    private int arabicVal; // this private instance variable will be used for arabic values

    public RomanNumeral(String r) {
        romanNum = r;
        arabicVal = valueOf(r);

    }

    // What this valueOf method does is it takes in a string of roman numerals and
    // converts it to an arabic value through a HashMap
    public static int valueOf(String romanNum) throws IllegalRomanNumeralException {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < romanNum.length(); i++) {
            int currentVal = map.getOrDefault(romanNum.charAt(i), 0);
            if (currentVal == 0) {
                throw new IllegalRomanNumeralException(romanNum);
            }
            int nextVal = 0;
            if (i < romanNum.length() - 1) {
                nextVal = map.getOrDefault(romanNum.charAt(i + 1), 0);
            } else {
                nextVal = 0;
            }
            if (currentVal < nextVal) {
                result -= currentVal;
            } else {
                result += currentVal;
            }
        }
        return result;
    }

    public String getromanNum() { // this is getting the private instance variable romanNum
        return romanNum;
    }

    public int getarabicVal() { // this is getting the private instance variable arabicVal
        return arabicVal;
    }

    public void setromanNum(String letter) { // this is setting the private instance variable romanNum

        RomanNumeral.valueOf(letter);

        romanNum = letter;

    }

    // The equal method checks whether or not two Roman Numerals are equal to each
    // other.
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass() || arabicVal != ((RomanNumeral) other).getarabicVal())
            return false;
        return true;
    }

    // returns the Roman Numeral and Arabic value as a string seperated by space
    public String toString() {
        String returnStr = "";
        returnStr += romanNum + " " + arabicVal;
        return returnStr;
    }

    // compareTo returns true if the Roman Numeral is greater than the other Roman
    // Numeral
    public boolean compareTo(RomanNumeral next) {
        if (this.arabicVal > next.getarabicVal())
            return true;
        return false;
    }

}