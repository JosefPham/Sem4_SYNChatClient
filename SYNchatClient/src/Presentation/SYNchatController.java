package Presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Group 9
 */
public class SYNchatController implements Initializable {

    private Thread t = null;
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
    @FXML
    private JFXTextArea txtArea_rightChat;
    @FXML
    private JFXTextArea txtArea_leftChat;
    @FXML
    private AnchorPane AnchorPane_List;
    @FXML
    private MediaView mv_background;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private AnchorPane pane_Welcome;
    boolean b = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = new File("src/Assets/backgroundAnimation.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv_background.setMediaPlayer(mp);
        mp.setCycleCount(mp.INDEFINITE);
        mp.setAutoPlay(true);
    }

    @FXML
    public void startPublicChat(ActionEvent event) {
        pane_Welcome.toBack();
        mp.stop();
        PresentationFacade.Ibus.publicThreads();
        btn_publicChat.setStyle("-fx-background-color: BLUE");
        btn_privatChat.setStyle("-fx-background-color: TRANSPARENT");

        this.t = startrun();
    }

    private synchronized Thread startrun() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String s = "";
                while (true) {
                    System.out.println("");
                    System.out.println(" .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------.  .----------------. ");
                    System.out.println("| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |");
                    System.out.println("| |    _______   | || |  ____  ____  | || | ____  _____  | || |     ______   | || |  ____  ____  | || |      __      | || |  _________   | |");
                    System.out.println("| |   /  ___  |  | || | |_  _||_  _| | || ||_   \\|_   _| | || |   .' ___  |  | || | |_   ||   _| | || |     /  \\     | || | |  _   _  |  | |");
                    System.out.println("| |  |  (__ \\_|  | || |   \\ \\  / /   | || |  |   \\ | |   | || |  / .'   \\_|  | || |   | |__| |   | || |    / /\\ \\    | || | |_/ | | \\_|  | |");
                    System.out.println("| |   '.___`-.   | || |    \\ \\/ /    | || |  | |\\ \\| |   | || |  | |         | || |   |  __  |   | || |   / ____ \\   | || |     | |      | |");
                    System.out.println("| |  |`\\____) |  | || |    _|  |_    | || | _| |_\\   |_  | || |  \\ `.___.'\\  | || |  _| |  | |_  | || | _/ /    \\ \\_ | || |    _| |_     | |");
                    System.out.println("| |  |_______.'  | || |   |______|   | || ||_____|\\____| | || |   `._____.'  | || | |____||____| | || ||____|  |____|| || |   |_____|    | |");
                    System.out.println("| |              | || |              | || |              | || |              | || |              | || |              | || |              | |");
                    System.out.println("| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |");
                    System.out.println(" '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'");
                    System.out.println("");
                    String tmp = PresentationFacade.getInstance().getS();
                    if (!tmp.equals(s)) {
                        s = tmp;
                        receivePublicMsg(s);
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        t.start();
        return t;
    }

    @FXML
    private void sendMsg(ActionEvent event) {
        if (!txtArea_YourChat.getText().trim().isEmpty()) {
            //PresentationFacade.getInstance().sendPublicMsg(txtArea_YourChat.getText());
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
        mp.stop();
        pane_Welcome.toBack();
        PresentationFacade.Ibus.privateThreads();
        btn_privatChat.setStyle("-fx-background-color: BLUE");
        btn_publicChat.setStyle("-fx-background-color: TRANSPARENT");
    }

    @FXML
    private void popOpHandler(MouseEvent event) {
        if(b) {
            Popup_pane.toFront();
            b = false;
        } else {
            Popup_pane.toBack();
            b = true;
        }
    }

    public void receivePublicMsg(String s) {
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
