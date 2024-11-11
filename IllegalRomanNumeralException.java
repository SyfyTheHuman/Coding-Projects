
// this class will be used to throw an exception if the user inputs
public class IllegalRomanNumeralException extends IllegalArgumentException {
    public IllegalRomanNumeralException(String r) {
        super("This input " + r + " is invalid");

    }
}
