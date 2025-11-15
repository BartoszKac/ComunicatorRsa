module org.example.front {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.json;              // JSON
    requires org.java_websocket;
    opens org.example.front to javafx.fxml;
    exports org.example.front;
}