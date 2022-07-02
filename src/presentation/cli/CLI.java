package presentation.cli;

public class CLI {
    public ICLIStrategy strategy;

    public boolean execute() {
        return this.strategy.execute();
    }

    public void setStrategy(ICLIStrategy strategy) {
        this.strategy = strategy;
    }
}