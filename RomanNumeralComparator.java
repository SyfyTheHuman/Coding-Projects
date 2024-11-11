
import java.util.Comparator;

//this entire comparator class is used to compare the roman numerals and sort them
public class RomanNumeralComparator implements Comparator<String> {

    public int compare(String r1, String r2) {
        int roman1 = RomanNumeral.valueOf(r1);
        int roman2 = RomanNumeral.valueOf(r2);
        return Integer.compare(roman1, roman2);
    }
}
