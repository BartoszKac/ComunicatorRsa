package org.example.front.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.front.RSA.Key;
import org.example.front.RSA.KeyStorage;
import org.example.front.RSA.RsaImplementation;
import org.example.front.http.HttpSender;
import org.example.front.model.Person;

public class ChatUser {
    public static ChatUser chatUser = new ChatUser();
    public static ChatUser getChatUser() { return chatUser; }

    public void showUserInput(Stage stage) {
        String myName = Person.getPerson().getName();

        VBox root = new VBox(20);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f5f6fa;");

        Label welcomeLabel = new Label("Witaj, " + myName + "!");
        welcomeLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        VBox searchBox = new VBox(10);
        Label searchLabel = new Label("Rozpocznij nową rozmowę:");
        searchLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Wpisz nazwę użytkownika...");
        usernameField.setStyle("-fx-padding: 12; -fx-background-radius: 5; -fx-border-color: #dcdde1; -fx-border-radius: 5;");

        Button openChatButton = new Button("SZUKAJ I CZATUJ");
        openChatButton.setMaxWidth(Double.MAX_VALUE);
        openChatButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 12; -fx-background-radius: 5; -fx-cursor: hand;");

        searchBox.getChildren().addAll(searchLabel, usernameField, openChatButton);

        VBox historyBox = new VBox(10);
        VBox.setVgrow(historyBox, Priority.ALWAYS); // Lista zajmuje resztę miejsca

        Label historyLabel = new Label("Twoje kontakty:");
        historyLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-weight: bold;");

        ListView<String> contactsList = new ListView<>();
        contactsList.getItems().addAll(KeyStorage.loadContacts(myName));
        contactsList.setStyle("-fx-background-radius: 5; -fx-border-color: #dcdde1; -fx-control-inner-background: #ffffff;");
        VBox.setVgrow(contactsList, Priority.ALWAYS);

        Button logoutButton = new Button("Wyloguj");
        logoutButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #e74c3c; -fx-cursor: hand;");
        logoutButton.setOnAction(e -> MainMenu.getMenu().showMenu(stage));

        historyBox.getChildren().addAll(historyLabel, contactsList);

        openChatButton.setOnAction(e -> startChat(stage, usernameField.getText().trim()));

        contactsList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selected = contactsList.getSelectionModel().getSelectedItem();
                if (selected != null) startChat(stage, selected);
            }
        });

        root.getChildren().addAll(welcomeLabel, searchBox, historyBox, logoutButton);

        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.setTitle("SecureChat - Kontakty");
        stage.show();
    }

    private void startChat(Stage stage, String targetUser) {
        if (targetUser.isEmpty()) return;

        String rawKey = HttpSender.getPublicKey(targetUser);
        if (rawKey != null && !rawKey.isEmpty()) {
            try {
                KeyStorage.saveContact(Person.getPerson().getName(), targetUser);

                Key recipientKey = new Key(java.math.BigInteger.ZERO, java.math.BigInteger.ZERO).StringtoKey(rawKey);
                RsaImplementation myLoggedRsa = Person.getPerson().getRsa();

                Chat chat = Chat.getChat();
                chat.setRecipientData(targetUser, recipientKey, myLoggedRsa);
                chat.showChat(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Nie znaleziono użytkownika: " + targetUser);
            alert.showAndWait();
        }
    }
}