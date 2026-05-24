package il.ac.hit.validation;

import java.util.Optional;

/**
 * Represents a failed validation result with a specific reason.
 */
public class Invalid implements ValidationResult {

    /** The reason for the validation failure. */
    private final String reason;

    /**
     * Constructs an Invalid result with the provided reason.
     *
     * @param reason the explanation of why validation failed
     * @throws IllegalArgumentException if the reason is null
     */
    public Invalid(String reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Reason cannot be null");
        }
        this.reason = reason;
        // The reason is successfully assigned to the instance variable
    }

    @Override
    public boolean isValid() {
        // Always returns false for an invalid result
        return false;
    }

    @Override
    public Optional<String> getReason() {
        // Packaging the reason safely in an Optional wrapper
        return Optional.of(reason);
    }

    @Override
    public String toString() {
        return "Invalid{reason='" + reason + "'}";
    }
}