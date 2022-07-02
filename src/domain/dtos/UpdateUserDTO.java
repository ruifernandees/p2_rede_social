package domain.dtos;

public class UpdateUserDTO {
    public String username;
    public String login;
    public String password;

    public UpdateUserDTO(String username, String login, String password) {
        this.username = username;
        this.login = login;
        this.password = password;
    }
}
