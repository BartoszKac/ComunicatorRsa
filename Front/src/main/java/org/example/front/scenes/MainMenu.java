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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
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
<<<<<<< HEAD
=======
=======
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
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
        stage.show();
    }
}
