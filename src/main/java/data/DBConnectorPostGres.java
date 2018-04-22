package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectorPostGres {
    private Connection connection = null;

    //Constants
    private static final String IP	     = "jdbc:postgresql://167.99.249.26";
    private static final String PORT     = "";
    public  static final String DATABASE = "/appdev/chinook";
    private static final String USERNAME = "appdev";
    private static final String PASSWORD = "appdev";

    public DBConnectorPostGres() throws Exception {

        Class.forName("org.postgresql.Driver");
        this.connection = null;
        connection = DriverManager.getConnection(IP+DATABASE,USERNAME, PASSWORD);

    }

    public Connection getConnection() {
        return this.connection;
    }

}