package presentation.cli;

import java.util.Scanner;

public abstract class CLIStrategy {
  protected Scanner reader;

  public CLIStrategy() {
      this.reader = new Scanner(System.in);
  }

  public abstract boolean execute();
}
