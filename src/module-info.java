module Offline.test3 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens sample to javafx.graphics, javafx.fxml, javafx.base;
}