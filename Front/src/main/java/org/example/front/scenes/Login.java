package org.example.front.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.front.http.HttpSender;
import org.example.front.model.Person;

public class Login {

    static Login login = new Login();
    private TextField usernameField;
    private PasswordField passwordField;

    ChatUser chatUser = ChatUser.getChatUser();

    public static Login getLogin() {
        return  login;
    }
    private Stage sta;

    public void showLogin(Stage primaryStage) {
        this.sta = primaryStage;
        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        // Username
        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);

        // Password
        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin());
        grid.add(loginButton, 1, 2);

        // Scene
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Tutaj możesz wywołać np. HttpSender.sendGetUsername(username)
        System.out.println("Trying to login with: " + username + " / " + password);
        Person person = new Person(username, password, "");
        HttpSender.PostLogin(person);
        if (HttpSender.PostLogin(person)) {
            Person.setPerson(person);
            System.out.println("Login OK");
            chatUser.showUserInput(sta);
        } else {
            System.out.println("Username or password empty!");
        }
    }
}
