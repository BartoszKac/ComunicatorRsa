package org.example.front.scenes;

import javafx.application.Platform;
import javafx.geometry.Insets;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.front.RSA.RsaImplementation;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;

public class Chat {
    static Chat chat = new Chat();
    private WebSocketClient client;
    private TextArea chatArea;
    private TextField nameField, messageField;
    private RsaImplementation rsaImplementation = new RsaImplementation();
    private RsaImplementation anotherUser = new RsaImplementation();

    private static final String WS_URL = "ws://localhost:8082/chat/websocket";


    public  static Chat getChat() {
        return chat;
    }

    public void showChat(Stage stage){
        anotherUser.run();
        rsaImplementation.run();
        stage.setTitle("JavaFX Chat (WebSocket STOMP)");

        chatArea = new TextArea();
        chatArea.setEditable(false);

        nameField = new TextField();
        nameField.setPromptText("Twoje imię");

        messageField = new TextField();
        messageField.setPromptText("Wiadomość");

        Button sendButton = new Button("Wyślij");
        sendButton.setOnAction(e -> sendMessage());

        HBox inputBox = new HBox(8, nameField, messageField, sendButton);
        inputBox.setPadding(new Insets(10));

        VBox root = new VBox(8, chatArea, inputBox);
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root, 600, 400));
        stage.show();

        connectWebSocket();

    }

    private void connectWebSocket() {
        try {
            client = new WebSocketClient(new URI(WS_URL)) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    Platform.runLater(() -> chatArea.appendText("[Połączono z serwerem]\n"));
                    // Wysłanie nagłówków STOMP CONNECT
                    send("CONNECT\naccept-version:1.2\n\n\0");
                    // Subskrypcja kanału
                    send("SUBSCRIBE\nid:sub-0\ndestination:/topic/messages\n\n\0");
<<<<<<< HEAD
=======
=======
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.front.RSA.Key;
import org.example.front.RSA.KeyStorage;
import org.example.front.RSA.RsaImplementation;
import org.example.front.http.HttpSender;
import org.example.front.model.Person;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import java.net.URI;
import java.util.List;

public class Chat {
    private static Chat chat = new Chat();
    private WebSocketClient client;
    private TextArea chatArea;
    private TextField messageField;
    private String recipientName;
    private Key recipientPublicKey;
    private RsaImplementation myRsa;

    private static final String WS_URL = "ws://localhost:8082/chat/websocket";

    public static Chat getChat() { return chat; }

    public void setRecipientData(String name, Key key, RsaImplementation loggedInUserRsa) {
        this.recipientName = name;
        this.recipientPublicKey = key;
        this.myRsa = loggedInUserRsa;
    }

    public void showChat(Stage stage) {
        VBox root = new VBox(0);
        root.setStyle("-fx-background-color: #ffffff;");

        HBox header = new HBox(15);
        header.setPadding(new Insets(10, 15, 10, 15));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #2c3e50; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        Button backButton = new Button("←");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18px; -fx-cursor: hand; -fx-font-weight: bold;");
        backButton.setOnAction(e -> {
            if (client != null) client.close();
            ChatUser.getChatUser().showUserInput(stage);
        });

        Label nameLabel = new Label(recipientName);
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        header.getChildren().addAll(backButton, nameLabel);

        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        chatArea.setStyle("-fx-background-color: #f5f6fa; -fx-control-inner-background: #f5f6fa; -fx-background-insets: 0; -fx-padding: 10; -fx-font-family: 'Segoe UI', sans-serif; -fx-font-size: 14px;");
        VBox.setVgrow(chatArea, Priority.ALWAYS);

        HBox footer = new HBox(10);
        footer.setPadding(new Insets(15));
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("-fx-background-color: white; -fx-border-color: #dcdde1; -fx-border-width: 1 0 0 0;");

        messageField = new TextField();
        messageField.setPromptText("Napisz wiadomość...");
        messageField.setStyle("-fx-background-color: #f1f2f6; -fx-background-radius: 20; -fx-padding: 10 15; -fx-border-radius: 20; -fx-border-color: #dfe6e9;");
        HBox.setHgrow(messageField, Priority.ALWAYS);

        Button sendButton = new Button("WYŚLIJ");
        sendButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 10 20; -fx-cursor: hand;");

        footer.getChildren().addAll(messageField, sendButton);

        sendButton.setOnAction(e -> sendMessage());
        messageField.setOnAction(e -> sendMessage());

        root.getChildren().addAll(header, chatArea, footer);

        stage.setTitle("Rozmowa z: " + recipientName);
        stage.setScene(new Scene(root, 600, 500));

        connectWebSocket();
        loadHistory();

        stage.show();
    }

    private void connectWebSocket() {
        if (client != null) client.close();

        try {
            client = new WebSocketClient(new URI(WS_URL)) {
                @Override
                public void onOpen(ServerHandshake h) {
                    System.out.println("[WS] Połączono");
                    send("CONNECT\naccept-version:1.2\n\n\0");
                    send("SUBSCRIBE\ndestination:/topic/messages\nid:sub-0\n\n\0");
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
                }

                @Override
                public void onMessage(String message) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
                    // Parsowanie prostych ramek STOMP MESSAGE
                    if (message.startsWith("MESSAGE")) {
                        String body = message.substring(message.indexOf("\n\n") + 2, message.lastIndexOf('\0'));
                        JSONObject json = new JSONObject(body);
                        String from = json.optString("from");
                        String text = json.optString("text");

                        Platform.runLater(() -> chatArea.appendText(from + ": " + rsaImplementation.DescriptionMessege(text) + "\n"));
                    } else if (message.contains("CONNECTED")) {
                        Platform.runLater(() -> chatArea.appendText("[STOMP Connected]\n"));
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Platform.runLater(() -> chatArea.appendText("[Rozłączono: " + reason + "]\n"));
                }

                @Override
                public void onError(Exception ex) {
                    Platform.runLater(() -> chatArea.appendText("[Błąd: " + ex.getMessage() + "]\n"));
                }
            };
            client.connect();
        } catch (Exception e) {
            chatArea.appendText("Błąd połączenia: " + e.getMessage() + "\n");
        }
    }

    private void sendMessage() {
        if (client == null || !client.isOpen()) {
            chatArea.appendText("[Nie połączono z serwerem]\n");
            return;
        }

        String name = nameField.getText().trim();
        String text = rsaImplementation.
                EncryptionMessege(messageField.getText().trim(),
                        anotherUser.getPublicKey());

        if (text.isEmpty()) return;
        if (name.isEmpty()) name = "Anonim";

        JSONObject msg = new JSONObject();
        msg.put("from", name);
        msg.put("text", text);


        // STOMP SEND frame
        String frame = "SEND\ndestination:/app/send\ncontent-type:application/json\n\n"
                + msg.toString() + "\0";
        client.send(frame);

        messageField.clear();
    }
}
<<<<<<< HEAD
=======
=======
                    if (message.startsWith("MESSAGE")) {
                        try {
                            String body = message.substring(message.indexOf("\n\n") + 2, message.lastIndexOf('\0'));
                            JSONObject json = new JSONObject(body);

                            String from = json.optString("sender");
                            String to = json.optString("recipient");
                            String encryptedText = json.optString("text");

                            String myName = Person.getPerson().getName();

                            if (to.equalsIgnoreCase(myName) && from.equalsIgnoreCase(recipientName)) {
                                String decryptedText = myRsa.DescriptionMessege(encryptedText);
                                Platform.runLater(() -> chatArea.appendText(from + ": " + decryptedText + "\n"));
                            }
                        } catch (Exception e) {
                            System.err.println("[WS] Błąd: " + e.getMessage());
                        }
                    }
                }
                @Override public void onClose(int i, String s, boolean b) {}
                @Override public void onError(Exception e) { e.printStackTrace(); }
            };
            client.connect();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void sendMessage() {
        if (client == null || !client.isOpen()) return;

        String plainText = messageField.getText().trim();
        if (plainText.isEmpty()) return;

        try {
            String encryptedText = myRsa.EncryptionMessege(plainText, recipientPublicKey);
            KeyStorage.saveSentMessage(Person.getPerson().getName(), recipientName, plainText);

            JSONObject msg = new JSONObject();
            msg.put("sender", Person.getPerson().getName());
            msg.put("recipient", recipientName);
            msg.put("text", encryptedText);

            String frame = "SEND\ndestination:/app/send\ncontent-type:application/json\n\n"
                    + msg.toString() + "\0";

            client.send(frame);
            chatArea.appendText("Ja: " + plainText + "\n");
            messageField.clear();

        } catch (Exception e) {
            System.err.println("[BŁĄD] " + e.getMessage());
        }
    }

    private void loadHistory() {
        String myName = Person.getPerson().getName();
        String historyJson = HttpSender.getHistory(myName, recipientName);

        if (historyJson == null || historyJson.isEmpty()) return;

        try {
            org.json.JSONArray messages = new org.json.JSONArray(historyJson);
            List<String> myLocalSentMessages = KeyStorage.loadSentMessages(myName, recipientName);
            int mySentCounter = 0;

            for (int i = 0; i < messages.length(); i++) {
                org.json.JSONObject msg = messages.getJSONObject(i);
                String sender = msg.getString("sender");
                String encryptedText = msg.getString("text");

                if (!sender.equalsIgnoreCase(myName)) {
                    try {
                        String decrypted = myRsa.DescriptionMessege(encryptedText);
                        chatArea.appendText(sender + ": " + decrypted + "\n");
                    } catch (Exception e) {
                        chatArea.appendText(sender + ": [Błąd deszyfracji]\n");
                    }
                } else {
                    String content = "[Wysłano]";
                    if (mySentCounter < myLocalSentMessages.size()) {
                        content = myLocalSentMessages.get(mySentCounter);
                        mySentCounter++;
                    }
                    chatArea.appendText("Ja: " + content + "\n");
                }
            }
            chatArea.appendText("--- Koniec historii ---\n");
        } catch (Exception e) {
            System.err.println("[HISTORIA] Błąd: " + e.getMessage());
        }
    }
}
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
