package il.ac.hit.validation;

/**
 * Represents a Platinum user in the system.
 */
public class PlatinumUser extends User {

    /**
     * Constructs a PlatinumUser.
     *
     * @param username the username
     * @param email    the email
     * @param password the password
     * @param age      the age
     */
    public PlatinumUser(String username, String email, String password, int age) {
        super(username, email, password, age);
        // Initializes a platinum user using the parent constructor
    }

    @Override
    public String toString() {
        return "PlatinumUser{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", age=" + getAge() +
                '}';
    }
}