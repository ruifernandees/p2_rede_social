package presentation.exceptions;
public class RemoveAccountException extends Exception {
  public RemoveAccountException(String message) {
    super("Erro ao remover conta: " + message);
  } 
}
