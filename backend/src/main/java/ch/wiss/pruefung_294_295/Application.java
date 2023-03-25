package ch.wiss.pruefung_294_295;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

    final static public String username = "root";
    final static public String password = "root";

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // JDBC code to execute SQL script
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mangalib", username, password);
                Statement stmt = conn.createStatement();) {
            String sql1 = "INSERT INTO role (name) VALUES ('ROLE_USER');"; 
			String sql2 = "INSERT INTO role (name) VALUES ('ROLE_ADMIN');";
            stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
            System.out.println("SQL script executed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@GetMapping("/")
	public String index() {
		return "Spring Boot Mangalib REST API!";
	}
}