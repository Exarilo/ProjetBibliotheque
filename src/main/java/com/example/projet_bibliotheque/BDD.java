package com.example.projet_bibliotheque;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD {

    Connection connection;
    private String DB_URL = "jdbc:mysql://localhost:3306/biblio";
    private String DB_USER = "root";
    private String DB_PASSWORD = "";

    public BDD() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        if (this.connection == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Vous êtes connecté");
                alert.showAndWait();

            }
            catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException err ) {
                System.out.println( err.getMessage( ) );
            }
        }
        return this.connection;
    }

}
