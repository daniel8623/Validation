package il.ac.hit.validation;

import java.util.Comparator;

/**
 * Comprehensive tests for the Validation Library.
 */
public class UserValidationComprehensiveDemo {

    /**
     * Main method running all comprehensive tests.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("=== Starting Comprehensive Tests ===");

        // Test User
        User testUser = new User("daniel123", "daniel@hit.ac.il", "P@ssw0rd1", 25);

        // ---------------------------------------------------------
        // Test 1: XOR Logic Trap (When BOTH are valid)
        // ---------------------------------------------------------
        System.out.println("\n--- Test 1: XOR Logic ---");
        // Age is > 18 (True), Email ends with 'il' (True)
        UserValidation v1 = UserValidation.ageBiggerThan18();
        UserValidation v2 = UserValidation.emailEndsWithIL();
        ValidationResult xorResult = v1.xor(v2).apply(testUser);

        // Expected: FALSE! Because XOR requires strictly ONE to be true.
        System.out.println("XOR both valid: " + xorResult.isValid() + " | Expected: false");
        if (!xorResult.isValid()) {
            System.out.println("Reason: " + xorResult.getReason().orElse("No reason"));
        }

        // ---------------------------------------------------------
        // Test 2: NONE Logic Trap
        // ---------------------------------------------------------
        System.out.println("\n--- Test 2: NONE Logic ---");
        // passwordIncludesDollarSign -> False (doesn't have $)
        // passwordIsDifferentFromUsername -> True (P@ssw0rd1 != daniel123)
        UserValidation noneTest = UserValidation.none(
                UserValidation.passwordIncludesDollarSign(),
                UserValidation.passwordIsDifferentFromUsername()
        );
        ValidationResult noneResult = noneTest.apply(testUser);

        // Expected: FALSE. 'none' means ALL conditions must fail. Since the second one is true, it fails.
        System.out.println("NONE test: " + noneResult.isValid() + " | Expected: false");

        // ---------------------------------------------------------
        // Test 3: ALL with Varargs
        // ---------------------------------------------------------
        System.out.println("\n--- Test 3: ALL Logic ---");
        UserValidation allTest = UserValidation.all(
                UserValidation.usernameLengthBiggerThan8(),
                UserValidation.emailLengthBiggerThan10(),
                UserValidation.ageBiggerThan18()
        );
        System.out.println("ALL test: " + allTest.apply(testUser).isValid() + " | Expected: true");

        // ---------------------------------------------------------
        // Test 4: Factory Pattern Verification
        // ---------------------------------------------------------
        System.out.println("\n--- Test 4: Factory Pattern ---");
        UserFactory factory = new UserFactory();
        User premium = factory.createUser("premium", "admin", "admin@hit.il", "12345", 30);
        System.out.println("Is PremiumUser? " + (premium instanceof PremiumUser) + " | Expected: true");

        // ---------------------------------------------------------
        // Test 5: Template Method Verification (Sorting)
        // ---------------------------------------------------------
        System.out.println("\n--- Test 5: Template Method (Sort) ---");
        User[] users = {
                new User("zebra", "z@il", "123", 20),
                new User("alpha", "a@il", "123", 30),
                new User("delta", "d@il", "123", 25)
        };
        // Sorting alphabetically by username
        UserUtils.sort(users, Comparator.comparing(User::getUsername));
        System.out.println("First user after sort: " + users[0].getUsername() + " | Expected: alpha");

        // ---------------------------------------------------------
        // Test 6: Constructor Validation (Defensive Programming)
        // ---------------------------------------------------------
        System.out.println("\n--- Test 6: Illegal Arguments ---");
        try {
            User badUser = new User("", "test@il", "pass", 20);
            System.out.println("Empty username test: FAILED (Should have thrown exception)");
        } catch (IllegalArgumentException e) {
            System.out.println("Empty username test: SUCCESS (Caught: " + e.getMessage() + ")");
        }

        try {
            User badAgeUser = new User("validUser", "test@il", "pass", -5);
            System.out.println("Negative age test: FAILED (Should have thrown exception)");
        } catch (IllegalArgumentException e) {
            System.out.println("Negative age test: SUCCESS (Caught: " + e.getMessage() + ")");
        }
    }
}