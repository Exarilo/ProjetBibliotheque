module com.example.projet_bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;

    opens com.example.projet_bibliotheque to javafx.fxml, jakarta.xml.bind;
    exports com.example.projet_bibliotheque;
}