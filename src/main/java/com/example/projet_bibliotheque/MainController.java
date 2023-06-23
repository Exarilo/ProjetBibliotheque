package com.example.projet_bibliotheque;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.sql.*;
import java.util.ResourceBundle;

/** Controller principal de l'application. */
public class MainController implements Initializable {
    ObservableList<Livre> livres;
    BDD bdd ;
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
    @FXML private TextField inputRangee;
    @FXML private MenuItem btOuvrirBDD;
    @FXML private Label labelConnection;

    @FXML private Button deleteSQL;

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
            livre.setRangee(Integer.parseInt(inputRangee.getText()));

            livres.add(livre);

            try {
                if (this.bdd == null)
                    this.bdd = new BDD();
                if (!this.bdd.isConnected)
                    this.bdd.CreateConnection();
                insertLivre(livre,bdd.connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            tableView.setItems(livres);
        } catch (NumberFormatException e) {
            if (e.getMessage().contains(inputParution.getText())) {
                livre.wrongFieldAlert.setContentText("Le champ parution doit être renseigné et de type entier");
            } else if (e.getMessage().contains(inputColonne.getText())) {
                livre.wrongFieldAlert.setContentText("Le champ colonne doit être renseigné et de type entier");
            } else if (e.getMessage().contains(inputRangee.getText())) {
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
            tableView.getItems().addAll(bibliotheque.livres);
            livres = FXCollections.observableList(bibliotheque.livres);
            tableView.setItems(livres);
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
        livres = FXCollections.observableList(bibliotheque.livres);
        tableView.setItems(livres);
        Register();
//       deleteSQL.setOnAction(deleteFromSQL());
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
                inputRangee.setText(Integer.toString(newValue.getRangee()));
            }
        });
    }

    /**
     * Permet reinitialiser les champs disponible sur la droite de l'ecran
     */
    public void DeleteField(){
       inputTitre.setText("");
       inputNomAuteur.setText("");
       inputPrenomAuteur.setText("");
       inputPresentation.setText("");
       inputParution.setText("");
       inputColonne.setText("");
        inputRangee.setText("");
    }

    /**
     * Permet de supprimer les lignes dans le tableau / également en BDD en mode connecté
     */
    @FXML
    public void deleteSelectedRow(ActionEvent event) throws SQLException {
        if (this.bdd == null)
            this.bdd = new BDD();
        if (!this.bdd.isConnected)
            this.bdd.CreateConnection();
        Livre selectedObject = tableView.getSelectionModel().getSelectedItem();

        if (selectedObject != null) {
            tableView.getItems().remove(selectedObject);
            String query = "DELETE FROM livre WHERE titre = ?";
            PreparedStatement statement = bdd.connection.prepareStatement(query);
            try {

                statement.setString(1, selectedObject.getTitre());
                statement.executeUpdate();
            }
            catch (Exception e){}
            finally {
                statement.close();
            }
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
     * Permet de se connecter ou de se deconnecter de la base de donnée
     */
    @FXML
    public void handleOpenBDD() {
        if(btOuvrirBDD.getText().equals("Connexion")) {
            labelConnection.setText("Vous êtes actuellement connecté");
            btOuvrirBDD.setText("Deconnexion");
            if (this.bdd == null)
                this.bdd = new BDD();
            if (!this.bdd.isConnected)
                this.bdd.CreateConnection();
            bibliotheque.livres.clear();
            livres.clear();
            getLivresFromDatabase();
        }
        else {
            labelConnection.setText("Vous êtes actuellement deconnecté");
            btOuvrirBDD.setText("Connexion");
            if (this.bdd != null)
                this.bdd.closeConnection();
            bibliotheque.livres.clear();
            livres.clear();
        }
    }

    /**
     * Permet de sauvegarder les livres de la bibliotheque en Docx
     */
    @FXML
    public void handleSauvegarderWordMenuItemAction(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Docx File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Docx files", "*.docx"));

            File selectedFile = fileChooser.showSaveDialog(null);

            if (selectedFile != null) {
                bibliotheque.toDocx(selectedFile.getPath());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Permet de sauvegarder les livres de la bibliotheque en PDF
     */
    @FXML
    public void handleSauvegarderPDFMenuItemAction(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));

            File selectedFile = fileChooser.showSaveDialog(null);

            if (selectedFile != null) {
                bibliotheque.toPDF(selectedFile.getPath());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ajoute les informations du livre dans la BDD
     */
    public static int insertLivre(Livre livre, Connection connection) throws SQLException {
        String query = "INSERT INTO livre (titre, nomAuteur, prenomAuteur, presentation, parution, colonne, rangee) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, livre.getTitre());
        statement.setString(2, livre.getNomAuteur());
        statement.setString(3, livre.getPrenomAuteur());
        statement.setString(4, livre.getPresentation());
        statement.setInt(5, livre.getParution());
        statement.setInt(6, livre.getColonne());
        statement.setInt(7, livre.getRangee());
        int rowsAffected = statement.executeUpdate();
        int livreId = -1;
        if (rowsAffected > 0) {
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                livreId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        }
        statement.close();
        return livreId;
    }

    /**
     * *Recupere les livres disponibles dans la BDD
     */
    public void getLivresFromDatabase() {
        if (this.bdd == null)
            this.bdd = new BDD();
        if (!this.bdd.isConnected)
            this.bdd.CreateConnection();

        try {
            Statement statement = this.bdd.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM livre");

            while (resultSet.next()) {
                Livre livre = new Livre();
                String titre = resultSet.getString("titre");
                String nomAuteur = resultSet.getString("nomAuteur");
                String prenomAuteur = resultSet.getString("prenomAuteur");
                String presentation = resultSet.getString("presentation");
                int parution = resultSet.getInt("parution");
                int colonne = resultSet.getInt("colonne");
                int rangee = resultSet.getInt("rangee");

                livre.setTitre(titre);
                livre.setNomAuteur(nomAuteur);
                livre.setPrenomAuteur(prenomAuteur);
                livre.setPresentation(presentation);
                livre.setParution(parution);
                livre.setColonne(colonne);
                livre.setRangee(rangee);

                livres.add(livre);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}