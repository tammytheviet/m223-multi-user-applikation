package ch.wiss.pruefung_294_295;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL {

    final static public String username = "root";
    final static public String password = "root";
    
    public static void SQLscript() {
        try (Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mangalib", username, password);
                Statement s = c.createStatement();) {
            String script1 = "INSERT INTO role (name) VALUES ('ROLE_USER');"; //Inserts the role 'ROLE_USER' into the table 'role' in the database 'mangalib'
			String script2 = "INSERT INTO role (name) VALUES ('ROLE_ADMIN');"; //Inserts the role 'ROLE_ADMIN' into the table 'role' in the database 'mangalib'
            s.executeUpdate(script1); //executes the first script
			s.executeUpdate(script2); //executes the second script
        } catch (Exception ex) {
            ex.printStackTrace(); //handles exceptions and errors
        }
    }
}
