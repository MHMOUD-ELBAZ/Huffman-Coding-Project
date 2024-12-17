package ds.project.Huffman;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class TreeVisualizer {

    public void visualize(TreeNode huffmanTree, Pane pane, double initialWidth, double initialHeight) {
        if (huffmanTree == null) return;

        // Add listeners to handle pane resizing
        pane.widthProperty().addListener((observable, oldValue, newValue) ->
                updateTreeVisualization(huffmanTree, pane, newValue.doubleValue(), pane.getHeight())
        );
        pane.heightProperty().addListener((observable, oldValue, newValue) ->
                updateTreeVisualization(huffmanTree, pane, pane.getWidth(), newValue.doubleValue())
        );

        // Initial visualization
        drawTree(pane, huffmanTree, initialWidth / 2, 0.1 * initialHeight, initialWidth / 4, initialHeight);
    }

    private void updateTreeVisualization(TreeNode huffmanTree, Pane pane, double width, double height) {
        pane.getChildren().clear(); // Clear previous elements
        drawTree(pane, huffmanTree, width / 2, 0.1 * height, width / 4, height);
    }

    private void drawTree(Pane pane, TreeNode node, double x, double y, double horizontalOffset, double paneHeight) {
        if (node == null) return;

        double verticalSpacing = paneHeight * 0.1; // Adjust spacing dynamically based on pane height

        // Draw left subtree
        if (node.getLeftChild() != null) {
            Line leftLine = new Line(x, y, x - horizontalOffset, y + verticalSpacing);
            leftLine.setStroke(Color.DARKGRAY);
            pane.getChildren().add(leftLine);
            drawTree(pane, node.getLeftChild(), x - horizontalOffset, y + verticalSpacing, horizontalOffset / 2, paneHeight);
        }

        // Draw right subtree
        if (node.getRightChild() != null) {
            Line rightLine = new Line(x, y, x + horizontalOffset, y + verticalSpacing);
            rightLine.setStroke(Color.DARKGRAY);
            pane.getChildren().add(rightLine);
            drawTree(pane, node.getRightChild(), x + horizontalOffset, y + verticalSpacing, horizontalOffset / 2, paneHeight);
        }

        // Draw node circle
        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(x, y, 20, Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);

        // Add node text
        String nodeText = node.isLeaf() ? (node.getCharacter() + "[" + node.getWeight() + "]") : String.valueOf(node.getWeight());
        Text text = new Text(x - 10, y + 5, nodeText);
        text.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
        pane.getChildren().add(text);
    }
}

