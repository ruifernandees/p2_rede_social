package presentation.cli;

public class CLI {
    public CLIStrategy strategy;

    public boolean execute() {
        return this.strategy.execute();
    }

    public void setStrategy(CLIStrategy strategy) {
        this.strategy = strategy;
    }
}