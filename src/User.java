public class User {
    public String username;
    public String login;
    public String pwd;
    private Boolean logged = false;

    public User(String username, String login, String pwd) {
        this.username = username;
        this.login = login;
        this.pwd = pwd;   
    }   

    @Override
    public String toString() {
        String result = "==== " + this.username + " ====";
        result += "\nLogin: " + this.login;
        result += "\nPassword: " + this.pwd;
        return result;
    }

    public Boolean isLogged() {
        return this.logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }
}