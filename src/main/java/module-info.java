module com.example.projet_bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.apache.poi.ooxml;
    requires  pdfbox;
    opens com.example.projet_bibliotheque to javafx.fxml, jakarta.xml.bind, org.apache.poi.ooxml,pdfbox;
    exports com.example.projet_bibliotheque;
}