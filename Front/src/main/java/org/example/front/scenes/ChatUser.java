package org.example.front.scenes;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.front.scenes.Chat;

public class ChatUser {

    public static ChatUser chatUser = new ChatUser();

    public static ChatUser getChatUser() {
        return chatUser;
    }

    private Stage stage;

    public void showUserInput(Stage stage) {
        this.stage = stage;

        Label label = new Label("Wpisz nazwę użytkownika:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("np. Jan");

        Button openChatButton = new Button("Otwórz Chat");

        Button searchUser = new Button("Szukaj użytkownika");

        openChatButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            if (!username.isEmpty()) {
               // checkUserExists(username);
            }
        });

        VBox root = new VBox(10, label, usernameField, searchUser,openChatButton);
        root.setPadding(new Insets(20));

        stage.setScene(new Scene(root, 300, 150));
        stage.setTitle("Wybierz użytkownika");
        stage.show();
    }
}
