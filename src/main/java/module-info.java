module com.hydottech {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.hydottech to javafx.fxml;
    exports com.hydottech;
}
