module com.example.binarybashers_quadline {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.binarybashers_quadline to javafx.fxml;
    exports com.example.binarybashers_quadline;
}