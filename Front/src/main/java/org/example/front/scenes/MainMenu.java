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
        // Tworzymy przyciski do ekranów
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Login login = Login.getLogin();
        Register register = Register.getRegister();

        // Akcje przycisków
        loginButton.setOnAction(e -> {

            login.showLogin(stage); // zakładamy, że Login ma metodę showLogin(Stage)
        });

        registerButton.setOnAction(e -> {

            register.showRegister(stage); // zakładamy, że Register ma metodę showRegister(Stage)
        });

        // Układ pionowy
        VBox root = new VBox(10, loginButton, registerButton);
        root.setPadding(new Insets(20));

        // Tworzymy scenę
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }
}
