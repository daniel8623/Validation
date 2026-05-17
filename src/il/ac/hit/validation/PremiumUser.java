package il.ac.hit.validation;

/**
 * Represents a Premium user in the system.
 */
public class PremiumUser extends User {

    /**
     * Constructs a PremiumUser.
     * * @param username the username
     * @param email    the email
     * @param password the password
     * @param age      the age
     */
    public PremiumUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }
}