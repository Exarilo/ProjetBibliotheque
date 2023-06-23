package com.example.projet_bibliotheque;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La class BDD sert a mettre les livres en base de donnee
 * Elle gere tout ce qui est connection avec le sql
 */
public class BDD {

    Connection connection;
    private String DB_URL = "jdbc:mysql://localhost:3306/biblio";
    private String DB_USER = "root";
    private String DB_PASSWORD = "";

    /**
     * Permet de definir si la connection est ouverte ou non
     */
    public boolean isConnected = false;


    /**
     * Constructeur de la BDD lors de l'instanciation creer une connection
     */
    public BDD() {
        isConnected=this.CreateConnection();
    }

    /**
     * Setter de la connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    /**
     * Permet d'ouvrir la connecteur il vient mettre a jour l'etat de la connection
     */
    public boolean CreateConnection() {
        if (this.connection == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                isConnected=true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Vous êtes connecté");
                alert.showAndWait();
                return true;

            }
            catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException err ) {
                System.out.println( err.getMessage( ) );
                return false;
            }
        }
        return true;
    }
    /**
     * Permet de fermer la connection il vient mettre a jour l'etat de la connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                isConnected = false;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Vous êtes déconnecté");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
