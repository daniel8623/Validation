package il.ac.hit.validation;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Utility class providing operations on User objects.
 */
public class UserUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private UserUtils() {
    }

    /**
     * Sorts an array of users using the provided comparator.
     * This method represents the Template Method design pattern.
     *
     * @param users      the array of users to sort
     * @param comparator the functionality for comparing two users
     * @throws IllegalArgumentException if users or comparator are null
     */
    public static void sort(User[] users, Comparator<User> comparator) {
        // Validation check to avoid NullPointerException
        if (users == null || comparator == null) {
            throw new IllegalArgumentException("Users array and comparator must not be null");
        }

        // Sorting the array in-place
        Arrays.sort(users, comparator);
    }
}