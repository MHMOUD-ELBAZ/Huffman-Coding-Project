package ds.project.Huffman;

public class TreeNode implements Comparable<TreeNode> {
    private char character;
    private int weight;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(TreeNode o) {
        return this.weight - o.weight ;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getWeight() {
        return weight;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
