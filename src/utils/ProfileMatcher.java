package utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.User;

public class ProfileMatcher {
  public static Boolean hasWhitespace(String text)  {
    Pattern whitespacePattern = Pattern.compile("\\s");
    Matcher hasWhitespaceMatcher = whitespacePattern.matcher(text);
    return hasWhitespaceMatcher.find();
  }

  public static Boolean hasSomeAlphanumericChar(String text) {
    Pattern alphaPattern = Pattern.compile("[a-zA-Z0-9_-]");
    Matcher hasAlphaMatcher = alphaPattern.matcher(text);
    return hasAlphaMatcher.find();
  }
  public static Boolean hasSomeAlphaChar(String text) {
    Pattern alphaPattern = Pattern.compile("[a-zA-Z]");
    Matcher hasAlphaMatcher = alphaPattern.matcher(text);
    return hasAlphaMatcher.find();
  }

  public static Boolean hasNonAlphaChar(String text)  {
    Pattern nonAlphanumericPattern = Pattern.compile("[^a-zA-Z\\s]+");
    Matcher usernameMatcher = nonAlphanumericPattern.matcher(text);
    return usernameMatcher.find();
  }

  public static Boolean userExists(String login, IUserDB db) {
    for (User user : db.getAllUsers()) {
      if (user.login.equals(login)) {
        return true;
      }
    }
    return false;
  }

}
