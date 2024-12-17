package ds.project.Huffman;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.Map;

public class HelloApplication extends Application {
    private TreeNode huffmanTree;
    private final double width = 1000;
    private final double height = 720;

    @Override
    public void start(Stage primaryStage) {
        // Initial scene: User input
        BorderPane inputPane = new BorderPane();
        VBox inputBox = new VBox(10);
        inputBox.setPadding(new Insets(20));
        inputBox.setStyle("-fx-background-color: #f0f8ff;");

        Label titleLabel = new Label("Huffman Encoding Tool");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label promptLabel = new Label("Enter text to encode:");
        TextField inputField = new TextField();
        inputField.setPromptText("Enter your text here...");

        TableView<Map.Entry<Character, FrequencyCode>> tableView = new TableView<>();
        TableColumn<Map.Entry<Character, FrequencyCode>, String> charColumn = new TableColumn<>("Character");
        charColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getKey().toString()));
        charColumn.setPrefWidth(100);

        TableColumn<Map.Entry<Character, FrequencyCode>, String> freqColumn = new TableColumn<>("Frequency");
        freqColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                String.valueOf(data.getValue().getValue().frequency)));
        freqColumn.setPrefWidth(100);

        TableColumn<Map.Entry<Character, FrequencyCode>, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getValue().code));
        codeColumn.setPrefWidth(200);

        tableView.getColumns().addAll(charColumn, freqColumn, codeColumn);
        tableView.setPrefWidth(400);

        Label resultLabel = new Label();
        resultLabel.setWrapText(true);

        Button encodeButton = new Button("Encode");
        encodeButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold;");

        Button visualizeButton = new Button("Visualize Huffman Tree");
        visualizeButton.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-font-weight: bold;");
        visualizeButton.setDisable(true);

        inputBox.getChildren().addAll(titleLabel, promptLabel, inputField, encodeButton, resultLabel, tableView, visualizeButton);
        inputPane.setCenter(inputBox);

        Scene inputScene = new Scene(inputPane, width, height);


        // Huffman Tree Visualizer Scene
        StackPane treePane = new StackPane();
        Pane treeContentPane = new Pane(); // Pane for tree visualization
        treePane.getChildren().add(treeContentPane);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ff6347; -fx-text-fill: white; -fx-font-weight: bold;");
        StackPane.setMargin(backButton, new Insets(10));
        StackPane.setAlignment(backButton, Pos.TOP_LEFT); // Fix position to the top-left corner

        treePane.getChildren().add(backButton);
        Scene treeScene = new Scene(treePane, width, height);

        backButton.setOnAction(event -> primaryStage.setScene(inputScene));

        visualizeButton.setOnAction(event -> {
            if (huffmanTree != null) {
                treeContentPane.getChildren().clear(); // Clear only the tree content
                TreeVisualizer treeVisualizer = new TreeVisualizer();
                treeVisualizer.visualize(huffmanTree, treeContentPane, width, height);
                primaryStage.setScene(treeScene);
            }
        });


        encodeButton.setOnAction(event -> {
            String text = inputField.getText();
            if (text.isEmpty()) {
                resultLabel.setText("Please enter some text.");
                return;
            }


            Huffman huffman = new Huffman();
            String encodedText = huffman.encode(text);
            huffmanTree = huffman.getHuffmanTree();
            Map<Character, FrequencyCode> map = huffman.getLastMap();


            tableView.getItems().clear();
            tableView.getItems().addAll(map.entrySet());

            resultLabel.setText("Encoded Text: " + encodedText);
            visualizeButton.setDisable(false);
        });


        primaryStage.setTitle("Huffman Tree Visualizer");

        primaryStage.setScene(inputScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
