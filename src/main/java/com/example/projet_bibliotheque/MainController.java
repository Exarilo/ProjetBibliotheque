package com.example.projet_bibliotheque;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;

/** Controller principal de l'application. */
public class MainController implements Initializable {
    ObservableList<Livre> livres;
    private Bibliotheque bibliotheque;
    @FXML private TableView<Livre> tableView;
    @FXML private TableColumn<Livre, String> champTitre;
    @FXML private TableColumn<Livre, String> champNomAuteur;
    @FXML private TableColumn<Livre, String> champPrenomAuteur;
    @FXML private TableColumn<Livre, String> champPresentation;
    @FXML private TableColumn<Livre, Integer> champParution;
    @FXML private TableColumn<Livre, Integer> champColonne;
    @FXML private TableColumn<Livre, Integer> champRangee;


    @FXML private TextField inputTitre;
    @FXML private TextField inputNomAuteur;
    @FXML private TextField inputPrenomAuteur;
    @FXML private TextField inputPresentation;
    @FXML private TextField inputParution;
    @FXML private TextField inputColonne;
    @FXML private TextField inputRangée;
    @FXML private MenuItem btOuvrirBDD;

    @FXML
    /**
     Méthode appelée lorsqu'on clique sur le bouton "Nouveau Livre".
     Elle affiche une boîte de dialogue d'information pour dire qu'un livre a bien été rajouté.
     */
    void handleNouveauLivre(ActionEvent event) {
        Livre livre = new Livre();
        livre.setTitre(inputTitre.getText());
        livre.setNomAuteur(inputNomAuteur.getText());
        livre.setPrenomAuteur(inputPrenomAuteur.getText());
        livre.setPresentation(inputPresentation.getText());
        try {
            livre.setParution(Integer.parseInt(inputParution.getText()));
            livre.setColonne(Integer.parseInt(inputColonne.getText()));
            livre.setRangee(Integer.parseInt(inputRangée.getText()));

            livres.add(livre);

            tableView.setItems(livres);
        } catch (NumberFormatException e) {
            if (e.getMessage().contains(inputParution.getText())) {
                livre.wrongFieldAlert.setContentText("Le champ parution doit être renseigné et de type entier");
            } else if (e.getMessage().contains(inputColonne.getText())) {
                livre.wrongFieldAlert.setContentText("Le champ colonne doit être renseigné et de type entier");
            } else if (e.getMessage().contains(inputRangée.getText())) {
                livre.wrongFieldAlert.setContentText("Le champ rangée doit être renseigné et de type entier");
            }
            livre.wrongFieldAlert.showAndWait();
        }
    }

    @FXML
    /**
     Méthode appelée lorsqu'on clique sur le bouton "Ouvrir de la menu bar".
     Elle ouvre une boîte de dialogue de sélection de fichier.
     */
    void handleOuvrirMenuItemAction(ActionEvent event) throws XMLStreamException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an XML file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));

        File selectedFile = fileChooser.showOpenDialog(null);


        if (selectedFile != null){
            bibliotheque = new Bibliotheque(selectedFile.getPath());
            tableView.getItems().addAll(bibliotheque.livre);
            livres = FXCollections.observableList(bibliotheque.livre);
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

    /**
     * initialise les champs necessaire a l'IHM a la creation
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bibliotheque= new Bibliotheque();
        champTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        champNomAuteur.setCellValueFactory(new PropertyValueFactory<>("nomAuteur"));
        champPrenomAuteur.setCellValueFactory(new PropertyValueFactory<>("prenomAuteur"));
        champPresentation.setCellValueFactory(new PropertyValueFactory<>("presentation"));
        champParution.setCellValueFactory(new PropertyValueFactory<>("parution"));
        champColonne.setCellValueFactory(new PropertyValueFactory<>("colonne"));
        champRangee.setCellValueFactory(new PropertyValueFactory<>("rangee"));
        bibliotheque= new Bibliotheque();
        livres = FXCollections.observableList(bibliotheque.livre);
        tableView.setItems(livres);
        Register();
    }
    /**
     *permet sur le clique dans le tableau de reseigner les champs sur la droite de l'IHM
     */
    private void Register(){
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                inputTitre.setText(newValue.getTitre());
                inputNomAuteur.setText(newValue.getNomAuteur());
                inputPrenomAuteur.setText(newValue.getPrenomAuteur());
                inputPresentation.setText(newValue.getPresentation());
                inputParution.setText(Integer.toString(newValue.getParution()));
                inputColonne.setText(Integer.toString(newValue.getColonne()));
                inputRangée.setText(Integer.toString(newValue.getRangee()));
            }
        });
    }

    public void handleSupprimer(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Livre supprimé");
        alert.showAndWait();
    }

    public void DeleteField(){
                inputTitre.setText("");
                inputNomAuteur.setText("");
                inputPrenomAuteur.setText("");
                inputPresentation.setText("");
                inputParution.setText("");
                inputColonne.setText("");
                inputRangée.setText("");

    }

    @FXML
    public void deleteSelectedRow(ActionEvent event) {
        Livre selectedObject = tableView.getSelectionModel().getSelectedItem();
        if (selectedObject != null) {
            tableView.getItems().remove(selectedObject);
        }
    }

    /**
     * Permet de sauvegarder le fichier ouvert
     */
    @FXML
    public void handleSauvegarderMenuItemAction() {
        try {
            if(bibliotheque.path!=null &&!bibliotheque.path.isEmpty())
                bibliotheque.toXml(bibliotheque.path);
            else
                handleSauvegarderSousMenuItemAction();

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
    /**
     * Permet de sauvegarder sous
     */
    @FXML
    public void handleSauvegarderSousMenuItemAction() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save XML File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));

            File selectedFile = fileChooser.showSaveDialog(null);

            if (selectedFile != null) {
                bibliotheque.toXml(selectedFile.getPath());
            }
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ouvre la base de donnée
     */
    @FXML
    public void handleOpenBDD() {
        if(btOuvrirBDD.getText().equals("Connexion"))
            btOuvrirBDD.setText("Deconnexion");
        else
            btOuvrirBDD.setText("Connexion");
    }
}