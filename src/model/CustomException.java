package model;

import application.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CustomException extends Exception{
    public CustomException(String message) {
        super(message);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/Design.png")));
        alert.getDialogPane().setMinHeight(80);
        alert.setTitle("ALERT");
        alert.setHeaderText("Wrong format");
        alert.show();
    }
}
