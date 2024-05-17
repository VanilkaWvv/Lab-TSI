module org.example.lab10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab10 to javafx.fxml;
    exports org.example.lab10;
}