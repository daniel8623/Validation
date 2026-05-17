package il.ac.hit.validation;

/**
 * A factory class for creating different types of users.
 * Implements the Factory Method design pattern.
 */
public class UserFactory {

    /**
     * Default constructor for the factory.
     */
    public UserFactory() {
    }

    /**
     * Factory method to instantiate specific User subclasses based on a string literal.
     *
     * @param userType the type of the user ("basic", "premium", "platinum")
     * @param username the username
     * @param email    the email address
     * @param password the password
     * @param age      the age
     * @return a new User object of the appropriate subclass
     * @throws IllegalArgumentException if the userType is null or unknown
     */
    public User createUser(String userType, String username, String email, String password, int age) {
        // Validating the input before processing
        if (userType == null) {
            throw new IllegalArgumentException("User type cannot be null");
        }

        // Processing the specific type using a switch statement
        switch (userType.toLowerCase()) {
            case "basic":
                return new BasicUser(username, email, password, age);
            case "premium":
                return new PremiumUser(username, email, password, age);
            case "platinum":
                return new PlatinumUser(username, email, password, age);
            default:
                throw new IllegalArgumentException("Unknown user type: " + userType);
        }
    }
}