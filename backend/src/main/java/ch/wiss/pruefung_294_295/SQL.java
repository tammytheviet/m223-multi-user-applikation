package ch.wiss.pruefung_294_295;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Diese Klasse wird verwendet, um die Datenbank 'mangalib' und die Tabelle 'role' in der Datenbank 'mangalib' zu erstellen.
 * Es werden auch die Rollen 'ROLE_USER' und 'ROLE_ADMIN' in die Tabelle 'role' eingefügt.
 * @author Tam Lai Nguyen, Fabio Facundo
 * @version 1.0
 * @param username: Benutzername für die Datenbank 'mangalib'
 * @param password: Passwort für die Datenbank 'mangalib'
 * @param script1: SQL-Script, dass die Rolle 'ROLE_USER' in die Tabelle 'role' in der Datenbank 'mangalib' einfügt
 * @param script2: SQL-Script, dass die Rolle 'ROLE_ADMIN' in die Tabelle 'role' in der Datenbank 'mangalib' einfügt
 */

public class SQL {

    //unveränderlicher Benutzername
    private static final String username = "root";

    //unveränderliches Passwort
    private static final String password = System.getenv("DB_PASSWORD");

    public static void SQLscript()
    {
        //Erstellt eine Verbindung zur Datenbank 'mangalib' mit dem Benutzernamen 'root' und dem Passwort 'root'
        try (Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mangalib", username, password);

            //Erstellt ein Statement, dass SQL-Scripte ausführen kann
             Statement s = c.createStatement();)

        {
            //Fügt die Rolle 'ROLE_USER' in die Tabelle 'role' in der Datenbank 'mangalib' ein
            String script1 = "INSERT INTO role (name) VALUES ('ROLE_USER');";

            //Fügt die Rolle 'ROLE_ADMIN' in die Tabelle 'role' in der Datenbank 'mangalib' ein
			String script2 = "INSERT INTO role (name) VALUES ('ROLE_ADMIN');";

            //Führt die beiden SQL-Scripte aus
            s.executeUpdate(script1);
			s.executeUpdate(script2);
        }

        //Falls ein Fehler auftritt, wird die Exception abgefangen und der Stacktrace des Fehlers wird ausgegeben
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
