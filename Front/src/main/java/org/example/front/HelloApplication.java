package org.example.front;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.front.RSA.RsaImplementation;
import org.example.front.scenes.ChatUser;
import org.example.front.scenes.Login;
import org.example.front.scenes.MainMenu;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;

public class HelloApplication extends Application {



    MainMenu menu = MainMenu.getMenu();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
      menu.showMenu(stage);
<<<<<<< HEAD
       // chatUser.showUserInput(stage);
=======
<<<<<<< HEAD
       // chatUser.showUserInput(stage);
=======
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)

    }





}
