package org.example.front.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.front.RSA.KeyStorage;
import org.example.front.RSA.RsaImplementation;
import org.example.front.http.HttpSender;
import org.example.front.model.Person;

public class Register {

    static Register register = new Register();
    ChatUser chatUser = ChatUser.getChatUser();

    public static Register getRegister() {
        return register;
    }

    public void showRegister(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #2c3e50;"); // Ciemne tło jak w MainMenu

        Label titleLabel = new Label("STWÓRZ KONTO");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-padding: 0 0 20 0;");

        TextField nameField = new TextField();
        nameField.setPromptText("Nazwa użytkownika");

        PasswordField passwordField = new PasswordField(); // Zmieniono na PasswordField dla bezpieczeństwa
        passwordField.setPromptText("Hasło");

        String inputStyle = "-fx-background-color: #34495e; -fx-text-fill: white; -fx-prompt-text-fill: #95a5a6; " +
                "-fx-padding: 12; -fx-background-radius: 5; -fx-border-color: #7f8c8d; -fx-border-radius: 5;";
        nameField.setStyle(inputStyle);
        passwordField.setStyle(inputStyle);

        Button registerButton = new Button("ZAREJESTRUJ SIĘ");
        registerButton.setMaxWidth(Double.MAX_VALUE);
        registerButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-padding: 12; -fx-background-radius: 5; -fx-cursor: hand;");

        Button backButton = new Button("Anuluj");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #bdc3c7; -fx-cursor: hand;");
        backButton.setOnAction(e -> MainMenu.getMenu().showMenu(stage));

        registerButton.setOnAction(e -> {
            String name = nameField.getText();
            String password = passwordField.getText();

            if (name.isEmpty() || password.isEmpty()) {
                titleLabel.setText("Wypełnij dane!");
                titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #e74c3c;");
                return;
            }

            RsaImplementation rsa = new RsaImplementation();
            rsa.run();

            KeyStorage.saveKeys(name,
                    rsa.getPublicKey().getX1(),
                    rsa.getPublicKey().getX2(),
                    rsa.getPrivateKey().getX2()
            );

            Person person = new Person(name, password, rsa.getPublicKey().toString());
            person.setRsa(rsa);

            if(HttpSender.PostRegister(person)){
                System.out.println("Registration successful & Keys saved to disk.");
                Person.setPerson(person);
                chatUser.showUserInput(stage);
            }
        });

        root.getChildren().addAll(titleLabel, nameField, passwordField, registerButton, backButton);

        Scene scene = new Scene(root, 350, 450);
        stage.setScene(scene);
        stage.setTitle("Rejestracja - SecureChat");
        stage.show();
    }
}