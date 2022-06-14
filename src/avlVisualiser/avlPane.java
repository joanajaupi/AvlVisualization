package avlVisualiser;

import avlimplementation.AVL;
import avlimplementation.AvlNode;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class avlPane extends Pane{
    private AVL<Integer> tree;
    private double radius = 15;
    private double vGap = 50;

    protected avlPane(){ }

    public avlPane(AVL<Integer> tree){
        this.tree = tree;
        Label lbl = new Label("Tree is empty");
        lbl.setStyle("-fx-text-fill: #ffffff; ");
        this.setStyle("-fx-background-color: #5a76a6");

    }


     public void displayTree(){
        this.getChildren().clear();
        if(tree.getroot() != null){
            displayTree(tree.getroot(), getWidth() / 2, vGap, getWidth() / 4, Color.web("#0e264f"));
        }
    }

    protected void displayTree(AvlNode<Integer> root, double x, double y, double hGap, Color color){
        if(root.getLeft() != null){
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2,color);
        }

        if (root.getRight() != null){
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2, color);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        //circle.setStroke(Color.BLACK);
        Text t = new Text(x - 4, y + 4, root.value + "");
        t.setFill(Color.WHITE);
        getChildren().addAll(circle, t);
    }

}
