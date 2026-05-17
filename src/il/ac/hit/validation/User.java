package il.ac.hit.validation;

import java.util.Objects;

/**
 * Represents a user in the software application.
 * This class holds basic user information and ensures data integrity upon instantiation and modification.
 */
public class User {

    /** The username of the user. */
    private String username;

    /** The email address of the user. */
    private String email;

    /** The password of the user. */
    private String password;

    /** The age of the user. */
    private int age;

    /**
     * Primary constructor to initialize a User object.
     * Delegates initialization to the setter methods to ensure consistent validation.
     *
     * @param username the user's username
     * @param email    the user's email address
     * @param password the user's password
     * @param age      the user's age
     */
    public User(String username, String email, String password, int age) {
        // We use setters here to avoid bypassing the validation logic
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setAge(age);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets the username after validating it is not null or empty.
     *
     * @param username the new username
     * @throws IllegalArgumentException if the username is null or empty
     */
    public void setUsername(String username) {
        // Validating the argument before assigning it to the field
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.username = username;
    }

    /**
     * Sets the email after validating it is not null or empty.
     *
     * @param email the new email
     * @throws IllegalArgumentException if the email is null or empty
     */
    public void setEmail(String email) {
        // Ensures email integrity at a basic level
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.email = email;
    }

    /**
     * Sets the password after validating it is not null or empty.
     *
     * @param password the new password
     * @throws IllegalArgumentException if the password is null or empty
     */
    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        this.password = password;
    }

    /**
     * Sets the age after validating it is a positive number.
     *
     * @param age the new age
     * @throws IllegalArgumentException if the age is negative
     */
    public void setAge(int age) {
        // Logical check to prevent impossible user attributes
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        // When overriding equals, hashCode must also be overridden
        return Objects.hash(username, email, password, age);
    }

    @Override
    public String toString() {
        // Providing a human-readable representation of the object
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}