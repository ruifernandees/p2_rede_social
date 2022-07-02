package presentation.exceptions;
public class MessageException extends Exception {
  public MessageException(String message) {
    super("Erro na mensagem: " + message);
  } 
}
