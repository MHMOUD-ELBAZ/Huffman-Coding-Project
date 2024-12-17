module ds.project.Huffman {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ds.project.Huffman to javafx.fxml;
    exports ds.project.Huffman;
}