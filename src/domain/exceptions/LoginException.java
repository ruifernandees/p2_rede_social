package domain.exceptions;

public class LoginException extends Exception {
  public LoginException(String message) {
    super("Erro ao logar: " + message);
  } 
}
