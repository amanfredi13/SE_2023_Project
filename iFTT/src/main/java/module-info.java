module com.group5.iftt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.group5.iftt to javafx.fxml;
    exports com.group5.iftt;
    exports com.group5.iftt.Model;
    opens com.group5.iftt.Model to javafx.fxml;
    exports com.group5.iftt.Controller;
    opens com.group5.iftt.Controller to javafx.fxml;
}