package me.matoosh.esketit.rules;

/**
 * Exception when parsing.
 */
public class ParsingError extends Exception {
    String message;

    public ParsingError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
