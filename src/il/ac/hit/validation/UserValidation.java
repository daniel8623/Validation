package il.ac.hit.validation;

import java.util.function.Function;

/**
 * Defines a validation function for a User, implementing the Combinator design pattern.
 * Allows chaining multiple validations together.
 */
public interface UserValidation extends Function<User, ValidationResult> {

    // =========================================
    // Instance Methods (Combinators)
    // =========================================

    /**
     * Combines this validation with another using a logical AND.
     *
     * @param other the other validation to apply
     * @return a combined UserValidation
     */
    default UserValidation and(UserValidation other) {
        return user -> {
            // Evaluates the current condition first
            var result = this.apply(user);

            // Proceeds to the second condition only if the first is valid
            return result.isValid() ? other.apply(user) : result;
        };
    }

    /**
     * Combines this validation with another using a logical OR.
     *
     * @param other the other validation to apply
     * @return a combined UserValidation
     */
    default UserValidation or(UserValidation other) {
        return user -> {
            var result = this.apply(user);

            // Short-circuits and returns true if the first condition is already valid
            if (result.isValid()) {
                return result;
            }
            return other.apply(user);
        };
    }

    /**
     * Combines this validation with another using a logical XOR.
     *
     * @param other the other validation to apply
     * @return a combined UserValidation
     */
    default UserValidation xor(UserValidation other) {
        return user -> {
            // Applying both validations to check their states
            var res1 = this.apply(user);
            var res2 = other.apply(user);

            // Uses the bitwise XOR operator to ensure strict exclusivity
            if (res1.isValid() ^ res2.isValid()) {
                return new Valid();
            } else {
                return new Invalid("XOR condition failed: strictly one condition must be valid");
            }
        };
    }

    // =========================================
    // Static Methods (Aggregators)
    // =========================================

    /**
     * Ensures all provided validations are fulfilled.
     *
     * @param validations a variable number of validations
     * @return a combined UserValidation
     */
    static UserValidation all(UserValidation... validations) {
        return user -> {
            // Iterating over all provided rules
            for (var validation : validations) {
                var result = validation.apply(user);

                // Halts at the first failure encountered
                if (!result.isValid()) {
                    return result;
                }
            }
            return new Valid();
        };
    }

    /**
     * Ensures none of the provided validations are fulfilled.
     *
     * @param validations a variable number of validations
     * @return a combined UserValidation
     */
    static UserValidation none(UserValidation... validations) {
        return user -> {
            // Iterating to ensure all conditions strictly fail
            for (var validation : validations) {
                var result = validation.apply(user);
                if (result.isValid()) {
                    return new Invalid("NONE condition failed: at least one validation passed");
                }
            }
            return new Valid();
        };
    }

    // =========================================
    // Static Methods (Specific Rules)
    // =========================================

    /**
     * @return a validation checking if the email ends with "il"
     */
    static UserValidation emailEndsWithIL() {
        return user -> user.getEmail().endsWith("il") ? new Valid() : new Invalid("Email must end with 'il'");
    }

    /**
     * @return a validation checking if the email length is strictly greater than 10
     */
    static UserValidation emailLengthBiggerThan10() {
        return user -> user.getEmail().length() > 10 ? new Valid() : new Invalid("Email length must be bigger than 10");
    }

    /**
     * @return a validation checking if the password length is strictly greater than 8
     */
    static UserValidation passwordLengthBiggerThan8() {
        return user -> user.getPassword().length() > 8 ? new Valid() : new Invalid("Password length must be bigger than 8");
    }

    /**
     * @return a validation checking if the password contains only letters and numbers
     */
    static UserValidation passwordIncludesLettersNumbersOnly() {
        // Utilizing Regex to enforce alphanumeric characters only
        return user -> user.getPassword().matches("^[a-zA-Z0-9]+$") ? new Valid() : new Invalid("Password must include letters and numbers only");
    }

    /**
     * @return a validation checking if the password includes a dollar sign
     */
    static UserValidation passwordIncludesDollarSign() {
        return user -> user.getPassword().contains("$") ? new Valid() : new Invalid("Password must include the $ sign");
    }

    /**
     * @return a validation checking if the password is not identical to the username
     */
    static UserValidation passwordIsDifferentFromUsername() {
        return user -> !user.getPassword().equals(user.getUsername()) ? new Valid() : new Invalid("Password must be different from username");
    }

    /**
     * @return a validation checking if the user's age is strictly greater than 18
     */
    static UserValidation ageBiggerThan18() {
        return user -> user.getAge() > 18 ? new Valid() : new Invalid("Age must be bigger than 18");
    }

    /**
     * @return a validation checking if the username length is strictly greater than 8
     */
    static UserValidation usernameLengthBiggerThan8() {
        return user -> user.getUsername().length() > 8 ? new Valid() : new Invalid("Username length must be bigger than 8");
    }
}