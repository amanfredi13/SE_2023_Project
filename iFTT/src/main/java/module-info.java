module com.group5.iftt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.group5.iftt to javafx.fxml;
    exports com.group5.iftt;
    exports com.group5.iftt.Controller;
    opens com.group5.iftt.Controller to javafx.fxml;
    exports com.group5.iftt.Model.ActionClassStates;
    opens com.group5.iftt.Model.ActionClassStates to javafx.fxml;
    exports com.group5.iftt.Model.TriggerClassStates;
    opens com.group5.iftt.Model.TriggerClassStates to javafx.fxml;
    exports com.group5.iftt.Model.Actions;
    opens com.group5.iftt.Model.Actions to javafx.fxml;
    exports com.group5.iftt.Model.Triggers;
    opens com.group5.iftt.Model.Triggers to javafx.fxml;
    exports com.group5.iftt.Model.RuleAndSerialize;
    opens com.group5.iftt.Model.RuleAndSerialize to javafx.fxml;
}