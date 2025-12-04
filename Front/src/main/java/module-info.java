module org.example.front {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;              // JSON
    requires org.java_websocket;
    requires org.slf4j;
    requires java.prefs;

    opens org.example.front to javafx.fxml;
    exports org.example.front;
          // dodaj SLF4J API

}