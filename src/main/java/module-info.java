module com.example.projet_bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.apache.poi.ooxml;
    requires  pdfbox;
    opens com.example.projet_bibliotheque to javafx.fxml, jakarta.xml.bind, org.apache.poi.ooxml,pdfbox;
    exports com.example.projet_bibliotheque.Controller;
    opens com.example.projet_bibliotheque.Controller to jakarta.xml.bind, javafx.fxml, org.apache.poi.ooxml, pdfbox;
    exports com.example.projet_bibliotheque.DAO;
    opens com.example.projet_bibliotheque.DAO to jakarta.xml.bind, javafx.fxml, org.apache.poi.ooxml, pdfbox;
    exports com.example.projet_bibliotheque.Model;
    opens com.example.projet_bibliotheque.Model to jakarta.xml.bind, javafx.fxml, org.apache.poi.ooxml, pdfbox;
    exports com.example.projet_bibliotheque.Vue;
    opens com.example.projet_bibliotheque.Vue to jakarta.xml.bind, javafx.fxml, org.apache.poi.ooxml, pdfbox;
}