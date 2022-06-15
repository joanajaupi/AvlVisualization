If you are using java1.8 version, the jar should run correctly.

If you do not have jre 1.8 in your system, you can run the jar by using
java --module-path "Path to your javafx lib folder" --add-modules javafx.controls,javafx.fxml -jar  avl.jar

For example
java --module-path "C:\Users\jonim\OneDrive\Desktop\jar\javafx-sdk-18.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar  avl.jar
