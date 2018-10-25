/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Connection.ConnectionFacade;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sigurd E. Espersen
 */
public class SYNchatController implements Initializable {

    private static SYNchatController instance = null;

    public static SYNchatController getInstance() {
        if (instance == null) {
            instance = new SYNchatController();
        }
        return instance;
    }

    @FXML
    private JFXButton btn_publicChat;
    @FXML
    private JFXButton btn_send;
    @FXML
    private ImageView pic_profile;
    @FXML
    private Label label_userInfo;
    @FXML
    private JFXButton btn_privatChat;
    @FXML
    private AnchorPane Popup_pane;
    @FXML
    private JFXTextField txtArea_YourChat;
    private JFXTextArea txtArea_rightChat;
    private JFXTextArea txtArea_leftChat;
    @FXML
    private JFXTextArea txtArea_totalChat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Popup_pane.setDisable(true);
    }

    @FXML
    public void startPublicChat(ActionEvent event) {
        PresentationFacade.Ibus.publicThreads();
    }

    @FXML
    private void sendMsg(ActionEvent event) {
        if (!txtArea_YourChat.getText().trim().isEmpty()) {
            PresentationFacade.getInstance().sendPublicMsg(txtArea_YourChat.getText());
            String nameMsg = "";
            String yourMsg = "";
            String dateMsg = "";
            nameMsg = "Default user:\n";
            txtArea_rightChat.appendText(nameMsg);
            yourMsg = txtArea_YourChat.getText() + "\n";
            txtArea_rightChat.appendText(yourMsg);
            dateMsg = new SimpleDateFormat("HH.mm").format(new Date()) + "\n";
            txtArea_rightChat.appendText(dateMsg);

            txtArea_leftChat.appendText("\n\n");

            txtArea_YourChat.clear();
        }
    }

    @FXML
    private void startPrivatChat(ActionEvent event) {
        PresentationFacade.Ibus.privateThreads();
    }

    @FXML
    private void popOpHandler(MouseEvent event) {
        if (Popup_pane.isDisabled()) {
            Popup_pane.setDisable(false);
            Popup_pane.toFront();
        } else {
            Popup_pane.setDisable(true);
            Popup_pane.toBack();
        }
    }

    public void recievePublicMsg(String s) {
        System.out.println("En eller anden faggots besked: " + s);
        String nameMsg = "";
        String yourMsg = "";
        String dateMsg = "";
        nameMsg = "Default user:\n";
        txtArea_leftChat.appendText(nameMsg);
        yourMsg = s + "\n";
        txtArea_leftChat.appendText(yourMsg);
        dateMsg = new SimpleDateFormat("HH.mm").format(new Date()) + "\n";
        txtArea_leftChat.appendText(dateMsg);
        txtArea_rightChat.appendText("\n\n");
    }

}
