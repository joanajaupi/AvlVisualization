package avlVisualiser;
import java.util.ArrayList;

import avlimplementation.AVL;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Visualiser{

	private static ArrayList<Integer> nodes = new ArrayList<>();

	public void start() throws Exception {

		AVL<Integer> tree = new AVL<>();
		BorderPane pane = new BorderPane();
		avlPane view = new avlPane(tree);
		pane.setCenter(view);
		TextField textField = new TextField();
		textField.setPrefWidth(100);
		textField.setAlignment(Pos.BASELINE_RIGHT);
		Button insert = new Button("Insert");
		insert.setPrefWidth(70);
		insert.setStyle("-fx-background-color: #2b3e45; -fx-text-fill: #ffffff");
		Button delete = new Button("Delete");
		delete.setPrefWidth(70);
		delete.setStyle("-fx-background-color: #2b3e45; -fx-text-fill: #ffffff");
		addFunctionalities(textField, insert, delete, tree, view);
		Button close = new Button("Close");
		close.setPrefWidth(70);
		close.setStyle("-fx-background-color: #7a0f0f; -fx-text-fill: #ffffff");

		HBox hBox = new HBox(20);
		hBox.setPadding(new Insets(20, 50, 50, 50));
		hBox.setStyle("-fx-background-color: #79898f");
		Label lbl = new Label("Enter a value");
		lbl.setStyle("-fx-text-fill: #ffffff; -fx-text-weight: bold;");
		hBox.getChildren().addAll(lbl, textField, insert, delete, close);
		hBox.setAlignment(Pos.TOP_LEFT);
		pane.setTop(hBox);
		Scene scene = new Scene(pane, 600, 500);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AVL VISUALISATION");
		primaryStage.setScene(scene);
		primaryStage.show();
		close.setOnAction(e ->{
			primaryStage.close();
		});

	}



	public void addFunctionalities(TextField textField, Button insert, Button delete, AVL<Integer> tree, avlPane view) {
		insert.setOnAction(e -> {
			if (textField.getText().length() == 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered anything!", ButtonType.OK);
				alert.getDialogPane().setMinHeight(80);
				alert.show();
			} else {
				int key = Integer.parseInt(textField.getText());
				nodes.add(key);
				tree.insert(key);
				view.displayTree();

			}
		});

		delete.setOnAction(e -> {
			if (textField.getText().length() == 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered anything!", ButtonType.OK);
				alert.getDialogPane().setMinHeight(80);
				alert.show();
			} else {
				int key = Integer.parseInt(textField.getText());
				nodes.add(key);
				tree.delete(key);
				view.displayTree();

			}
		});

	}
}