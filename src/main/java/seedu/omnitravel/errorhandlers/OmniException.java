package seedu.omnitravel.errorhandlers;

/**
 * The OmniException class represents an exception specific to the OmniTravel application.
 * It extends the Java Exception class.
 */

public class OmniException extends Exception {
    public OmniException(String errorMessage){
        super(errorMessage);
    }
}
