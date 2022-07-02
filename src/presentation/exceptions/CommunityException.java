package presentation.exceptions;
public class CommunityException extends Exception {
  public CommunityException(String message) {
    super("Erro de comunidade: " + message);
  } 
}
