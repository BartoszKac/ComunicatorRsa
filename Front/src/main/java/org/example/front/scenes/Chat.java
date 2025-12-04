package org.example.front.scenes;

import javafx.application.Platform;
import javafx.geometry.Insets;
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
                }

                @Override
                public void onMessage(String message) {
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
