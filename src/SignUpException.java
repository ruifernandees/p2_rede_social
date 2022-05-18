public class SignUpException extends Exception {
  public SignUpException(String message) {
    super("Erro ao criar conta: " + message);
  } 
}
