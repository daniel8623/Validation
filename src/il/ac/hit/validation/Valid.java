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
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Optional<String> getReason() {
        // A valid result inherently has no error reason
        return Optional.empty();
    }
}