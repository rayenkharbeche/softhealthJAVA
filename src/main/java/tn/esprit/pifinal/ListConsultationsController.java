/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.Consultation;
import tn.esprit.entities.User;
import tn.esprit.services.CategorieServices;
import tn.esprit.services.ConsultationServices;
import tn.esprit.services.UserService;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ListConsultationsController implements Initializable {

    @FXML
    private AnchorPane id_affichage;
    @FXML
    private JFXComboBox<String> id_patient;
    @FXML
    private JFXTextField id_recherche;
    @FXML
    private JFXCheckBox id_nbre;
    @FXML
    private JFXCheckBox id_date;
    @FXML
    private ScrollPane x;
    @FXML
    private VBox pnItems;
    
    public static Consultation consultation_a_modifier = new Consultation();
    private static ConsultationServices consultationServices = new ConsultationServices();
    private static UserService userService = new UserService();
    private static CategorieServices categorieServices = new CategorieServices();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
               for (User user : userService.readAllUserByRole("patient")) {
                id_patient.getItems().add(user.getUsername());
            }
            loadConsultationGrid();
        } catch (SQLException ex) {
            Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
   private void loadConsultationGrid() throws SQLException {
        List<Consultation> consultations = fetchConsultations();
        Node[] nodes = new Node[consultations.size()];

        AtomicInteger i = new AtomicInteger(0);
        consultations.forEach(consultation -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            try {
                try {
                    displayConsultationrDetails(node, consultation);
                } catch (IOException ex) {
                    Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (WriterException ex) {
                    Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            setupActions(node, consultation, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length >= 0) {
            pnItems.setStyle("-fx-background-color : #4B0082");
            pnItems.toFront();
        }
    }

    private List<Consultation> fetchConsultations() throws SQLException {
        try {
            return consultationServices.readAllConsultation();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private Node loadNewItemNode() {
        try {
            return FXMLLoader.load(getClass().getResource("/fxml/item_consultation.fxml"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void displayConsultationrDetails(Node node, Consultation consultation) throws SQLException, IOException, WriterException {
   
		 QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = consultation.getTitre();
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
       
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
     
        
        ImageView qrView = new ImageView();
       
        ImageView image_qr = (ImageView) node.lookup(".img_qr");
        image_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        Label item_user = (Label) node.lookup(".item_user");
        item_user.setText(userService.getUserById(consultation.getId_user()).getUsername());
        Label item_categorie = (Label) node.lookup(".item_categorie");
        item_categorie.setText(categorieServices.getCategorieType(consultation.getId_categorie()));
        Label item_titre = (Label) node.lookup(".item_titre");
        item_titre.setText(consultation.getTitre());

        // other properties
        // ...
    }

    private void setupActions(Node node, Consultation consultation, int index) {

        Button deleteButton = (Button) node.lookup(".item_action_supprimer");
        deleteButton.setOnMouseClicked(DeleteEventHandler(consultation, index));
        Button modifierButton = (Button) node.lookup(".item_action_modifier");

        modifierButton.setOnMouseClicked(UpdateEventHandler(consultation, index));
    }
private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
			throws IOException, WriterException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}

    private void setHoverStyleForNode(Node[] nodes, int i) {
        final int j = i;
        nodes[i].setOnMouseEntered(even -> {
            nodes[j].setStyle("-fx-background-color : #0A0E3F");
        });
        nodes[i].setOnMouseExited(even -> {
            nodes[j].setStyle("-fx-background-color : #02030A");
        });
    }

    private EventHandler<MouseEvent> DeleteEventHandler(Consultation consultation, int index) {
        return event -> {

            consultationServices.supprimerConsultation(consultation);

            pnItems.getChildren().remove(index);

            Notifications notificationBuilder = Notifications.create()
                    .title("consultation Supprimée")
                    .text("Vous avez supprimé une consultation")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);

            notificationBuilder.showError();
        };
    }

    public EventHandler<MouseEvent> UpdateEventHandler(Consultation consultation, int index) {
        return event -> {

            try {
                consultation_a_modifier = consultation;
                consultationServices.incrementerNombreVu(consultation.getId_categorie());
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/UpdateConsultation.fxml"));
                id_affichage.getChildren().clear();
                id_affichage.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(AllUsersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        };
    }

    private List<Consultation> fetchPatient(String patient) throws SQLException {
        try {
            return consultationServices.readAllConsultationByPatient(userService.getUserByUserName(patient));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void loadPatients() throws SQLException {
        List<Consultation> consultations = fetchPatient(id_patient.getSelectionModel().getSelectedItem());
        Node[] nodes = new Node[consultations.size()];

        AtomicInteger i = new AtomicInteger(0);
        consultations.forEach(consultation -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            try {
                try {
                    displayConsultationrDetails(node, consultation);
                } catch (IOException ex) {
                    Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (WriterException ex) {
                    Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            setupActions(node, consultation, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }

    private void loadNames() throws SQLException {
        List<Consultation> consultations = fetchConsultations();
        Node[] nodes = new Node[consultations.size()];

        AtomicInteger i = new AtomicInteger(0);
        consultations.forEach(consultation -> {
            int j = i.getAndIncrement();
            if (consultation.getTitre().contains(id_recherche.getCharacters())) {
                Node node = nodes[j] = loadNewItemNode();

                try {
                    displayConsultationrDetails(node, consultation);
                } catch (SQLException ex) {
                    Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (WriterException ex) {
                    Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
                }

                setupActions(node, consultation, j);

                setHoverStyleForNode(nodes, j);

                pnItems.getChildren().add(node);
            }
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }

    private List<Consultation> fetchNbreVu() throws SQLException {
        try {
            return consultationServices.TrierParNbreVu();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void loadNbreVu() throws SQLException {
        List<Consultation> consultations = fetchNbreVu();
        Node[] nodes = new Node[consultations.size()];

        AtomicInteger i = new AtomicInteger(0);
        consultations.forEach(consultation -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            try {
                displayConsultationrDetails(node, consultation);
            } catch (SQLException ex) {
                Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriterException ex) {
                Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            setupActions(node, consultation, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }

      private List<Consultation> fetchDate() throws SQLException {
        try {
            return consultationServices.TrierParDateCreation();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void loadDate() throws SQLException {
        List<Consultation> consultations = fetchDate();
        Node[] nodes = new Node[consultations.size()];

        AtomicInteger i = new AtomicInteger(0);
        consultations.forEach(consultation -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            try {
                displayConsultationrDetails(node, consultation);
            } catch (SQLException ex) {
                Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriterException ex) {
                Logger.getLogger(ListConsultationsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            setupActions(node, consultation, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }
    
    @FXML
    private void afficherbypatient(ActionEvent event) throws SQLException {
   pnItems.getChildren().clear();
        loadPatients();
    }

    @FXML
    private void rechercher(KeyEvent event) throws SQLException {
         pnItems.getChildren().clear();
        loadNames();
    }

    @FXML
    private void trier_par_nbre(ActionEvent event) throws SQLException {
         if (id_nbre.isSelected()) {
            id_date.setSelected(false);
            pnItems.getChildren().clear();
            loadDate();
 }
    }

    @FXML
    private void trier_par_date(ActionEvent event) throws SQLException {
        if (id_date.isSelected()) {
            id_nbre.setSelected(false);
            pnItems.getChildren().clear();
            loadDate();
 }
    }
    
}
