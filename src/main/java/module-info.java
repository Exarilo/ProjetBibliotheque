module com.example.projet_bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;
    requires poi.ooxml;
    requires poi.ooxml.schemas;
    requires java.sql;

    opens com.example.projet_bibliotheque to javafx.fxml, jakarta.xml.bind;
    exports com.example.projet_bibliotheque;
}