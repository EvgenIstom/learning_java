package model;

public record UserData(String email, String username, String password) {

    public UserData() {
        this("", "", "");
    }

    public UserData withEmail(String email) {
        return new UserData(email, this.username, this.password);
    }

    public UserData withUsername(String username) {
        return new UserData(this.email, username, this.password);
    }

    public UserData withPassword(String password) {
        return new UserData(this.email, this.username, password);
    }
}