package com.example.projet_bibliotheque;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/** Controller principal de l'application. */
public class MainController implements Initializable {
    private Bibliotheque bibliotheque;
    @FXML private TableView<Livre> tableView;
    @FXML private TableColumn<Livre, String> champTitre;
    @FXML private TableColumn<Livre, String> champNomAuteur;
    @FXML private TableColumn<Livre, String> champPrenomAuteur;
    @FXML private TableColumn<Livre, String> champPresentation;
    @FXML private TableColumn<Livre, Integer> champParution;
    @FXML private TableColumn<Livre, Integer> champColonne;
    @FXML private TableColumn<Livre, Integer> champRangee;

    @FXML
    /**
     Méthode appelée lorsqu'on clique sur le bouton "Nouveau Livre".
     Elle affiche une boîte de dialogue d'information pour dire qu'un livre a bien été rajouté.
     */
    void handleNouveauLivre(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Et boom c'est validé");
        alert.showAndWait();
    }

    @FXML
    /**
     Méthode appelée lorsqu'on clique sur le bouton "Ouvrir de la menu bar".
     Elle ouvre une boîte de dialogue de sélection de fichier.
     */
    void handleOuvrirMenuItemAction(ActionEvent event) throws XMLStreamException, IOException {

        ObservableList<Livre> livres3 = FXCollections.observableArrayList();
        Livre livre = new Livre();
        livre.setTitre("adzd");
        livre.setNomAuteur("cefe");
        livre.setPrenomAuteur("ffefe");
        livre.setParution(4);
        livre.setPresentation("4zfzf");
        livre.setColonne(1);
        livre.setRangee(2);
        livres3.add(livre);

        Livre livre2 = new Livre();
        livre2.setTitre("adzd");
        livre2.setNomAuteur("cefde");
        livre2.setPrenomAuteur("ffedfe");
        livre2.setParution(4);
        livre2.setPresentation("4zfzf");
        livre2.setColonne(1);
        livre2.setRangee(2);
        livres3.add(livre2);





        tableView.setItems(livres3);






        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an XML file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));

        File selectedFile = fileChooser.showOpenDialog(null);


        if (selectedFile != null){
            bibliotheque = new Bibliotheque(selectedFile.getPath());
            tableView.getItems().addAll(bibliotheque.livre);
            ObservableList<Livre> livres = FXCollections.observableList(bibliotheque.livre);
            tableView.setItems(livres);

//            tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//                if (newValue != null) {
//
//                    /*
//                    champTitre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
//                    champNomAuteur.setText(newValue.auteur.nom);
//                    champPrenomAuteur.setText(newValue.auteur.prenom);
//                    champPresentation.setText(newValue.presentation);
//                    champParution.setText(Integer.toString(newValue.parution));
//                    champColonne.setText(Integer.toString(newValue.colonne));
//                    champRangee.setText(Integer.toString(newValue.rangee));*/
//                }
//            });
        }
    }
    @FXML
    /**
     Méthode permettant de quitter l'application
     */
    void handleQuitterMenuItemAction(ActionEvent event) {
        Platform.exit();
    }


    /**
     Méthode permettant d'ouvrir une form avec 3 images dedans (les notres)
     */
    @FXML
    void handleInfosMenuItemAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("C'est nous !");
        stage.setResizable(false);

        VBox vbox = new VBox();
        vbox.getChildren().add(new ImageView(new Image("https://static.vecteezy.com/ti/vecteur-libre/t2/2002332-ablack-man-avatar-character-isolated-icon-gratuit-vectoriel.jpg")));
        vbox.getChildren().add(new ImageView(new Image("https://static.vecteezy.com/ti/vecteur-libre/t2/2002332-ablack-man-avatar-character-isolated-icon-gratuit-vectoriel.jpg")));
        vbox.getChildren().add(new ImageView(new Image("https://static.vecteezy.com/ti/vecteur-libre/t2/2002332-ablack-man-avatar-character-isolated-icon-gratuit-vectoriel.jpg")));

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Crée un nouveau livre.
     */
    @FXML
    protected void creerLivre() {
        tableView.getSelectionModel().clearSelection();
        //clearChamps();
    }

    /**
     * Efface le contenu des champs de saisie
     */
    private void clearChamps() {
        champTitre.setText("");
        champNomAuteur.setText("");
        champPrenomAuteur.setText("");
        champPresentation.setText("");
        champParution.setText("");
        champColonne.setText("");
        champRangee.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        champTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        champNomAuteur.setCellValueFactory(new PropertyValueFactory<>("nomAuteur"));
        champPrenomAuteur.setCellValueFactory(new PropertyValueFactory<>("prenomAuteur"));
        champPresentation.setCellValueFactory(new PropertyValueFactory<>("presentation"));
        champParution.setCellValueFactory(new PropertyValueFactory<>("parution"));
        champColonne.setCellValueFactory(new PropertyValueFactory<>("colonne"));
        champRangee.setCellValueFactory(new PropertyValueFactory<>("rangee"));
    }
}