package fr.breci.Realm.Managers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
  
public class SQLManager extends Database
{
    private String user = "";
    private String database = "";
    private String password = "";
    private String port = "";
    private String hostname = "";
    private Connection c = null;
  
  
    public SQLManager(String hostname, String portnmbr, String database, String username, String password) {
        this.hostname = hostname;
        this.port = portnmbr;
        this.database = database;
        this.user = username;
        this.password = password;
    }
    public Connection open() {
    	System.out.println("user : " + user);
    	System.out.println("database : " + database);
    	System.out.println("password : " + password);
    	System.out.println("port : " + port);
    	System.out.println("hostname : " + hostname);
    		try {
            Class.forName("com.mysql.jdbc.Driver");
            this.c = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
            System.out.println("Connexion SQL faite pour PrivateHorse");
            return c;
        } catch (SQLException e) {
            System.out.println("Could not connect to MySQL server! because: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
        }
        return this.c;
    }
    public boolean checkConnection() {
        if (this.c != null) {
            return true;
        }
        return false;
    }
    public Connection getConn() {
        return this.c;
    }
    public void closeConnection(Connection c) {
        c = null;
    }
} 