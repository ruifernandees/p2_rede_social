package domain.dtos;

public class CreateUserDTO {
    public String username;
    public String login;
    public String password;

    public CreateUserDTO(String username, String login, String password) {
        this.username = username;
        this.login = login;
        this.password = password;
    }
}
