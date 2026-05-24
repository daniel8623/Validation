package il.ac.hit.validation;

/**
 * A demonstration class provided to test the UserValidation implementation.
 */
public class UserValidationV2Demo {

    /**
     * The main method to execute the validation test.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // Creating a new test user instance
        User user = new User("admin","admin@#yzw.co.il","abc123",34);

        // Setting up the first validation rule
        UserValidation validation1 = UserValidation.emailLengthBiggerThan10();

        // Setting up the second validation rule
        UserValidation validation2 = UserValidation.emailEndsWithIL();

        // Combining the validations using the 'and' combinator and applying to the user
        ValidationResult result = (validation1.and(validation2)).apply(user);

        // Checking the result and printing the appropriate message
        if(result.isValid()) {
            System.out.println("User is valid");
        } else {
            System.out.println("User is not valid");
        }
    }
}