module com.example.projet_bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;

    requires jakarta.xml.bind;
    requires java.xml.bind;


    opens com.example.projet_bibliotheque to javafx.fxml, jakarta.xml.bind;
    exports com.example.projet_bibliotheque;
}