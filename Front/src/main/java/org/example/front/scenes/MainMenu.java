package org.example.front.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

   public static MainMenu menu = new MainMenu();

    public static MainMenu getMenu() {
        return menu;
    }

    public void showMenu(Stage stage) {
        Button loginButton = new Button("Zaloguj się");
        Button registerButton = new Button("Zarejestruj się");

        String btnStyle = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;";
        loginButton.setStyle(btnStyle);
        registerButton.setStyle(btnStyle + "-fx-background-color: #2ecc71;"); // Zielony dla rejestracji

        VBox root = new VBox(15, loginButton, registerButton);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #2c3e50;");

        loginButton.setOnAction(e -> Login.getLogin().showLogin(stage));
        registerButton.setOnAction(e -> Register.getRegister().showRegister(stage));

        stage.setScene(new Scene(root, 350, 250));
        stage.setTitle("SecureChat v1.0");
        stage.show();
    }
}
