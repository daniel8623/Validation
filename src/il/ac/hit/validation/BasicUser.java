package il.ac.hit.validation;

/**
 * Represents a Basic user in the system.
 */
public class BasicUser extends User {

    /**
     * Constructs a BasicUser.
     * * @param username the username
     * @param email    the email
     * @param password the password
     * @param age      the age
     */
    public BasicUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }
}