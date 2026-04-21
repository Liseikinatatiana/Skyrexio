package user;

public class User {
    private String username;
    private String password;
    private String errorMessage;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User (String username,String password, String errorMessage){
        this.username = username;
        this.password = password;
        this.errorMessage = errorMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
