module com.group5.iftt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.group5.iftt to javafx.fxml;
    exports com.group5.iftt;
}