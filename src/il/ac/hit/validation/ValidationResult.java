package il.ac.hit.validation;

import java.util.Optional;

/**
 * Represents the result of a validation process.
 */
public interface ValidationResult {

    /**
     * Checks if the validation was successful.
     *
     * @return true if valid, false otherwise
     */
    boolean isValid();

    /**
     * Retrieves the reason for the validation failure, if any.
     *
     * @return an Optional containing the failure reason, or an empty Optional if valid
     */
    Optional<String> getReason();
}