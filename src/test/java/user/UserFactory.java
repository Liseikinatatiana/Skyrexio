package user;

import utils.PropertyReader;

public class UserFactory {
    public static User standardUser() {
        return new User(
                PropertyReader.getProperty("saucedemoo.user"),
                PropertyReader.getProperty("saucedemoo.password"));
    }

    public static User LockedOutUser() {
        return new User(
                PropertyReader.getProperty("saucedemoo.locked_user"),
                PropertyReader.getProperty("saucedemoo.password"),
                PropertyReader.getProperty("saucedemoo.error_locked"));
    }

    public static User emptyUsernameUser() {
        return new User(
                PropertyReader.getProperty("saucedemoo.no_user"),
                PropertyReader.getProperty("saucedemoo.password"),
                PropertyReader.getProperty("saucedemoo.error_no_user"));
    }

    public static User emptyPasswordUser() {
        return new User(
                PropertyReader.getProperty("saucedemoo.user"),
                PropertyReader.getProperty("saucedemoo.no_password"),
                PropertyReader.getProperty("saucedemoo.error_no_password"));
    }

    public static User incorrectUser() {
        return new User(
                PropertyReader.getProperty("saucedemoo.incorrect_user"),
                PropertyReader.getProperty("saucedemoo.password"),
                PropertyReader.getProperty("saucedemoo.error_incorrect_user"));
    }
}
