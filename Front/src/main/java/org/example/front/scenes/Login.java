package org.example.front.scenes;

import javafx.geometry.Insets;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
<<<<<<< HEAD
=======
=======
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.front.RSA.KeyStorage;
import org.example.front.RSA.RsaImplementation;
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
import org.example.front.http.HttpSender;
import org.example.front.model.Person;

public class Login {

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
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
<<<<<<< HEAD
=======
=======
    private static Login login = new Login();
    private TextField usernameField;
    private PasswordField passwordField;
    private Stage sta;

    private ChatUser chatUser = ChatUser.getChatUser();

    public static Login getLogin() {
        return login;
    }

    public void showLogin(Stage primaryStage) {
        this.sta = primaryStage;

        VBox mainRoot = new VBox(20);
        mainRoot.setAlignment(Pos.CENTER);
        mainRoot.setPadding(new Insets(40));
        mainRoot.setStyle("-fx-background-color: #2c3e50;"); // Ciemne, eleganckie tło

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

        Label title = new Label("LOGOWANIE");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        grid.add(title, 0, 0, 2, 1);
        GridPane.setMargin(title, new Insets(0, 0, 10, 0));

        String inputStyle = "-fx-padding: 10; -fx-background-color: #f1f2f6; -fx-border-color: #dfe6e9; -fx-border-radius: 5; -fx-background-radius: 5;";

        usernameField = new TextField();
        usernameField.setPromptText("Nazwa użytkownika");
        usernameField.setStyle(inputStyle);

        passwordField = new PasswordField();
        passwordField.setPromptText("Hasło");
        passwordField.setStyle(inputStyle);

        Button loginButton = new Button("ZALOGUJ");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-padding: 12; -fx-background-radius: 5; -fx-cursor: hand;");

        loginButton.setOnAction(e -> handleLogin());

        Button backButton = new Button("← Wróć");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #7f8c8d; -fx-cursor: hand;");
        backButton.setOnAction(e -> MainMenu.getMenu().showMenu(primaryStage));

        grid.add(new Label("Użytkownik:"), 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(new Label("Hasło:"), 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(loginButton, 0, 3, 2, 1);
        grid.add(backButton, 0, 4, 2, 1);

        mainRoot.getChildren().add(grid);

        Scene scene = new Scene(mainRoot, 450, 400);
        primaryStage.setTitle("SecureChat - Logowanie");
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleLogin() {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
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
<<<<<<< HEAD
=======
=======
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Pola nie mogą być puste!");
            return;
        }

        RsaImplementation rsa = new RsaImplementation();
        KeyStorage.KeyPair saved = KeyStorage.loadKeys(username);

        if (saved != null) {
            System.out.println("Wczytano klucze z dysku dla: " + username);
            rsa.setKeys(saved.n, saved.e, saved.d);
        } else {
            System.out.println("Brak kluczy na dysku. Generuję nowe...");
            rsa.run();
            KeyStorage.saveKeys(username, rsa.getPublicKey().getX1(), rsa.getPublicKey().getX2(), rsa.getPrivateKey().getX2());
        }

        Person person = new Person(username, password, rsa.getPublicKey().toString());
        person.setRsa(rsa);

        if (HttpSender.PostLogin(person)) {
            Person.setPerson(person);
            System.out.println("Logowanie udane!");
            chatUser.showUserInput(sta);
        } else {
            System.err.println("Błąd logowania: Nieprawidłowe dane lub problem z serwerem.");
        }
    }
}
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
