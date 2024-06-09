package Program_package;

public class IllegalArgumentNameException extends Exception {

    public IllegalArgumentNameException(String message) {
        super(message);
        System.out.println(message);
    }
}