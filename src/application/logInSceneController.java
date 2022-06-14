package application;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import avlVisualiser.Visualiser;
import avlVisualiser.avlPane;
import avlimplementation.AVL;
import avlimplementation.AvlNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
public class logInSceneController {
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	//Main menu buttons
	 @FXML
	    private Button about;

	    @FXML
	    private Button getStarted;

	    @FXML
	    private Button howToUse;

	//avlScene buttons
		@FXML
		private TextField valueField;
		@FXML
		private Button addButton;
		@FXML
		private Button deleteButton;
	@FXML
	private Button searchButton;
	@FXML
	private Button heightButton;
	@FXML
	private Button depthButton;
	@FXML
	private Button inButton;
	@FXML
	private Button preButton;
	@FXML
	private Button postButton;
	@FXML
	private Button backButton;


//Avltree
	private AVL<Integer> tree = new AVL<>();
	//private avlPane view = new avlPane(tree);
	private static ArrayList<Integer> nodes = new ArrayList<>();
	@FXML
	Pane avlPane = new Pane();
	@FXML
	Pane pane;
	private double radius = 15;
	private double vGap = 50;
    @FXML
    void aboutPressed(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
    	  stage = (Stage) about.getScene().getWindow();
    	  scene = new Scene(root);
    	  stage.setScene(scene);
    	  stage.show();
    }

    @FXML
    void getStartedPressed(ActionEvent event) throws Exception {
		root = FXMLLoader.load(getClass().getResource("avlScene.fxml"));
   	  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   	  scene = new Scene(root);
   	  stage.setScene(scene);
		 //new Visualiser().start();

   	  stage.show();
    }

    @FXML
    void howToUsePressed(ActionEvent event) throws IOException {
    	/* root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
   	  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   	  scene = new Scene(root);
   	  stage.setScene(scene);
   	  stage.show();*/

    }

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {

    	root = FXMLLoader.load(getClass().getResource("logInScene.fxml"));
     	  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     	  scene = new Scene(root);
     	  stage.setScene(scene);
     	  stage.show();
    }

	@FXML
	void addButton(ActionEvent event) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		if (valueField.getText().length() == 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered node you wish to insert!", ButtonType.OK);
			alert.getDialogPane().setMinHeight(80);
			alert.show();
		} else {
			int key = Integer.parseInt(valueField.getText());
			nodes.add(key);
			tree.insert(key);

			pane.getChildren().remove(avlPane);
			displayTree();
			pane.getChildren().add(avlPane);

		}
	}

	@FXML
	void deleteButton(ActionEvent event) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		if (valueField.getText().length() == 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered node you wish to delete!", ButtonType.OK);
			alert.getDialogPane().setMinHeight(80);
			alert.show();
		} else {
			int key = Integer.parseInt(valueField.getText());
			nodes.add(key);
			tree.delete(key);

			pane.getChildren().remove(avlPane);
			displayTree();
			pane.getChildren().add(avlPane);

		}
	}


	@FXML
	void backButton(ActionEvent event) throws IOException {
		tree = null;
		nodes = null;
		root = FXMLLoader.load(getClass().getResource("logInScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	public void displayTree(){
		avlPane.getChildren().clear();
		if(tree.getroot() != null){
			displayTree(tree.getroot(), avlPane.getWidth() / 2, vGap, avlPane.getWidth() / 4, Color.web("#0e264f"));
		}
	}

	@FXML
	protected void displayTree(AvlNode<Integer> root, double x, double y, double hGap, Color color){
		if(root.getLeft() != null){
			avlPane.getChildren().add(new Line(x - hGap, y + vGap, x, y));
			displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2,color);
		}

		if (root.getRight() != null){
			avlPane.getChildren().add(new Line(x + hGap, y + vGap, x, y));
			displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2, color);
		}

		Circle circle = new Circle(x, y, radius);
		circle.setFill(color);
		//circle.setStroke(Color.BLACK);
		Text t = new Text(x - 4, y + 4, root.value + "");
		t.setFill(Color.WHITE);
		avlPane.getChildren().addAll(circle, t);
	}




}


