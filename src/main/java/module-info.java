module com.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.finalproject to javafx.fxml;
    exports com.example.finalproject.FX;
    exports com.example.finalproject.Class;

}