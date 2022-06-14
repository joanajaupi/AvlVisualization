package controller;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.AVL;
import model.AvlNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
    private Button howToUse;

    @FXML
    private TextField valueField;
    @FXML
    public Label label;

    private AVL<Integer> tree = new AVL<>();
    private ArrayList<Integer> nodes = new ArrayList<>();
    @FXML
    public Pane avlPane = new Pane();
    @FXML
    public Pane pane;
    private double radius = 17;
    private double vGap = 50;

    @FXML
    void aboutPressed(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("About.fxml"));
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
        stage.show();
    }


    @FXML
    void howToUsePressed(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("helpCenter.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("logInScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openGit(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/JonMukaj/AvlVisualization"));
    }

    @FXML
    void openLinkedin(ActionEvent event) throws Exception {
        final Node source = (Node) event.getSource();
        if (source.getId().matches("joana")) {
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/joana-jaupi-a88477219/"));
        } else if (source.getId().matches("jon"))
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/jon-mukaj/"));
        else if (source.getId().matches("fabio"))
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/fabiomarku/"));
        else if (source.getId().matches("kevin"))
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/kevin-tenolli-59357415a/"));
        else if (source.getId().matches("enest"))
            Desktop.getDesktop().browse(new URI("https://github.com/JonMukaj/AvlVisualization")); //dummy
        else if (source.getId().matches("kristi"))
            Desktop.getDesktop().browse(new URI("https://github.com/JonMukaj/AvlVisualization")); //dummy
    }

    @FXML
    void addButton(ActionEvent event) {
        label.setVisible(false);
        if (valueField.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered node you wish to insert!", ButtonType.OK);
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/Design.png")));
            alert.getDialogPane().setMinHeight(80);
            alert.setTitle("PROMPT");
            alert.setHeaderText("Insertion");
            alert.show();
        } else {
            int key = Integer.parseInt(valueField.getText());
            if (tree.search(tree.getRoot(), key) != null) {
                label.setText("No duplicate node allowed!");
                label.setTextFill(Color.RED);
                label.setVisible(true);
            } else {
                nodes.add(key);
                tree.insert(key);
                pane.getChildren().remove(avlPane);
                label.setText("Inserted!");
                label.setTextFill(Color.valueOf("#15C633"));
                label.setVisible(true);
                displayTree();
                pane.getChildren().add(avlPane);
            }
            valueField.setText("");
        }
    }

    @FXML
    void deleteButton(ActionEvent event) {
        label.setVisible(false);
        if (valueField.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered node you wish to delete!", ButtonType.OK);
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/Design.png")));
            alert.getDialogPane().setMinHeight(80);
            alert.setTitle("PROMPT");
            alert.setHeaderText("Deletion");
            alert.show();
        } else {
            int key = Integer.parseInt(valueField.getText());
            if (tree.search(tree.getRoot(), key) == null) {
                label.setText("Node " + key + " is not in the AVL!");
                label.setTextFill(Color.RED);
                label.setVisible(true);
            } else {
                nodes.add(key);
                tree.delete(key);
                pane.getChildren().remove(avlPane);
                label.setText("Deleted!");
                label.setTextFill(Color.valueOf("#15C633"));
                label.setVisible(true);
                displayTree();
                pane.getChildren().add(avlPane);
            }
            valueField.setText("");
        }
    }

    @FXML
    void searchButton(ActionEvent event) {
        label.setVisible(false);
        if (valueField.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered node you wish to search!", ButtonType.OK);
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/Design.png")));
            alert.getDialogPane().setMinHeight(80);
            alert.setTitle("PROMPT");
            alert.setHeaderText("Searching");
            alert.show();
        } else {
            int key = Integer.parseInt(valueField.getText());
            if (tree.search(tree.getRoot(), key) == null) {
                label.setText("Node " + key + " is not in the AVL!");
                label.setTextFill(Color.RED);
                label.setVisible(true);
            } else {
                pane.getChildren().remove(avlPane);
                displayTreeSearch(key);
                label.setText("Found!");
                label.setTextFill(Color.valueOf("#15C633"));
                label.setVisible(true);
                pane.getChildren().add(avlPane);
            }
            valueField.setText("");
        }
    }


    @FXML
    void heightButton(ActionEvent event) {
        label.setVisible(false);
        if (valueField.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered node you wish to get height!", ButtonType.OK);
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/Design.png")));
            alert.getDialogPane().setMinHeight(80);
            alert.setHeaderText("Height");
            alert.setTitle("PROMPT");
            alert.show();
        } else {
            int key = Integer.parseInt(valueField.getText());
            if (tree.search(tree.getRoot(), key) == null) {
                label.setText("Node " + key + " is not in the AVL!");
                label.setTextFill(Color.RED);
                label.setVisible(true);
            } else {
                pane.getChildren().remove(avlPane);
                displayTreeHeight(key);
                pane.getChildren().add(avlPane);
            }
            valueField.setText("");
        }
    }

    @FXML
    void depthButton(ActionEvent event) {
        label.setVisible(false);
        if (valueField.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered node you wish to get depth!", ButtonType.OK);
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/Design.png")));
            alert.getDialogPane().setMinHeight(80);
            alert.setTitle("PROMPT");
            alert.setHeaderText("Depth");
            alert.show();
        } else {
            int key = Integer.parseInt(valueField.getText());
            if (tree.search(tree.getRoot(), key) == null) {
                label.setText("Node " + key + " is not in the AVL!");
                label.setTextFill(Color.RED);
                label.setVisible(true);
            } else {
                pane.getChildren().remove(avlPane);
                displayTreeDepth(key);
                pane.getChildren().add(avlPane);
            }
            valueField.setText("");
        }
    }

    @FXML
    void backButton(ActionEvent event) throws IOException {
        label.setVisible(false);
        tree = null;
        nodes = null;
        root = FXMLLoader.load(getClass().getResource("logInScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void displayTree() {
        avlPane.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), avlPane.getWidth() / 2, vGap, avlPane.getWidth() / 4, Color.web("#0e264f"));
        }
    }

    private void displayTreeSearch(Integer target) {
        avlPane.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTreeSearch(tree.getRoot(), avlPane.getWidth() / 2, vGap, avlPane.getWidth() / 4, Color.web("#0e264f"), target);
        }
    }

    private void displayTreeHeight(Integer target) {
        avlPane.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTreeHeight(tree.getRoot(), avlPane.getWidth() / 2, vGap, avlPane.getWidth() / 4, Color.web("#0e264f"), target);
        }
    }

    private void displayTreeDepth(Integer target) {
        avlPane.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTreeDepth(tree.getRoot(), avlPane.getWidth() / 2, vGap, avlPane.getWidth() / 4, Color.web("#0e264f"), target);
        }
    }


    private void displayTree(AvlNode<Integer> root, double x, double y, double hGap, Color color) {
        if (root.getLeft() != null) {
            avlPane.getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2, color);
        }

        if (root.getRight() != null) {
            avlPane.getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2, color);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        Text t = new Text(x - 5, y + 4, root.value + "");
        t.setFill(Color.WHITE);
        avlPane.getChildren().addAll(circle, t);
    }


    private void displayTreeSearch(AvlNode<Integer> root, double x, double y, double hGap, Color color, Integer target) {
        if (root.getLeft() != null) {
            avlPane.getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTreeSearch(root.getLeft(), x - hGap, y + vGap, hGap / 2, color, target);
        }

        if (root.getRight() != null) {
            avlPane.getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTreeSearch(root.getRight(), x + hGap, y + vGap, hGap / 2, color, target);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        if (root.getValue().compareTo(target) == 0) {
            circle.setFill(Color.RED);
        }
        Text t = new Text(x - 5, y + 4, root.value + "");
        t.setFill(Color.WHITE);
        avlPane.getChildren().addAll(circle, t);
    }


    private void displayTreeHeight(AvlNode<Integer> root, double x, double y, double hGap, Color color, Integer target) {
        if (root.getLeft() != null) {
            avlPane.getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTreeHeight(root.getLeft(), x - hGap, y + vGap, hGap / 2, color, target);
        }

        if (root.getRight() != null) {
            avlPane.getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTreeHeight(root.getRight(), x + hGap, y + vGap, hGap / 2, color, target);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        if (root.getValue().compareTo(target) == 0) {
            circle.setFill(Color.GREEN);
            alertShow("Height of highlighted node is: ", Integer.toString(root.getHeight()));
        }
        Text t = new Text(x - 5, y + 4, root.value + "");
        t.setFill(Color.WHITE);
        avlPane.getChildren().addAll(circle, t);
    }

    private void displayTreeDepth(AvlNode<Integer> root, double x, double y, double hGap, Color color, Integer target) {
        if (root.getLeft() != null) {
            avlPane.getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTreeDepth(root.getLeft(), x - hGap, y + vGap, hGap / 2, color, target);
        }

        if (root.getRight() != null) {
            avlPane.getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTreeDepth(root.getRight(), x + hGap, y + vGap, hGap / 2, color, target);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        if (root.getValue().compareTo(target) == 0) {
            circle.setFill(Color.BLUE);
            alertShow("Depth of highlighted node is: ", Integer.toString(tree.findDepth(tree.getRoot(), target)));
        }
        Text t = new Text(x - 5, y + 4, root.value + "");
        t.setFill(Color.WHITE);
        avlPane.getChildren().addAll(circle, t);
    }

    private void alertShow(String type, String message) {
        Alert alert;
        if (message.equals(""))
            alert = new Alert(Alert.AlertType.INFORMATION, "Tree is Empty!", ButtonType.OK);
        else
            alert = new Alert(Alert.AlertType.INFORMATION, type + message, ButtonType.OK);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/Design.png")));
        alert.getDialogPane().setMinHeight(80);
        alert.setHeaderText("Tree Traversal");
        alert.setTitle("PROMPT");
        alert.show();
    }

    @FXML
    void inButtonPressed(ActionEvent event) throws IOException {
        label.setVisible(false);
        alertShow("Inorder Output: ", tree.inorderText());
    }

    @FXML
    void preButtonPressed(ActionEvent event) throws IOException {
        label.setVisible(false);
        alertShow("Preorder Output: ", tree.preorderText());
    }

    @FXML
    void postButtonPressed(ActionEvent event) throws IOException {
        label.setVisible(false);
        alertShow("Postorder Output: ", tree.postorderText());
    }

}


