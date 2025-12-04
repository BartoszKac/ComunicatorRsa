package org.example.front.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.front.RSA.RsaImplementation;
import org.example.front.http.HttpSender;
import org.example.front.model.Const;
import org.example.front.model.Person;

public class Register {

    static Register register = new Register();



    ChatUser chatUser = ChatUser.getChatUser();

    public static Register getRegister() {
        return  register;
    }

    public void showRegister(Stage stage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");

        Button registerButton = new Button("Register");

        registerButton.setOnAction(e -> {
            String name = nameField.getText();
            String password = passwordField.getText();
            if (name.isEmpty() || password.isEmpty()) {
                System.out.println("Name and Password cannot be empty");
                return;
            }
            System.out.println("Attempting to register user: " + name);

            RsaImplementation rsa = new RsaImplementation();
            rsa.run();

            Person person = new Person(name, password, rsa.getPublicKey().toString());

            if(HttpSender.PostRegister(person)){
                System.out.println("Registration successful");
                Person.setPerson(person);
               // Const.saveKey(rsa.getPrivateKey()., rsa.getPublicKey());
                chatUser.showUserInput(stage);
            } else {
                System.out.println("Registration failed");
            }


        });

        VBox root = new VBox(10, new Label("Register"), nameField, passwordField, registerButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }
}
