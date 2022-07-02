package singletons;

import infra.MemoryDatabase;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private MemoryDatabase database;
    
    private DatabaseConnection() {
        this.database = new MemoryDatabase();
    }

    public static DatabaseConnection getConnection() {
        if (DatabaseConnection.instance == null) {
            DatabaseConnection.instance = new DatabaseConnection(); 
        }
        return DatabaseConnection.instance;
    }

    public MemoryDatabase getMemoryDatabase() {
        return this.database;
    }
}
