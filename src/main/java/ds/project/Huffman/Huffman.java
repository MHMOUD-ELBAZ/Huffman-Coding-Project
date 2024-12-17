package ds.project.Huffman;

import java.util.*;

public class Huffman {
    private String plainText;
    private LinkedHashMap<Character, FrequencyCode> map;
    private TreeNode huffmanTree;

    public Huffman() {
    }

    public String encode(String plainText){
        this.plainText = plainText;

        //1-set the frequency of each char in the map
        mapPlainText();

        //2-Set the tree
        setHuffmanTree();


        //3-set the code for each char while traversing the tree
        traverse(huffmanTree, new LinkedList<>());

        //4-set the encoded text
        StringBuilder encodedText = new StringBuilder();
        for(Character c : plainText.toCharArray()){
            encodedText.append(map.get(c).code);
        }


        return encodedText.toString();
    }

    public Map<Character, FrequencyCode> getLastMap(){return map; }

    public TreeNode getHuffmanTree(){return huffmanTree;}

    private void mapPlainText(){
        map = new LinkedHashMap<>();

        for(int i = 0; i < plainText.length(); i++){
            char c = plainText.charAt(i);

            if(map.containsKey(c)){
                FrequencyCode fc = map.get(c);
                fc.frequency++;
            }
            else{
                map.put(c, new FrequencyCode(1));
            }
        }
    }

    private void setHuffmanTree(){
        MinHeap<TreeNode> heap = new MinHeap<>();

        for(Map.Entry<Character,FrequencyCode> entry : map.entrySet()){
            TreeNode node = new TreeNode(entry.getValue().frequency);
            node.setCharacter(entry.getKey());
            heap.add(node);
        }

        while(heap.getSize() > 1){
            TreeNode left = heap.removeRoot();
            TreeNode right = heap.removeRoot();

            TreeNode newNode = new TreeNode(left.getWeight() + right.getWeight());
            newNode.setLeftChild(left);
            newNode.setRightChild(right);

            heap.add(newNode);
        }

        this.huffmanTree = heap.removeRoot();
    }

    private void traverse(TreeNode root, Deque<Character> deque){
        if(root.isLeaf()){
            String code = getCode(deque);
            if(deque.isEmpty()){code = "0";}
            map.get(root.getCharacter()).code = code;
        }

        if(root.getLeftChild() != null){
            deque.addLast('0');
            traverse(root.getLeftChild(), deque);
            deque.removeLast();
        }

        if(root.getRightChild() != null){
            deque.addLast('1');
            traverse(root.getRightChild(), deque);
            deque.removeLast();
        }
    }

    private String getCode(Deque<Character> deque){
        StringBuilder code = new StringBuilder();

        for(Character c : deque)
            code.append(c);

        return code.toString();
    }
}
