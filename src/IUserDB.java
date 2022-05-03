import java.util.ArrayList;

public interface IUserDB {
  public void showAllUsers();
  public void addUser(User user);
  public User getUser(Integer id);
  public ArrayList<User> getAllUsers();
  public Integer amountOfUsers();
}
