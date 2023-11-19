module ui {
    requires javafx.controls;
    requires javafx.fxml;


    exports ui;
    exports structure.Matrix;
    exports structure.List;
    opens ui to javafx.fxml, javafx.controls;
}