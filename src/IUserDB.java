import java.util.ArrayList;

import domain.entities.User;

public interface IUserDB {
  public void showAllUsers();
  public void addUser(User user);
  public User getUser(Integer id);
  public ArrayList<User> getAllUsers();
  public Integer amountOfUsers();
}
