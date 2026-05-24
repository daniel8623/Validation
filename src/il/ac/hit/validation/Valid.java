package il.ac.hit.validation;

import java.util.Optional;

/**
 * Represents a successful validation result.
 */
public class Valid implements ValidationResult {

    /**
     * Constructs a Valid instance.
     */
    public Valid() {
        // Empty constructor for valid result initialization
    }

    @Override
    public boolean isValid() {
        // Always returns true for a valid result
        return true;
    }

    @Override
    public Optional<String> getReason() {
        // A valid result inherently has no error reason
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Valid{status='Validation Passed'}";
    }
}